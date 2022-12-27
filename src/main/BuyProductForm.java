package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BuyProductForm extends Application{
	
	Scene scene;
	BorderPane bPane1, bPanequan;
	GridPane gPane;
	FlowPane bottomBtn;

	
	Label selectWatchLbl, quantityLbl, watchNameLbl;
	Button addWatchToCartBtn, clearCartBtn, checkOutBtn;
	Spinner<Integer> quantitySp;
	
	TableView<Watch> watchTable, cartTable;

	
	
	public void setTableWatch() {
		watchTable = new TableView<>();
		TableColumn<Watch, String> col1 = new TableColumn<Watch, String>("Watch ID");
		TableColumn<Watch, String> col2 = new TableColumn<Watch, String>("Watch Name");
		TableColumn<Watch, String> col3 = new TableColumn<Watch, String>("Watch Brand");
		TableColumn<Watch, Integer> col4 = new TableColumn<Watch, Integer>("Watch Price");
		TableColumn<Watch, Integer> col5 = new TableColumn<Watch, Integer>("Watch Stock");
		
		col1.setCellValueFactory(new PropertyValueFactory<Watch, String>("WatchID"));
		col2.setCellValueFactory(new PropertyValueFactory<Watch, String>("WatchName"));
		col3.setCellValueFactory(new PropertyValueFactory<Watch, String>("WatchBrand"));
		col4.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("WatchPrice"));
		col5.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("WatchStock"));
		
		//add ke table pakai colom
		watchTable.getColumns().addAll(col1, col2, col3, col4, col5);
		
		bPane1.setTop(watchTable);
	}
	
	public void setTableCart() {
		cartTable = new TableView<>();
//		TableColumn<Watch, String> col1 = new TableColumn<Watch, String>("User ID");
//		TableColumn<Watch, String> col2 = new TableColumn<Watch, String>("Watch ID");
//		TableColumn col3 = new TableColumn<> ("Quantity");
//
//		
//		col1.setCellValueFactory(new PropertyValueFactory<Watch, String>("UserID"));
//		col2.setCellValueFactory(new PropertyValueFactory<Watch, String>("WatchID"));
//		col3.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
	}
	
	public void init() {
		bPane1 = new BorderPane();
		bPanequan = new BorderPane();
		gPane = new GridPane();
		bottomBtn = new FlowPane();
		
//		watch namenya nanti ganti ke get text
		watchNameLbl = new Label("rolex");
		
		selectWatchLbl = new Label("Selected Watch: " + watchNameLbl.getText());
		bPanequan.setTop(selectWatchLbl);
		selectWatchLbl.setAlignment(Pos.TOP_LEFT);
		
		quantityLbl = new Label("Quantity: ");
		bPanequan.setCenter(quantityLbl);
		gPane.add(quantityLbl, 0, 0);
		
		quantitySp = new Spinner<>(0, 100, 0, 1);
		bPanequan.setCenter(quantitySp);
		gPane.add(quantitySp, 1, 0);
		
		addWatchToCartBtn = new Button("Add Watch To Cart");
		bPanequan.setCenter(addWatchToCartBtn);
		gPane.add(addWatchToCartBtn, 2, 0);
		
		gPane.setHgap(10);
		gPane.setAlignment(Pos.CENTER);
		bPanequan.setCenter(gPane);
		
		clearCartBtn = new Button("Clear Cart");
		bottomBtn.getChildren().add(clearCartBtn);
		
		checkOutBtn = new Button("Checkout");
		bottomBtn.getChildren().add(checkOutBtn);
		
		bottomBtn.setHgap(15);
		
		
		bPane1.setCenter(bPanequan);

		bPane1.setBottom(bottomBtn);
		bottomBtn.setAlignment(Pos.BOTTOM_CENTER);
		
		
		
		scene = new Scene(bPane1, 600, 600);
	}
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		setTableWatch();
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
