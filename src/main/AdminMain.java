package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class AdminMain{

	private static AdminMain instance;
	
	Scene scene;
	BorderPane bPane;
	
	MenuBar menuBar;
	Menu userMenu, managementMenu;
	MenuItem logOutMI, manageProductMI, manageBrandMI; 
	
	public static AdminMain getInsance() {
		if (instance == null) {
			instance = new AdminMain();
		}
		return instance;
	}
	
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
		scene = new Scene(bPane,800,500);
		
		logOutMI.setOnAction((event) -> {
			System.out.println("Balik ke Login");
		});
		
		manageBrandMI.setOnAction((event) -> {
			System.out.println("Masuk ke manage brand tpi masih di dlaam main form");
		});
		
	}
	
	public void showPage() {
		initialize();
		
		manageBrandMI.setOnAction((event)->{
			ManageBrandForm mbf = ManageBrandForm.getInstance();
			bPane.setCenter(mbf.getWindow());
		});
		
//		manageProductMI.setOnAction((event) -> {
//			System.out.println("Masuk ke manage product tpi masih di dlaam main form");
//			ManageProductForm mpf = new ManageProductForm();
//			try {
//				mpf.start(primaryStage);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//		
//		logOutMI.setOnAction((event) -> {
//			System.out.println("Balik ke Login");
//			LoginRegisForm lrf = new LoginRegisForm();
//			try {
//				lrf.start(primaryStage);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
		
		logOutMI.setOnAction((event) -> {
			LoginForm.setUser(null);
			LoginForm lf = LoginForm.getInstance();
			lf.showLogin();
		});
		
		Main.changeScene(scene, "Admin Main");
	}
}
