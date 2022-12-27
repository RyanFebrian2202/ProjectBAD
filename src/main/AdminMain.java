package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminMain extends Application{

	Scene scene;
	
	BorderPane bPane;
	
	MenuBar menuBar;
	Menu userMenu, managementMenu;
	MenuItem logOutMI, manageProductMI, manageBrandMI; 
	
	public void initialize() {
		bPane = new BorderPane();
		
		menuBar = new MenuBar();
		userMenu = new Menu("User");
		managementMenu = new Menu("Management");
		logOutMI = new MenuItem("Logout");
		manageProductMI = new MenuItem("Manage Product");
		manageBrandMI =  new MenuItem("Manage Brand");
		
//		//add menu ke menubar
		menuBar.getMenus().add(userMenu);
		menuBar.getMenus().add(managementMenu);
		
//		//add menu item ke menu
		userMenu.getItems().add(logOutMI);
		managementMenu.getItems().add(manageProductMI);
		managementMenu.getItems().add(manageBrandMI);
		
		
		
		bPane.setTop(menuBar);
		scene = new Scene(bPane,500,500);
		
		logOutMI.setOnAction((event) -> {
			System.out.println("Balik ke Login");
		});
		
		manageProductMI.setOnAction((event) -> {
			System.out.println("Masuk ke manage product tpi masih di dlaam main form");
		});
		
		manageBrandMI.setOnAction((event) -> {
			System.out.println("Masuk ke manage brand tpi masih di dlaam main form");
		});
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Main Page");
		primaryStage.show();
		
	}

	

}
