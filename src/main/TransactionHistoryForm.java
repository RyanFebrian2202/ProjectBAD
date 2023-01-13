package main;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;
import model.Cart;
import model.DetailTransaction;
import model.HeaderTransaction;
import model.Watch;

public class TransactionHistoryForm {
	
	private static TransactionHistoryForm instance;
	
//	Scene scene;
	BorderPane bPane;
	FlowPane fPane;
	
	Label selectedTransactionLbl;
	
	TableView<HeaderTransaction> tableView1;
	TableView<DetailTransaction> tableView2;
	
	Database db = Database.getConnection();
	Window transactionHistoryWindow;
	
	Vector<HeaderTransaction> transactionList = new Vector<HeaderTransaction>();
	Vector<DetailTransaction> transactionDetailList = new Vector<DetailTransaction>();
	Vector<Watch> watchList = new Vector<Watch>();
	
	int transactionID = 0;
	
	public static TransactionHistoryForm getInstance() {
		if(instance == null) {
			instance = new TransactionHistoryForm();
		}
		
		return instance;
	}

	public void initialize() {
		
		bPane = new BorderPane();
		fPane = new FlowPane();
		
		selectedTransactionLbl = new Label("Selected Transaction: None");
		
//		scene = new Scene(bPane, 999, 700);
		transactionHistoryWindow = new Window("Transaction History");
		transactionHistoryWindow.getRightIcons().add(new CloseIcon(transactionHistoryWindow));
		transactionHistoryWindow.getContentPane().getChildren().add(bPane);
				
	}
	
	public void arrangeComponent() {
		// Codingan untuk Table View Transaction Header
		tableView1 = new TableView<HeaderTransaction>();
		TableColumn<HeaderTransaction, Integer> column1 = new TableColumn<>("Transaction ID");
		column1.setMinWidth(130);
		column1.setCellValueFactory(new PropertyValueFactory<HeaderTransaction, Integer>("TransactionID"));

		TableColumn<HeaderTransaction, Integer> column2 = new TableColumn<>("User ID");
		column2.setMinWidth(120);
		column2.setCellValueFactory(new PropertyValueFactory<HeaderTransaction, Integer>("UserID"));

		TableColumn<HeaderTransaction, Date> column3 = new TableColumn<>("Transaction Date");
		column3.setMinWidth(170);
		column3.setCellValueFactory(new PropertyValueFactory<HeaderTransaction, Date>("TransactionDate"));

		tableView1.getColumns().add(column1);
		tableView1.getColumns().add(column2);
		tableView1.getColumns().add(column3);
		
		tableView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Biar column nya ga bisa di resize
		tableView1.setPadding(new Insets(10, 10, 10, 10));
		tableView1.setMinHeight(250);
		tableView1.setMaxHeight(250);	

		VBox vbox = new VBox(tableView1);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		
		// Codingan untuk Selected Transaction
		fPane.setPadding(new Insets(10, 0, 10, 0));
		fPane.getChildren().add(selectedTransactionLbl);
		fPane.setAlignment(Pos.CENTER_LEFT);
		
		// Codingan untuk Table View Transaction Detail
		tableView2 = new TableView<>();
		
		TableColumn<DetailTransaction, Integer> column4 = new TableColumn<>("Transaction ID");
		column4.setMinWidth(142);
		column4.setCellValueFactory(new PropertyValueFactory<DetailTransaction, Integer>("TransactionID"));
		
		TableColumn<DetailTransaction, Integer> column5 = new TableColumn<>("Watch ID");
		column5.setMinWidth(142);
		column5.setCellValueFactory(new PropertyValueFactory<DetailTransaction, Integer>("WatchID"));
		
		TableColumn<DetailTransaction, String> column6 = new TableColumn<DetailTransaction, String>("Watch Name");
		column6.setMinWidth(170);
		column6.setCellValueFactory(new PropertyValueFactory<DetailTransaction, String>("WatchName"));
		
		TableColumn<DetailTransaction, String> column7 = new TableColumn<DetailTransaction, String>("Watch Brand");
		column7.setMinWidth(142);
		column7.setCellValueFactory(new PropertyValueFactory<DetailTransaction, String>("WatchBrand"));
		
		TableColumn<DetailTransaction, String> column8 = new TableColumn<DetailTransaction, String>("Watch Price");
		column8.setMinWidth(142);
		column8.setCellValueFactory(new PropertyValueFactory<DetailTransaction, String>("WatchPrice"));
		
		// blm tau hrs ambil data dari class mana
		TableColumn<DetailTransaction, Integer> column9 = new TableColumn<DetailTransaction, Integer>("Quantity");
		column9.setMinWidth(142);
		column9.setCellValueFactory(new PropertyValueFactory<DetailTransaction, Integer>("Quantity"));
				
		// blm tau hrs ambil data dari class mana
		TableColumn<DetailTransaction, String> column10 = new TableColumn<DetailTransaction, String>("Sub Total");
		column10.setMinWidth(143);
		column10.setCellValueFactory(new PropertyValueFactory<DetailTransaction, String>("SubTotal"));
		
		tableView2.getColumns().addAll(column4,column5,column6,column7,column8,column9,column10); // Ini error karena gua harus ambil data dari 2 kelas yang beda. Dari kelas Watch dan Transaction History. Sedangkan tableView2 itu gua cuma bisa ambil data dari kelas Watch.
		
		// Biar column nya ga bisa di resize
		tableView2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableView2.setPadding(new Insets(10, 10, 10, 10));

		// Atur layout utama
		bPane.setTop(tableView1);
		bPane.setAlignment(tableView1, Pos.CENTER);
		bPane.setCenter(fPane);
		bPane.setBottom(tableView2);
		bPane.setAlignment(tableView2, Pos.CENTER);
	}
	
