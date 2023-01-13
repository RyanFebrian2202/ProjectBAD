package main;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage {
	private static MainStage instance;
	private Stage stage;
	
	public static MainStage getInstance() {
		if (instance == null) {
			instance = new MainStage();
		}
		return instance;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setScene(Scene scene) {
		this.stage.setScene(scene);
	}

	public void setTitle(String title) {
		this.stage.setTitle(title);
	}
	
	public void show() {
		this.stage.show();
	}
	
}
