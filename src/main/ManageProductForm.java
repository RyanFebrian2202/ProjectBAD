package main;

import javafx.application.Application;
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
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void initialize() {
		
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
		watchBrandCBX.getItems().add("Rolex");
		watchBrandCBX.getItems().add("Omega");
		watchBrandCBX.getItems().add("Fossil");
		watchBrandCBX.getItems().add("Guess");
		watchBrandCBX.getItems().add("Alexandre Christie");
		watchBrandCBX.getItems().add("Casio");
		watchBrandCBX.getSelectionModel().select(3);
		
		scene = new Scene(bPane, 750, 550);
		
	}
	
	public void arrangeComponent() {
		
		TableView tableView = new TableView<Watch>();
		
		TableColumn<Watch, String> column1 = new TableColumn<>("Watch ID");
		column1.setMinWidth(110);
		column1.setCellValueFactory(new PropertyValueFactory<Watch, String>("watchId"));

		TableColumn<Watch, String> column2 = new TableColumn<>("Watch Name");
		column2.setMinWidth(270);
		column2.setCellValueFactory(new PropertyValueFactory<Watch, String>("watchName"));

		TableColumn<Watch, String> column3 = new TableColumn<>("Watch Brand");
		column3.setMinWidth(130);
		column3.setCellValueFactory(new PropertyValueFactory<Watch, String>("watchBrand"));

		TableColumn<Watch, Integer> column4 = new TableColumn<>("Watch Price");
		column4.setMinWidth(130);
		column4.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("watchPrice"));

		TableColumn<Watch, Integer> column5 = new TableColumn<>("Watch Stock");
		column5.setMinWidth(110);
		column5.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("watchStock"));

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);
		tableView.getColumns().add(column4);
		tableView.getColumns().add(column5);
		
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.getItems().add(new Watch(1, "Submariner 300 Watch", "Rolex", 10500, 4));
		tableView.getItems().add(new Watch(2, "Seamaster Planet Ocean Watch", "Omega", 6900, 5));

		VBox vbox = new VBox(tableView);
		
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
		
	
		bPane.setTop(tableView);
		bPane.setCenter(gPane);
		bPane.setBottom(fPane);
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
		
//		insertWatchBtn.setOnAction((evt)->{
//			if(!watchNameTF.getText().endsWith("Watch")) {
//				
//			} else if(!watchPriceTF.getText().equals(0)) {
//				
//			} else if(watchStockSpn.getValueFactory().equals(0)) {
//				
//			} else if(watchBrandCBX.getSelectionModel().equals(0)) {
//				
//			} else {
//				tableView.getItems().add((new Watch(watchNameTF.getText(), watchPriceTF.getText(), watchStockSpn.getValueFactory(), watchBrandCBX.getSelectionModel())));
//			}
//		});
////				Alert alert = new Alert(AlertType.ERROR);
////				alert.setHeaderText("Error name");
////				alert.setContentText("Invalid name!");
////				alert.show();
//			} else {
//				tableUser.getItems().add((new User(nameTF.getText(), ageSPN.getValue())));
//			}
//		});
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Manage Product");
		primaryStage.show();
	}

}