	public void getWatchData() {
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
					watchbrand = rsb.getString("brandName");					
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
	
	public void getData() {
		getWatchData();
		LoginForm.getInstance();
		int userID = LoginForm.getUser().getUserID();
		String query = "SELECT * FROM `headertransaction` WHERE UserID = " + userID;
		ResultSet rs = db.executeQuery(query);
		
		try {
			while(rs.next()) {
				int transactionId = rs.getInt("TransactionID");
				Date transactionDate = new Date(); 
				transactionDate = rs.getDate("TransactionDate");
				
				HeaderTransaction transactionHead = new HeaderTransaction(transactionId, userID, transactionDate);						
				transactionList.add(transactionHead);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refreshTable() {
		transactionList.clear();
		watchList.clear();
		getData();
		ObservableList<HeaderTransaction> transactionObs = FXCollections.observableArrayList(transactionList);
		tableView1.setItems(transactionObs);
	}
	
	public void selectTable() {
		tableView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HeaderTransaction>() {
			@Override
			public void changed(ObservableValue<? extends HeaderTransaction> observable, HeaderTransaction oldValue, HeaderTransaction newValue) {
				if (newValue != null) {
					transactionDetailList.clear();
					transactionID = newValue.getTransactionID();
					selectedTransactionLbl.setText("Selected Transaction: " + "Transaction " + transactionID);
					
					String queryDetail = String.format("SELECT * FROM `detailtransaction` WHERE TransactionID = %d",transactionID);
					ResultSet rs = db.executeQuery(queryDetail);
					
					try {
						while(rs.next()) {
							int watchID = rs.getInt("WatchID");
							int quantity = rs.getInt("Quantity");
							String watchName = null;
							String watchBrand = null;
							String watchPrice = null;
							String subTotal = null;
							
							for (int i = 0; i<watchList.size(); i++) {
								if(watchList.get(i).getWatchID() == watchID) {
									watchName = watchList.get(i).getWatchName();
									watchBrand = watchList.get(i).getWatchBrand();
									watchPrice = "$" + Integer.toString(watchList.get(i).getWatchPrice());
									subTotal = "$" + Integer.toString(watchList.get(i).getWatchPrice() * quantity);
								}
							}
							
							DetailTransaction detailTransaction = new DetailTransaction(transactionID, watchID, quantity, watchName, watchBrand, watchPrice, subTotal);
							transactionDetailList.add(detailTransaction);
						}
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				ObservableList<DetailTransaction> detailTransactionObs = FXCollections.observableArrayList(transactionDetailList);
				tableView2.setItems(detailTransactionObs);
			}
		});
	}
	
	public Window getTransactionHistoryWindow(){
		initialize();
		arrangeComponent();
		refreshTable();
		selectTable();
		
		return transactionHistoryWindow;
	}

}
