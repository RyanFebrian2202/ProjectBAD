package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ManageBrandForm extends Application{
	
	Scene scene;
	BorderPane bPane;
	GridPane gPane;
	FlowPane fPane;
	
	Label watchBrandLbl;
	Button insertBrandBtn, updateBrandBtn, deleteBrandBtn;
	TextField brandNameTF;
	ScrollPane scrollPane;
	
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
		
		scene = new Scene(bPane, 450, 550);
	}
	
	public void arrangeComponent() {
		TableView<Watch> tableView = new TableView<>();
		
		TableColumn<Watch, String> column1 = new TableColumn<>("Brand ID");
		column1.setCellValueFactory(new PropertyValueFactory<>("brandId"));

		TableColumn<Watch, String> column2 = new TableColumn<>("Brand Name");
		column2.setCellValueFactory(new PropertyValueFactory<>("brandName"));
		
		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		
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
		
	
		bPane.setTop(tableView);
		bPane.setCenter(gPane);
		bPane.setBottom(fPane);
		scrollPane.setContent(bPane);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {	
		initialize();
		arrangeComponent();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Manage Brand");
		primaryStage.show();
	}
}
