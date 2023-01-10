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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Brand;
import model.Watch;

public class ManageProductForm extends Application {
	
	Scene scene;
	BorderPane bPane;
	GridPane gPane;
	FlowPane fPane;
	
	Label watchNameLbl, watchPriceLbl, watchStockLbl, watchBrandLbl;
	Button insertWatchBtn, updateWatchBtn, deleteWatchBtn;
	TextField watchNameTF, watchPriceTF;
	Spinner<Integer> watchStockSpn;
	ComboBox<String> watchBrandCBX;
	TableView<Watch> watchTable;
	
	Vector<Brand> brandList = new Vector<Brand>();
	Vector<Watch> watchList = new Vector<Watch>();
	
	Database db = Database.getConnection();
	
	Boolean kondisi = false;
	
	public void getBrandData() {
		String query = "SELECT * FROM `brand`";
		ResultSet rs = db.executeQuery(query);
		
		try {
			while(rs.next()) {
				int brandID = rs.getInt("BrandID");
				String brandName = rs.getString("BrandName");
				
				Brand brand = new Brand(brandID,brandName);
				brandList.add(brand);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initialize() {
		
		getBrandData();
		
		bPane = new BorderPane();
		gPane = new GridPane();
		fPane = new FlowPane();
		
		// Label Area
		watchNameLbl = new Label ("Watch Name: ");
		watchNameLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		watchPriceLbl = new Label ("Watch Price: ");
		watchPriceLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		watchStockLbl = new Label ("Watch Stock: ");
		watchStockLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		watchBrandLbl = new Label ("Watch Brand: ");
		watchBrandLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		
		// Button Area
		insertWatchBtn = new Button ("Insert Watch");
		insertWatchBtn.setPrefWidth(100);
		insertWatchBtn.setPrefHeight(30);
		
		updateWatchBtn = new Button ("Update Watch");
		updateWatchBtn.setPrefWidth(130);
		updateWatchBtn.setPrefHeight(30);
		
		deleteWatchBtn = new Button ("Delete Watch");
		deleteWatchBtn.setPrefWidth(100);
		deleteWatchBtn.setPrefHeight(30);
		
		// Text Field Area
		watchNameTF = new TextField ();
		watchNameTF.setPromptText("Name");
		watchPriceTF = new TextField ();
		watchPriceTF.setPromptText("Price");
		
		// Spinner Area
		watchStockSpn = new Spinner<>(0, 1000, 0); // Angka pertama itu mulai dari brp, angka kedua itu sampai berapa, angka ketiga itu defaultnya.
		
		// Combo Box Area
		watchBrandCBX = new ComboBox<>();
		watchBrandCBX.getItems().add("Choose One");
		for(int i = 0; i<brandList.size(); i++) {
			watchBrandCBX.getItems().add(brandList.get(i).getBrandName());
		}
		watchBrandCBX.getSelectionModel().select(0);
		
		scene = new Scene(bPane, 750, 550);
		
	}
	
	public void arrangeComponent() {
		
		watchTable = new TableView<>();

		TableColumn<Watch, Integer> column1 = new TableColumn<>("Watch ID");
		column1.setCellValueFactory(new PropertyValueFactory<>("WatchID"));

		TableColumn<Watch, String> column2 = new TableColumn<>("Watch Name");
		column2.setCellValueFactory(new PropertyValueFactory<>("WatchName"));

		TableColumn<Watch, String> column3 = new TableColumn<>("Watch Brand");
		column3.setCellValueFactory(new PropertyValueFactory<>("WatchBrand"));

		TableColumn<Watch, Integer> column4 = new TableColumn<>("Watch Price");
		column4.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("WatchPrice"));
		column4.setMinWidth(130);

		TableColumn<Watch, Integer> column5 = new TableColumn<>("Watch Stock");
		column5.setCellValueFactory(new PropertyValueFactory<>("WatchStock"));
		column5.setMinWidth(110);
		
		watchTable.getColumns().addAll(column1,column2,column3,column4,column5);
		
		AlertError("You must select a watch from the table first!");
		AlertInformation("New watch successfully inserted!");
		AlertInformation("Watch successfully updated!");
		AlertInformation("Watch successfully deleted!");
		
		watchNameTF.setMinWidth(170);
		watchPriceTF.setMinWidth(170);
		
		gPane.add(watchNameLbl, 0, 0);
		gPane.add(watchStockLbl, 0, 1);
		gPane.add(watchNameTF, 1, 0);
		gPane.add(watchStockSpn, 1, 1);
		gPane.add(watchPriceLbl, 2, 0);
		gPane.add(watchBrandLbl, 2, 1);
		gPane.add(watchPriceTF, 3, 0);
		gPane.add(watchBrandCBX, 3, 1);
		gPane.setHgap(10);
		gPane.setVgap(10);
		gPane.setPadding(new Insets(10, 0, 0, 0));
		gPane.setAlignment(Pos.CENTER);
		
		fPane.setPadding(new Insets(6, 0, 20, 0));
		fPane.setHgap(15);
		fPane.getChildren().addAll(insertWatchBtn, updateWatchBtn, deleteWatchBtn);
		fPane.setAlignment(Pos.TOP_CENTER);
		
		bPane.setTop(watchTable);
		bPane.setCenter(gPane);
		bPane.setBottom(fPane);
	}
	
	public void getData() {
		String query = "SELECT * FROM `watch`";
		ResultSet rs = db.executeQuery(query);
		
		try {
			while(rs.next()) {
				int watchid = rs.getInt("WatchID");
				String watchname = rs.getString("WatchName");
				int watchbrandID = rs.getInt("BrandID");
				int watchprice = rs.getInt("WatchPrice");
				int watchstock = rs.getInt("WatchStock");
				
				String querybrand = "SELECT * FROM `brand` WHERE BrandID = " + watchbrandID;
				ResultSet rsb = db.executeQuery2(querybrand);
				String watchbrand = "";
				if(rsb.next()) {
					watchbrand = rsb.getString("BrandName");					
				}
				
				Watch watch = new Watch(watchid, watchname, watchbrand, watchprice, watchstock);
				watchList.add(watch);
				rsb.close();
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refreshTable() {
		watchList.clear();
		getData();
		ObservableList<Watch> watchObs = FXCollections.observableArrayList(watchList);
		watchTable.setItems(watchObs);
	}
	
	public void addWatch() {
		insertWatchBtn.setOnMouseClicked((event)->{
			String brandName = watchBrandCBX.getValue();
			
			if(brandName.equals("Choose One")) {
				//error
			} else {
				brandList.clear();
				getBrandData();
				int brandID = 1;
				for(int i = 0; i<brandList.size(); i++) {
					if(brandList.get(i).getBrandName().equals(brandName)) {
						brandID = brandList.get(i).getBrandID();
					}
				}
				
				String query = String.format("INSERT INTO `watch`(`BrandID`, `WatchName`, `WatchPrice`, `WatchStock`) VALUES ('%d','%s','%d','%d')", brandID,watchNameTF.getText(),Integer.parseInt(watchPriceTF.getText()),watchStockSpn.getValue());
				db.executeUpdate(query);
				watchNameTF.setText("");
				watchPriceTF.setText("");
				watchStockSpn.getValueFactory().setValue(0);
				watchBrandCBX.getSelectionModel().select(0);
				refreshTable();
			}
		});
	}
	
	public void editTable() {
		watchTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Watch>() {

			@Override
			public void changed(ObservableValue<? extends Watch> observable, Watch oldValue, Watch newValue) {
				// TODO Auto-generated method stub
				if (newValue != null) {
					kondisi = true;
					
					brandList.clear();
					getBrandData();
					watchNameTF.setText(newValue.getWatchName());
					watchPriceTF.setText(Integer.toString(newValue.getWatchPrice()));
					watchStockSpn.getValueFactory().setValue(newValue.getWatchStock());
					String brandValue = newValue.getWatchBrand();
					
					for(int i = 0; i<brandList.size(); i++) {
						if(brandList.get(i).getBrandName().equals(brandValue)) {
							watchBrandCBX.getSelectionModel().select(i+1);
						}
					}
					
					updateWatchBtn.setOnMouseClicked((event)->{
						String brandName = watchBrandCBX.getValue();
						brandList.clear();
						getBrandData();
						int brandID = 1;
						for(int i = 0; i<brandList.size(); i++) {
							if(brandList.get(i).getBrandName().equals(brandName)) {
								brandID = brandList.get(i).getBrandID();
							}
						}
						String query = String.format("UPDATE `watch` SET `BrandID`='%d',`WatchName`='%s',`WatchPrice`='%d',`WatchStock`='%d' WHERE `WatchID` = %d", brandID,watchNameTF.getText(),Integer.parseInt(watchPriceTF.getText()),watchStockSpn.getValue(),newValue.getWatchID());
						db.executeUpdate(query);
						watchNameTF.setText("");
						watchPriceTF.setText("");
						watchStockSpn.getValueFactory().setValue(0);
						watchBrandCBX.getSelectionModel().select(0);
						refreshTable();
					});
					
					deleteWatchBtn.setOnMouseClicked((event)->{
						String query = String.format("DELETE FROM `watch` WHERE `WatchID` = %d", newValue.getWatchID());
						db.executeUpdate(query);
						watchNameTF.setText("");
						watchPriceTF.setText("");
						watchStockSpn.getValueFactory().setValue(0);
						watchBrandCBX.getSelectionModel().select(0);
						refreshTable();
					});
				}
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void AlertError(String content) {
		Alert error = new Alert(AlertType.ERROR);
		error.setHeaderText("Error");
		error.setContentText(content);
		error.show();
	}
	
	public void AlertInformation(String content) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setHeaderText("Message");
		info.setContentText(content);
		info.show();
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {	
		initialize();
		arrangeComponent();
		refreshTable();
		addWatch();
		editTable();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Manage Product");
		primaryStage.show();
	}
	
//	insertWatchBtn.setOnAction((evt)->{
//	if(!watchNameTF.getText().endsWith("Watch")) {
//		
//	} else if(!watchPriceTF.getText().equals(0)) {
//		
//	} else if(watchStockSpn.getValueFactory().equals(0)) {
//		
//	} else if(watchBrandCBX.getSelectionModel().equals(0)) {
//		
//	} else {
//		tableView.getItems().add((new Watch(watchNameTF.getText(), watchPriceTF.getText(), watchStockSpn.getValueFactory(), watchBrandCBX.getSelectionModel())));
//	}
//});
////		Alert alert = new Alert(AlertType.ERROR);
////		alert.setHeaderText("Error name");
////		alert.setContentText("Invalid name!");
////		alert.show();
//	} else {
//		tableUser.getItems().add((new User(nameTF.getText(), ageSPN.getValue())));
//	}
//});

}
