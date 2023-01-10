package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Cart;
//import jfxtras.labs.scene.control.window.CloseIcon;
import model.Watch;

public class BuyProductForm extends Application{
	
	Scene scene;
	BorderPane bPane1, bPanequan;
	GridPane gPane;
	FlowPane bottomBtn, QuanPane;
//	jfxtras.labs.scene.control.window.Window buyproductWindow;

	
	Label selectWatchLbl, quantityLbl, watchNameLbl;
	Button addWatchToCartBtn, clearCartBtn, checkOutBtn;
	Spinner<Integer> quantitySp;
	
	TableView<Watch> watchTable;
	TableView<Cart> cartTable;
	Vector<Watch> watchlist;
	Vector<Cart> cartlist;
	Database db = Database.getConnection();
	
	int watchId;
	
	public void setTableWatch() {
		watchTable = new TableView<>();
		watchlist = new Vector<>();
		TableColumn<Watch, Integer> col1 = new TableColumn<Watch, Integer>("Watch ID");
		TableColumn<Watch, String> col2 = new TableColumn<Watch, String>("Watch Name");
		TableColumn<Watch, Integer> col3 = new TableColumn<Watch, Integer>("Watch Brand");
		TableColumn<Watch, Integer> col4 = new TableColumn<Watch, Integer>("Watch Price");
		TableColumn<Watch, Integer> col5 = new TableColumn<Watch, Integer>("Watch Stock");
		
		col1.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("watchID"));
		col2.setCellValueFactory(new PropertyValueFactory<Watch, String>("WatchName"));
		col3.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("WatchBrand"));
		col4.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("WatchPrice"));
		col5.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("WatchStock"));
		
		watchTable.setMaxSize(650, 250);
		
