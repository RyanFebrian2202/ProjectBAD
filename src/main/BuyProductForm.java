package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;
import model.Cart;
import model.User;
import model.Watch;

public class BuyProductForm{
	
	private static BuyProductForm instance;


	Scene scene;
	BorderPane bPane1, bPanequan;
	GridPane gPane;
	FlowPane bottomBtn;
	Window buyproductWindow;
	
	Label selectWatchLbl, quantityLbl, watchNameLbl;
	Button addWatchToCartBtn, clearCartBtn, checkOutBtn;
	Spinner<Integer> quantitySp;
	
	TableView<Watch> watchTable;
	TableView<Cart> cartTable;
	Vector<Watch> watchlist;
	Vector<Cart> cartlist;
	Database db = Database.getConnection();
	
	int watchId;
	int userID = LoginForm.getUser().getUserID();
	
	public static BuyProductForm getInstance() {
		if (instance == null) {
			instance = new BuyProductForm();
		}
		return instance;
	}
	
	public void setTableWatch() {
		watchTable = new TableView<>();
		watchlist = new Vector<>();
		TableColumn<Watch, Integer> col1 = new TableColumn<Watch, Integer>("Watch ID");
		TableColumn<Watch, String> col2 = new TableColumn<Watch, String>("Watch Name");
		TableColumn<Watch, Integer> col3 = new TableColumn<Watch, Integer>("Watch Brand");
		TableColumn<Watch, Integer> col4 = new TableColumn<Watch, Integer>("Watch Price");
		TableColumn<Watch, Integer> col5 = new TableColumn<Watch, Integer>("Watch Stock");
		
		col1.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("WatchID"));
		col2.setCellValueFactory(new PropertyValueFactory<Watch, String>("WatchName"));
		col3.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("WatchBrand"));
		col4.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("WatchPrice"));
		col5.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("WatchStock"));
		
		watchTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	
		watchTable.setMaxHeight(250);
		watchTable.setMinHeight(250);
//		set minimal ukuran kolom
		col1.setMinWidth(999/5);
		col2.setMinWidth(999/5);
		col3.setMinWidth(999/5);
		col4.setMinWidth(999/5);
		col5.setMinWidth(999/5);
		
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
		TableColumn<Cart, Integer> col3 = new TableColumn<Cart, Integer>("Quantity");

		
		col1.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("UserID"));
		col2.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("WatchID"));
		col3.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("Quantity"));
		
//		cartTable.setMaxSize(650, 250);
		cartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		col1.setMinWidth(999/3);
		col2.setMinWidth(999/3);
		col3.setMinWidth(999/3);
		
		cartTable.setMaxHeight(250);
		cartTable.setMinHeight(250);
		
		//add ke table pakai colom
		cartTable.getColumns().addAll(col1,col2,col3);
	}
	
