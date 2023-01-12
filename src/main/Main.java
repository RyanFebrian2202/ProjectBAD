package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void changeScene(Scene scene, String title) {
		MainStage mainStage = MainStage.getInstance();
		mainStage.setTitle(title);
		mainStage.setScene(scene);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainStage startStage = MainStage.getInstance();
		startStage.setStage(primaryStage);
		
		LoginForm lf = LoginForm.getInstance();
		lf.showLogin();
		
		startStage.show();
	}

}
