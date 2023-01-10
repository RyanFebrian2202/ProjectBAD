package main;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import model.Watch;

public class ViewTransactionHistoryForm extends Application {
	
	Scene scene;
	BorderPane bPane;
	FlowPane fPane;
	
	Label selectedTransactionLbl;
	
	TableView<TransactionHistory> tableView1;
	TableView<Watch> tableView2;
	
	int quantity = 0;
	int subTotal = 0;
	int transactionId = 0;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void initialize() {
		
		bPane = new BorderPane();
		fPane = new FlowPane();
		
		selectedTransactionLbl = new Label("Selected Transaction: None");
		
		scene = new Scene(bPane, 999, 700);
				
	}
	
	public void arrangeComponent() {
		// Codingan untuk Table View Transaction Header
		tableView1 = new TableView<>();
		TableColumn<TransactionHistory, String> column1 = new TableColumn<>("Transaction ID");
		column1.setMinWidth(333);
		column1.setCellValueFactory(new PropertyValueFactory<TransactionHistory, String>("transactionId"));

		TableColumn<TransactionHistory, String> column2 = new TableColumn<>("User ID");
		column2.setMinWidth(333);
		column2.setCellValueFactory(new PropertyValueFactory<TransactionHistory, String>("userId"));

		TableColumn<TransactionHistory, String> column3 = new TableColumn<>("Transaction Date");
		column3.setMinWidth(333);
		column3.setCellValueFactory(new PropertyValueFactory<TransactionHistory, String>("transactionDate"));

		tableView1.getColumns().add(column1);
		tableView1.getColumns().add(column2);
		tableView1.getColumns().add(column3);
		
		tableView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Biar column nya ga bisa di resize

		tableView1.getItems().add(new TransactionHistory(1, 2, "2022-05-25"));
		tableView1.getItems().add(new TransactionHistory(2, 2, "2022-05-25"));
		tableView1.getItems().add(new TransactionHistory(3, 2, "2022-05-25"));
		tableView1.getItems().add(new TransactionHistory(4, 2, "2022-05-28"));
		tableView1.getItems().add(new TransactionHistory(5, 2, "2022-05-28"));
		tableView1.getItems().add(new TransactionHistory(6, 2, "2022-05-28"));

		VBox vbox = new VBox(tableView1);
		
		// Codingan untuk Selected Transaction
		fPane.setPadding(new Insets(10, 0, 10, 7));
		fPane.getChildren().add(selectedTransactionLbl);
		fPane.setAlignment(Pos.CENTER_LEFT);
		
		// Codingan untuk Table View Transaction Detail
		tableView2 = new TableView<>();
		
		TableColumn<TransactionHistory, String> column4 = new TableColumn<>("Transaction ID");
		column4.setMinWidth(142);
		column4.setCellValueFactory(new PropertyValueFactory<TransactionHistory, String>("transactionId"));
		
		TableColumn<Watch, String> column5 = new TableColumn<>("Watch ID");
		column5.setMinWidth(142);
		column5.setCellValueFactory(new PropertyValueFactory<Watch, String>("WatchID"));
		
		TableColumn<Watch, String> column6 = new TableColumn<Watch, String>("watchName");
		column6.setMinWidth(143);
		column6.setCellValueFactory(new PropertyValueFactory<Watch, String>("WatchName"));
		
		TableColumn<Watch, String> column7 = new TableColumn<Watch, String>("Watch Brand");
		column7.setMinWidth(142);
		column7.setCellValueFactory(new PropertyValueFactory<>("WatchBrand"));
		
		TableColumn<Watch, Integer> column8 = new TableColumn<>("Watch Price");
		column8.setMinWidth(142);
		column8.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("WatchPrice"));
		
		// blm tau hrs ambil data dari class mana
		TableColumn column9 = new TableColumn<>("Quantity");
		column9.setMinWidth(142);
		column9.setCellValueFactory(new PropertyValueFactory<>("quantity"));
				
		// blm tau hrs ambil data dari class mana
		TableColumn column10 = new TableColumn<>("Sub Total");
		column10.setMinWidth(143);
		column10.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
		
		tableView2.getColumns().add(column4); // Ini error karena gua harus ambil data dari 2 kelas yang beda. Dari kelas Watch dan Transaction History. Sedangkan tableView2 itu gua cuma bisa ambil data dari kelas Watch.
		tableView2.getColumns().add(column5);
		tableView2.getColumns().add(column6);
		tableView2.getColumns().add(column7);
		tableView2.getColumns().add(column8);
		tableView2.getColumns().add(column9);
		tableView2.getColumns().add(column10);
		
		// Biar column nya ga bisa di resize
		tableView2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
				
		VBox vbox2 = new VBox(tableView2);
				
		// Atur layout utama
		bPane.setTop(tableView1);
		bPane.setCenter(fPane);
		bPane.setBottom(tableView2);
	}
	
	public void selectTable() {
		tableView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TransactionHistory>() {
			@Override
			public void changed(ObservableValue<? extends TransactionHistory> observable, TransactionHistory oldValue, TransactionHistory newValue) {
				if (newValue != null) {
					selectedTransactionLbl.setText("Selected Watch: " + "Transaction" + newValue.getTransactionId());
					transactionId = newValue.getTransactionId();
				}	
			}
		});
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		initialize();
		arrangeComponent();
		selectTable();
		primaryStage.setScene(scene);
		primaryStage.setTitle("View Transaction History");
		primaryStage.show();
		
	}

}