public void init() {
		
		
		bPane1 = new BorderPane();
		bPanequan = new BorderPane();
		
		gPane = new GridPane();
		bottomBtn = new FlowPane();

		buyproductWindow = new Window("Buy Product");
		
		setTableWatch();
		setTableCart();
				
		selectWatchLbl = new Label("Selected Watch: None");

		
		quantityLbl = new Label("Quantity: ");

		quantitySp = new Spinner<>(0, 100, 0, 1);

		addWatchToCartBtn = new Button("Add Watch To Cart");
		
		
		gPane.setAlignment(Pos.CENTER);
		
		clearCartBtn = new Button("Clear Cart");
		bottomBtn.getChildren().add(clearCartBtn);
		
		checkOutBtn = new Button("Checkout");
		bottomBtn.getChildren().add(checkOutBtn);
		
		bottomBtn.setHgap(15);
		bottomBtn.setPadding(new Insets(8, 0, 0, 0));
		
		gPane.add(selectWatchLbl, 0, 1);
		gPane.add(quantityLbl, 1, 2);
		gPane.add(quantitySp, 2, 2);
		gPane.add(addWatchToCartBtn, 3, 2);
		gPane.setHgap(7);

//		gPane.add(QuanPane, 1, 2);
		
		bPanequan.setCenter(gPane);
		
		bPanequan.setBottom(cartTable);
		bPanequan.setAlignment(cartTable, Pos.CENTER);
		
		bPane1.setCenter(bPanequan);

		bPane1.setBottom(bottomBtn);
		
		bPane1.setPadding(new Insets(8, 8, 8, 8));

		
		bottomBtn.setAlignment(Pos.BOTTOM_CENTER);

		buyproductWindow.getRightIcons().add(new CloseIcon(buyproductWindow));
		buyproductWindow.getContentPane().getChildren().add(bPane1);

//		scene = new Scene(buyproductWindow, 800, 700);
		
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
				
				Watch watch = new Watch(watchid, watchname, watchbrand, watchprice, watchstock);
				watchlist.add(watch);
				rsb.close();
			}
			rs.close();
			
			rs = db.executeQuery("SELECT * FROM `cart` WHERE UserID = " + userID);
			while(rs.next()) {
				int watchid = rs.getInt("WatchID");
				int quantity = rs.getInt("Quantity");
				
				Cart cart = new Cart(userID,watchid,quantity);
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
			if (watchId < 1) {
				AlertError("You must select the product!");
			}else if (quantitySp.getValue() == 0) {
				AlertError("You must input quantity more than 0!");
			}else {
				String query = String.format("INSERT INTO `cart`(`UserID`, `WatchID`, `Quantity`) VALUES ('%d','%d','%d')",userID,watchId,quantitySp.getValue());
				db.executeUpdate(query);
				quantitySp.getValueFactory().setValue(0);
				selectWatchLbl.setText("Selected Watch: None");
				watchId = 0;
				refreshTable();
			}
		});
	}
	
	
	
	public void AlertInformation(String content) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setHeaderText("Message");
		info.setContentText(content);
		info.showAndWait();
	}
	
	public void AlertError(String content) {

		Alert error = new Alert(AlertType.ERROR);
		error.setHeaderText("Error");
		error.setContentText(content);
		error.showAndWait();
	}
	
	public void Checkout() {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		now = Calendar.getInstance().getTime();
		sdf.format(now);
		java.sql.Date sqlDate = new java.sql.Date(now.getTime());
//		System.out.println(now);
		
		String query = "INSERT INTO `headertransaction` (`UserID`, `TransactionDate`) VALUES (?,?)";
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setInt(1, LoginForm.getUser().getUserID());
			ps.setDate(2, sqlDate);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int transactionID = 0;
		String transactionQuery = String.format("SELECT TransactionID FROM `headertransaction` WHERE UserID = %d ORDER BY TransactionID DESC LIMIT 1",userID);
		ResultSet rs = db.executeQuery(transactionQuery);
		try {
			while(rs.next()){
				transactionID = rs.getInt("TransactionID");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
		for(int i = 0; i<cartlist.size();i++) {
			
			String queryDetail = String.format("INSERT INTO `detailtransaction` " + "(`TransactionID`, `WatchID`, `Quantity`) " + "VALUES ('%d','%d','%d')",transactionID,cartlist.get(i).getWatchID(),cartlist.get(i).getQuantity() );
			db.executeUpdate(queryDetail);
		}
		
		String queryDelete = "DELETE FROM `cart` WHERE UserID = " + userID;
		db.executeUpdate(queryDelete);
	}
		
	public Window getBuyWindow() {
		init();
		refreshTable();
		selectTable();
		addWatch();
		
		clearCartBtn.setOnMouseClicked(e -> {
			Alert conforclear = new Alert(AlertType.CONFIRMATION);
			conforclear.setContentText("Are you sure to clear cart?");
			conforclear.showAndWait().ifPresent(respone -> {
				if (respone == ButtonType.OK) {
					String queryDelete = "DELETE FROM `cart` WHERE UserID = " + userID;
					db.executeUpdate(queryDelete);
					refreshTable();
				}
			});
			
		});
		
		checkOutBtn.setOnMouseClicked(e -> {
			Alert conforcheckout = new Alert(AlertType.CONFIRMATION);
			conforcheckout.setContentText("Are you sure want to checkout?");
			conforcheckout.showAndWait().ifPresent(respone -> {
				if (respone == ButtonType.OK) {
					Alert info = new Alert(AlertType.INFORMATION);
					info.setHeaderText("Message");
					info.setContentText("Checkout successful!");
					
//					Masukin data cart ke transaction
					Checkout();
					
//					hapus cart
					refreshTable();
					info.showAndWait();
				}
			});
		});
		
		return buyproductWindow;
	}
}
