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
import model.HeaderTransaction;
import model.Watch;

public class ViewTransactionHistoryForm extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{

//		Label selectedTransactionLbl = new Label("Selected Transaction: " + );

		// Codingan untuk Table View Transaction Header
		TableView tableView1 = new TableView<HeaderTransaction>();
		
		TableColumn<HeaderTransaction, String> column1 = new TableColumn<>("Transaction ID");
		column1.setMinWidth(333);
		column1.setCellValueFactory(new PropertyValueFactory<HeaderTransaction, String>("transactionID"));

		TableColumn<HeaderTransaction, String> column2 = new TableColumn<>("User ID");
		column2.setMinWidth(333);
		column2.setCellValueFactory(new PropertyValueFactory<HeaderTransaction, String>("userID"));

		TableColumn<HeaderTransaction, String> column3 = new TableColumn<>("Transaction Date");
		column3.setMinWidth(333);
		column3.setCellValueFactory(new PropertyValueFactory<HeaderTransaction, String>("transactionDate"));

		tableView1.getColumns().add(column1);
		tableView1.getColumns().add(column2);
		tableView1.getColumns().add(column3);
		
		// Biar column nya ga bisa di resize
		tableView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView1.getItems().add(new HeaderTransaction("1", "2", "2022-05-25"));
		tableView1.getItems().add(new HeaderTransaction("2", "2", "2022-05-25"));
		tableView1.getItems().add(new HeaderTransaction("3", "2", "2022-05-25"));
		tableView1.getItems().add(new HeaderTransaction("4", "2", "2022-05-28"));
		tableView1.getItems().add(new HeaderTransaction("5", "2", "2022-05-28"));
		tableView1.getItems().add(new HeaderTransaction("6", "2", "2022-05-28"));

		VBox vbox = new VBox(tableView1);
		
		
		// Codingan untuk Selected Transaction
		FlowPane centerPane = new FlowPane();
		centerPane.setPadding(new Insets(10, 0, 10, 7));
//		centerPane.getChildren().add(selectedTransactionLbl);
		centerPane.setAlignment(Pos.CENTER_LEFT);
		
		
		// Codingan untuk Table View Transaction Detail
		int quantity = 0;
		int subTotal = 0;
		TableView tableView2 = new TableView();
		
		TableColumn<HeaderTransaction, String> column4 = new TableColumn<>("Transaction ID");
		column4.setMinWidth(142);
		column4.setCellValueFactory(new PropertyValueFactory<HeaderTransaction, String>("transactionId"));
		
		TableColumn<Watch, String> column5 = new TableColumn<>("Watch ID");
		column5.setMinWidth(142);
		column5.setCellValueFactory(new PropertyValueFactory<Watch, String>("watchId"));
		
		TableColumn<Watch, String> column6 = new TableColumn<Watch, String>("Watch Name");
		column6.setMinWidth(143);
		column6.setCellValueFactory(new PropertyValueFactory<Watch, String>("watchName"));
		
		TableColumn<Watch, String> column7 = new TableColumn<Watch, String>("Watch Brand");
		column7.setMinWidth(142);
		column7.setCellValueFactory(new PropertyValueFactory<>("watchBrand"));
		
		TableColumn<Watch, Integer> column8 = new TableColumn<>("Watch Price");
		column8.setMinWidth(142);
		column8.setCellValueFactory(new PropertyValueFactory<Watch, Integer>("watchPrice"));
		
		// blm tau hrs ambil data dari class mana
		TableColumn column9 = new TableColumn<>("Quantity");
		column9.setMinWidth(142);
		column9.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		// blm tau hrs ambil data dari class mana
		TableColumn column10 = new TableColumn<>("Sub Total");
		column10.setMinWidth(143);
		column10.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
		
		tableView2.getColumns().add(column4);
		tableView2.getColumns().add(column5);
		tableView2.getColumns().add(column6);
		tableView2.getColumns().add(column7);
		tableView2.getColumns().add(column8);
		tableView2.getColumns().add(column9);
		tableView2.getColumns().add(column10);
		
		// Biar column nya ga bisa di resize
		tableView2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		VBox vbox2 = new VBox(tableView2);
		
		
		// Codingan untuk atur posisi tampilan form
		BorderPane mainPane = new BorderPane();
		mainPane.setTop(tableView1);
		mainPane.setCenter(centerPane);
		mainPane.setBottom(tableView2);

		
		Scene scene = new Scene(vbox);
		Scene scene2 = new Scene(vbox2);
		Scene scene3 = new Scene(mainPane, 999, 700);
		primaryStage.setTitle("View Transaction History");
		primaryStage.setScene(scene);
		primaryStage.setScene(scene2);
		primaryStage.setScene(scene3);
		primaryStage.show();
	}

}
