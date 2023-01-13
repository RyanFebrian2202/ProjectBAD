package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CustomerMain{
	
	private static CustomerMain instance;

	Scene scene;
	
	BorderPane bPane;
	
	MenuBar menuBar;
	Menu userMenu, managementMenu;
	MenuItem logOutMI, buyWatchMI, myTransactionHistoryMI; 
	
	public static CustomerMain getInstance() {
		if (instance == null) {
			instance = new CustomerMain();
		}
		
		return instance;
	}
	
	public void initialize() {
		bPane = new BorderPane();
		
		menuBar = new MenuBar();
		userMenu = new Menu("User");
		managementMenu = new Menu("Management");
		logOutMI = new MenuItem("Logout");
		buyWatchMI = new MenuItem("Buy Watch");
		myTransactionHistoryMI =  new MenuItem("My Transaction History");
		
//		//add menu ke menubar
		menuBar.getMenus().add(userMenu);
		menuBar.getMenus().add(managementMenu);
		
//		//add menu item ke menu
		userMenu.getItems().add(logOutMI);
		managementMenu.getItems().add(buyWatchMI);
		managementMenu.getItems().add(myTransactionHistoryMI);
		
		
		
		bPane.setTop(menuBar);
		bPane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
		scene = new Scene(bPane,1000,730);
		
//		BorderPane buyProductPage = new BuyProductForm();
	}
	
	public void showCustomerPage() {
		initialize();
		
		buyWatchMI.setOnAction((event)->{
			BuyProductForm bpf = BuyProductForm.getInstance();
			bPane.setCenter(bpf.getBuyWindow());
		});
		
		myTransactionHistoryMI.setOnAction((event) -> {
			TransactionHistoryForm thf = TransactionHistoryForm.getInstance();
			bPane.setCenter(thf.getTransactionHistoryWindow());
		});
		
		logOutMI.setOnAction((event)->{
			LoginForm.setUser(null);
			LoginForm lf = LoginForm.getInstance();
			lf.showLogin();
		});
		
		Main.changeScene(scene, "Customer Main");
	}

	

}