//		set minimal ukuran kolom
		col1.setMinWidth(80);
		col2.setMinWidth(200);
		col3.setMinWidth(123);
		col4.setMinWidth(122);
		col5.setMinWidth(122);
		
		//add ke table pakai colom
		watchTable.getColumns().addAll(col1, col2, col3, col4, col5);
		
		bPane1.setTop(watchTable);
		bPane1.setAlignment(watchTable, Pos.TOP_CENTER);
		
		col1.getCellData(0);
	}
	
	public void setTableCart() {
		cartTable = new TableView<>();
		cartlist = new Vector<>();
		TableColumn<Cart, Integer> col1 = new TableColumn<Cart, Integer>("User ID");
		TableColumn<Cart, Integer> col2 = new TableColumn<Cart, Integer>("Watch ID");
		TableColumn<Cart, Integer>col3 = new TableColumn<Cart, Integer>("Quantity");

		
		col1.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("UserID"));
		col2.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("WatchID"));
		col3.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("Quantity"));
		
		cartTable.setMaxSize(650, 250);
		col1.setMinWidth(650/3);
		col2.setMinWidth(650/3);
		col3.setMinWidth(650/3);
		
		//add ke table pakai colom
		cartTable.getColumns().addAll(col1,col2,col3);
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
//				PreparedStatement ps = db.prepareStatement(querybrand);
//				ResultSet rs2 = ps.executeQuery();
//				String test = rs2.getString("brandName");
//				System.out.println(test);
				String watchbrand = "";
				if(rsb.next()) {
					watchbrand = rsb.getString("brandName");					
				}
				System.out.println(watchbrand);
				
				Watch watch = new Watch(watchid, watchname, watchbrand, watchprice, watchstock);
				watchlist.add(watch);
				rsb.close();
			}
			rs.close();
			
			rs = db.executeQuery("SELECT * FROM `cart`");
			while(rs.next()) {
				int watchid = rs.getInt("WatchID");
				int customerid = rs.getInt("UserID");
				int quantity = rs.getInt("Quantity");
				
				Cart cart = new Cart(customerid,watchid,quantity);
				cartlist.add(cart);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectTable() {
		watchTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Watch>() {

			@Override
			public void changed(ObservableValue<? extends Watch> observable, Watch oldValue, Watch newValue) {
				// TODO Auto-generated method stub
				if (newValue != null) {
					selectWatchLbl.setText("Selected Watch: " + newValue.getWatchName());
					
					watchId = newValue.getWatchID();
				}
			}
		});
	}
	
	public void refreshTable() {
		watchlist.clear();
		cartlist.clear();
		getData();
		ObservableList<Watch> watchObs = FXCollections.observableArrayList(watchlist);
		watchTable.setItems(watchObs);
		ObservableList<Cart> cartObs = FXCollections.observableArrayList(cartlist);
		cartTable.setItems(cartObs);
//		System.out.println(watchlist.get(1).getWatchID());
	}
	
	public void addWatch() {
		addWatchToCartBtn.setOnMouseClicked((event)->{
			Cart cart = new Cart(1,watchId,quantitySp.getValue());
			cartlist.add(cart);
			ObservableList<Cart> cartObs = FXCollections.observableArrayList(cartlist);
			cartTable.setItems(cartObs);
			
//			String query = String.format("INSERT INTO `cart`(`UserID`, `WatchID`, `Quantity`) VALUES ('%d','%d','%d')",1,watchId,quantitySp.getValue());
//			db.executeUpdate(query);
		});
	}
	
	public void init() {
		
		
		bPane1 = new BorderPane();
		bPanequan = new BorderPane();
		
		gPane = new GridPane();
		bottomBtn = new FlowPane();
		QuanPane = new FlowPane();
		
//		buyproductWindow = new jfxtras.labs.scene.control.window.Window("Buy Product");
		
		setTableWatch();
		setTableCart();
		
		
		
//		watch namenya nanti ganti ke get text		
		selectWatchLbl = new Label("Selected Watch: None");
//		bPanequan.setTop(selectWatchLbl);
		
		quantityLbl = new Label("Quantity: ");
//		bPanequan.setCenter(quantityLbl);
		QuanPane.getChildren().add(quantityLbl);
//		gPane.add(quantityLbl, 0, 0);
		
		quantitySp = new Spinner<>(0, 100, 0, 1);
//		bPanequan.setCenter(quantitySp);
		QuanPane.getChildren().add(quantitySp);
//		gPane.add(quantitySp, 1, 0);
		
		addWatchToCartBtn = new Button("Add Watch To Cart");
//		bPanequan.setCenter(addWatchToCartBtn);
		QuanPane.getChildren().add(addWatchToCartBtn);
//		gPane.add(addWatchToCartBtn, 2, 0);
		
		QuanPane.setHgap(10);
		gPane.setAlignment(Pos.CENTER);
		
		clearCartBtn = new Button("Clear Cart");
		bottomBtn.getChildren().add(clearCartBtn);
		
		checkOutBtn = new Button("Checkout");
		bottomBtn.getChildren().add(checkOutBtn);
		
		bottomBtn.setHgap(15);
		bottomBtn.setPadding(new Insets(8, 0, 0, 0));
		
		gPane.add(selectWatchLbl, 0, 1);
		
		gPane.add(QuanPane, 1, 2);
		
		bPanequan.setCenter(gPane);
		
		bPanequan.setBottom(cartTable);
		bPanequan.setAlignment(cartTable, Pos.CENTER);
		
		bPane1.setCenter(bPanequan);

		bPane1.setBottom(bottomBtn);
		
		bPane1.setPadding(new Insets(8, 8, 8, 8));

		
		bottomBtn.setAlignment(Pos.BOTTOM_CENTER);

//		buyproductWindow.getRightIcons().add(new CloseIcon(buyproductWindow));
//		buyproductWindow.getContentPane().getChildren().add(bPane1);
		
		scene = new Scene(bPane1, 750, 700);
		
	}
	
	public void AlertInformation(String content) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setHeaderText("Message");
		info.setContentText("Data has been successfully added");
		info.show();
	}
	
	public void AlertConformation(String content) {
		Alert confor = new Alert(AlertType.CONFIRMATION);
		confor.setContentText(content);
		confor.show();
	}
	
	public void AlertError(String content) {
		Alert error = new Alert(AlertType.ERROR);
		error.setHeaderText("Error");
		error.setContentText(content);
		error.show();
	}
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		refreshTable();
		selectTable();
		addWatch();
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
