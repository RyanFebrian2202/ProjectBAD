package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
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
		scene = new Scene(bPane,600,600);
		
		logOutMI.setOnAction((event) -> {
			System.out.println("Balik ke Login");
		});
		
//		BorderPane buyProductPage = new BuyProductForm();
		
		buyWatchMI.setOnAction((event) -> {
			System.out.println("asd");
//			bPane.setCenter(buyProductPage);
			
		});
	}
	
	public void showCustomerPage() {
		initialize();
		
		myTransactionHistoryMI.setOnAction((event) -> {
			System.out.println("Masuk ke manage brand tpi masih di dlaam main form");
		});
		
		logOutMI.setOnAction((event)->{
			LoginForm.setUser(null);
			LoginForm lf = LoginForm.getInstance();
			lf.showLogin();
		});
		
		Main.changeScene(scene, "Customer Main");
	}

	

}
