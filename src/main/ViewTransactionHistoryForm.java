package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewTransactionHistoryForm extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{

		Label selectedTransactionLbl = new Label("Selected Transaction: ");

		// Codingan untuk Table View Transaction Header
		TableView tableView1 = new TableView();
		
		TableColumn<TransactionHistory, String> column1 = new TableColumn<>("Transaction ID");
		column1.setCellValueFactory(new PropertyValueFactory<>("transactionId"));

		TableColumn<TransactionHistory, String> column2 = new TableColumn<>("User ID");
		column2.setCellValueFactory(new PropertyValueFactory<>("userId"));

		TableColumn<TransactionHistory, String> column3 = new TableColumn<>("Transaction Date");
		column3.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));

		tableView1.getColumns().add(column1);
		tableView1.getColumns().add(column2);
		tableView1.getColumns().add(column3);

		tableView1.getItems().add(new TransactionHistory("1", "2", "2022-05-25"));
		tableView1.getItems().add(new TransactionHistory("2", "2", "2022-05-25"));
		tableView1.getItems().add(new TransactionHistory("3", "2", "2022-05-25"));
		tableView1.getItems().add(new TransactionHistory("4", "2", "2022-05-28"));
		tableView1.getItems().add(new TransactionHistory("5", "2", "2022-05-28"));
		tableView1.getItems().add(new TransactionHistory("6", "2", "2022-05-28"));

		VBox vbox = new VBox(tableView1);
		
		
		// Codingan untuk Selected Transaction
		FlowPane centerPane = new FlowPane();
		
		
		// Codingan untuk Table View Transaction Detail
		int quantity = 0;
		int subTotal = 0;
		TableView tableView2 = new TableView();
		
		TableColumn<TransactionHistory, String> column4 = new TableColumn<>("Transaction ID");
		column4.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
		
		TableColumn<Watch, String> column5 = new TableColumn<>("Watch ID");
		column5.setCellValueFactory(new PropertyValueFactory<>("watchId"));
		
		TableColumn<Watch, String> column6 = new TableColumn<>("Watch Name");
		column6.setCellValueFactory(new PropertyValueFactory<>("watchName"));
		
		TableColumn<Watch, String> column7 = new TableColumn<>("Watch Brand");
		column7.setCellValueFactory(new PropertyValueFactory<>("watchBrand"));
		
		TableColumn<Watch, Integer> column8 = new TableColumn<>("Watch Price");
		column8.setCellValueFactory(new PropertyValueFactory<>("watchPrice"));
		
		TableColumn column9 = new TableColumn<>("Quantity");
		column9.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		TableColumn<ViewTransactionHistoryForm, Integer> column10 = new TableColumn<>("Sub Total");
		column10.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
		
		tableView2.getColumns().add(column4);
		tableView2.getColumns().add(column5);
		tableView2.getColumns().add(column6);
		tableView2.getColumns().add(column7);
		tableView2.getColumns().add(column8);
		tableView2.getColumns().add(column9);
		tableView2.getColumns().add(column10);
		
		VBox vbox2 = new VBox(tableView2);
		
		// Codingan untuk atur posisi tampilan form
		BorderPane mainPane = new BorderPane();
		mainPane.setTop(tableView1);
		mainPane.setCenter(selectedTransactionLbl);
		mainPane.getAlignment(selectedTransactionLbl);
		mainPane.setAlignment(selectedTransactionLbl, Pos.CENTER_LEFT);
		mainPane.setBottom(tableView2);

		
		
		Scene scene = new Scene(vbox);
		
		Scene scene2 = new Scene(vbox2);
		
		Scene scene3 = new Scene(mainPane, 650, 600);

		primaryStage.setTitle("View Transaction History");

		primaryStage.setScene(scene);
		
		primaryStage.setScene(scene2);
		
		primaryStage.setScene(scene3);

		primaryStage.show();
	}

}

