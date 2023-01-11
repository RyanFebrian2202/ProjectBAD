package loginRegis;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Login extends Application {
	
	Scene scene;
	Background bgL, bgL1, bgL2;
	BackgroundFill bfL, bfL1, bfL2;
	
	//login
	GridPane grid;
	BorderPane border;
	Label TitleLogin, emailLogin, pwLogin;
	TextField emailTextField;
	PasswordField pwLoginPF;
	Button LoginBtn, RegisInsteadBtn;
	
	
public void init() {
	
	// buat kotak putihny
	bfL = new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY);
	bfL1 = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
	bfL2 = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);

	bgL2 = new Background(bfL2);
	bgL = new Background(bfL);
	bgL1 = new Background(bfL1);
	
	// std
	grid = new GridPane();
	border = new BorderPane();
	
	
	// login
	TitleLogin = new Label("Watches Dealer Login");
	TitleLogin.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
	emailLogin = new Label("Email :");
	pwLogin = new Label("Password :");
	emailTextField = new TextField();
	emailTextField.setPromptText("Email Address");
	pwLoginPF = new PasswordField();
	pwLoginPF.setPromptText("Password");
	
	LoginBtn = new Button("Login");
	LoginBtn.setMinWidth(200);
	LoginBtn.setBackground(bgL1);
	LoginBtn.setTextFill(Color.WHITE);
	RegisInsteadBtn = new Button("Register Instead");
	RegisInsteadBtn.setMinWidth(200);
	RegisInsteadBtn.setBackground(bgL1);
	RegisInsteadBtn.setTextFill(Color.WHITE);

	
}
	
public void login() {
	
	grid.add(TitleLogin, 0, 0);
	grid.add(emailLogin, 0, 1);
	grid.add(emailTextField, 0, 2);
	grid.add(pwLogin, 0, 3);
	grid.add(pwLoginPF, 0, 4);
	grid.add(LoginBtn, 0, 5);
	grid.add(RegisInsteadBtn, 0, 6);
	
	
	grid.setAlignment(Pos.CENTER);
	grid.setVgap(20);
	grid.setHgap(20);	
	GridPane.setHalignment(TitleLogin, HPos.CENTER);
	grid.setBackground(bgL2);
	
	BorderPane.setMargin(grid, new Insets(120));

	border.setCenter(grid);
	border.setBackground(bgL);
	
}


	@Override
	public void start(Stage stage) throws Exception {
		init();		
		stage.setTitle("Login");
		login();
		scene = new Scene(border, 500, 600);
		
		
		RegisInsteadBtn.setOnAction( (event) -> {
			System.out.println("Pindah ke border Regis");
//			border.setCenter(Regis);
		});
    	
    	LoginBtn.setOnAction( (event1) -> {
    		System.out.println("Masuk APK");
    	});
		
		LoginBtn.setOnAction(event -> {
			if (emailTextField.getText().isEmpty()) {
				showErrorAlert("Email field must be filled.");
			} else if (pwLoginPF.getText().isEmpty()) {
				showErrorAlert("Password field must be filled.");
			} 
//        else {
//            // Check email + password match database
//            boolean loginSuccessful = checkData
//    }
			
			scene.setFill(Color.PALETURQUOISE);
			stage.setScene(scene);
			stage.show();
		});
		
		
		// Add Regis class ke stack pane
		Regis otherClass = new Regis();
        StackPane root = new StackPane();
        root.getChildren().add(Regis.getRoot());		
	}		
	
	private void showErrorAlert(String string) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText("Invalid credential!");
        alert.showAndWait();		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
