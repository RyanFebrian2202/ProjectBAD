package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;
//import jfxtras.labs.scene.control.window.Window;
import model.Brand;
import model.Cart;
import model.Watch;

public class ManageBrandForm{

	private static ManageBrandForm instance;
	
//	Scene scene;
	BorderPane bPane;
	GridPane gPane;
	FlowPane fPane;
	
	Label watchBrandLbl;
	Button insertBrandBtn, updateBrandBtn, deleteBrandBtn;
	TextField brandNameTF;
	ScrollPane scrollPane;
	Database db = Database.getConnection();
	TableView<Brand> brandTable;
	Window windowBrand;
	
	Vector<Brand> brandList = new Vector<Brand>();
	
	int brandID = 0;
	
	public static ManageBrandForm getInstance() {
		if (instance == null) {
			instance = new ManageBrandForm();
		}
		return instance;
	}
	
	public void initialize() {
		bPane = new BorderPane();
		gPane = new GridPane();
		fPane = new FlowPane();
		
		// Label Area
		watchBrandLbl = new Label ("Brand Name:");
		watchBrandLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		
		// Button Area
		insertBrandBtn = new Button ("Insert Brand");
		updateBrandBtn = new Button ("Update Brand");
		deleteBrandBtn = new Button ("Delete Brand");
		
		// Text Field Area
		brandNameTF = new TextField ();
		brandNameTF.setPromptText("Brand Name");
		
		scrollPane = new ScrollPane();
		
		windowBrand = new Window("Manage Brand");
		windowBrand.getRightIcons().add(new CloseIcon(windowBrand));
		windowBrand.getContentPane().getChildren().add(bPane);
		
//		scene = new Scene(bPane, 450, 550);
	}
	
	public void arrangeComponent() {
		brandTable = new TableView<Brand>();
		
		TableColumn<Brand, Integer> brandIdColumn = new TableColumn<Brand, Integer>("Brand ID");
		brandIdColumn.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("BrandID"));

		TableColumn<Brand, String> brandNameColumn = new TableColumn<Brand, String>("Brand Name");
		brandNameColumn.setCellValueFactory(new PropertyValueFactory<Brand, String>("BrandName"));
		
		brandTable.getColumns().addAll(brandIdColumn,brandNameColumn);
		
		brandTable.setMaxSize(452, 400);
		brandIdColumn.setMinWidth(450/2);
		brandNameColumn.setMinWidth(450/2);
		
		brandNameTF.setMinWidth(170);
		
		gPane.add(watchBrandLbl, 0, 0);
		gPane.add(brandNameTF, 1, 0);
		gPane.setHgap(10);
		gPane.setVgap(10);
		gPane.setPadding(new Insets(0, 0, 0, 10));
//		gPane.setAlignment(Pos.CENTER);
		gPane.setAlignment(Pos.CENTER_LEFT);
		
		fPane.setPadding(new Insets(6, 0, 20, 0));
		fPane.setHgap(10);
		fPane.getChildren().addAll(insertBrandBtn, updateBrandBtn, deleteBrandBtn);
		fPane.setAlignment(Pos.TOP_CENTER);
		
		
		bPane.setTop(brandTable);
		bPane.setCenter(gPane);
		bPane.setBottom(fPane);

		bPane.setAlignment(brandTable, Pos.TOP_CENTER);
		
		scrollPane.setContent(bPane);
	}
	
	public void getData() {
		String query = "SELECT * FROM `brand`";
		ResultSet rs = db.executeQuery(query);
		
		try {
			while(rs.next()) {
				int brandid = rs.getInt("BrandID");
				String brandname = rs.getString("BrandName");
				
				Brand brand = new Brand(brandid,brandname);
				brandList.add(brand);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refreshTable() {
		brandList.clear();
		getData();
		ObservableList<Brand> brandObs = FXCollections.observableArrayList(brandList);
		brandTable.setItems(brandObs);
	}
	
	public void addBrand() {
		
		insertBrandBtn.setOnMouseClicked((event)->{
			
			if (brandNameTF.getText().equals("")) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Error");
				error.setContentText("Data needed not complete!");
				error.showAndWait();
			}else {
				String query = String.format("INSERT INTO `brand`(`BrandName`) VALUES ('%s')", brandNameTF.getText());
				db.executeUpdate(query);
				brandNameTF.setText("");
				refreshTable();
				
				Alert info = new Alert(AlertType.INFORMATION);
				info.setHeaderText("Message");
				info.setContentText("New brand successfully inserted!");
				info.showAndWait();
			}
			
		});
	}
	
	public void editTable() {
//		TableSelectionModel<Brand> tableSelectionModel = brandTable.getSelectionModel();
//		tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
//		Brand brand = tableSelectionModel.getSelectedItem();
//		brandNameTF.setText(Integer.toString(brand.getBrandID()));
//		brand.getBrandName();
		
		brandTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Brand>() {

			@Override
			public void changed(ObservableValue<? extends Brand> observable, Brand oldValue, Brand newValue) {
				if (newValue != null) {
					brandID = newValue.getBrandID();
					brandNameTF.setText(newValue.getBrandName());
					
					updateBrandBtn.setOnMouseClicked((event)->{
						
						if (brandID > 0) {
							
							String query = String.format("UPDATE `brand` SET `BrandName`='%s' WHERE `BrandID` = %d", brandNameTF.getText(), newValue.getBrandID());
							db.executeUpdate(query);
							brandNameTF.setText("");
							brandID = 0;
							refreshTable();
							brandNameTF.setText("");

							
							Alert info = new Alert(AlertType.INFORMATION);
							info.setHeaderText("Message");
							info.setContentText("Brand successfully updated!");
							info.showAndWait();
						} else {
							Alert error = new Alert(AlertType.ERROR);
							error.setHeaderText("Error");
							error.setContentText("You must select a brand from the table first!");
							error.showAndWait();
						}
						
					});
					
					deleteBrandBtn.setOnMouseClicked((event)->{
						
						if(brandID > 0) {
							String query = String.format("DELETE FROM `brand` WHERE `BrandID` = %d", newValue.getBrandID());
							db.executeUpdate(query);
							brandNameTF.setText("");
							brandID = 0;
							refreshTable();
							
							Alert info = new Alert(AlertType.INFORMATION);
							info.setHeaderText("Message");
							info.setContentText("Brand successfully deleted!");
							info.showAndWait();
						} else {
							Alert error = new Alert(AlertType.ERROR);
							error.setHeaderText("Error");
							error.setContentText("You must select a brand from the table first!");
							error.showAndWait();
						}
					});
				}
			}
		});
	}
	
	public Window getWindow() {
		initialize();
		arrangeComponent();
		refreshTable();
		addBrand();
		editTable();
		
		updateBrandBtn.setOnMouseClicked(e -> {
			Alert error = new Alert(AlertType.ERROR);
			error.setHeaderText("Error");
			error.setContentText("You must select a brand from the table first!");
			error.showAndWait();
		});
		
		deleteBrandBtn.setOnMouseClicked(e -> {
			Alert error = new Alert(AlertType.ERROR);
			error.setHeaderText("Error");
			error.setContentText("You must select a brand from the table first!");
			error.showAndWait();
		});
		
		
		return windowBrand;
	}
//
//	public void test() {	
//		initialize();
//		arrangeComponent();
//		refreshTable();
//		addBrand();
//		editTable();
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("Manage Brand");
//		primaryStage.show();
//	}
}
