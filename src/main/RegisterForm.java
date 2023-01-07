package main;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterForm extends Application {
	GridPane gridRegis;
	BorderPane bPaneRegis, LoginFormPage;
	StackPane stackPaneRegis;
	
	Label titleLblRegis, nameLblRegis, genderLblRegis, emailLblRegis, passwordLblRegis, confirmPasswordLblRegis;
	TextField emailTFRegis, nameTFRegis;
	RadioButton maleRBRegis, femaleRBRegis;
	ToggleGroup genderTGRegis;
	PasswordField passwordPFRegis, confirmPasswordPFRegis;
	Button regisBtn, backToLoginBtn;
	HBox genderHboxRegis;
	Scene sceneRegis;
	Rectangle rectangleRegis;
	
	public void init() {
		
//		 	Grid Pane
			gridRegis = new GridPane();
//			LoginFormPage = new LoginForm();
			gridRegis.setAlignment(Pos.CENTER);
			gridRegis.setHgap(15);
			gridRegis.setVgap(15);
	        
	        bPaneRegis = new BorderPane();
	        
//	      Label Title
	        titleLblRegis = new Label("Register");
	        titleLblRegis.setFont(Font.font("Vernada", FontWeight.BOLD, 20));
	        gridRegis.setHalignment(titleLblRegis, HPos.CENTER);
	        gridRegis.add(titleLblRegis, 0, 0);

//	      Label Name
	        nameLblRegis = new Label("Name :");
	        gridRegis.add(nameLblRegis, 0, 1);
	        
//	      TextField Name
	        nameTFRegis = new TextField();
	        nameTFRegis.setPromptText("Name");
	        nameTFRegis.setMinWidth(250);
	        gridRegis.add(nameTFRegis, 0, 2);
	        
//	      Label Gender
	        genderLblRegis = new Label("Gender :");
	        gridRegis.add(genderLblRegis, 0, 3);
	        
//	      Radio Button Gender
	        maleRBRegis = new RadioButton("Male");
	        femaleRBRegis = new RadioButton("Female");
	        
	        genderTGRegis = new ToggleGroup();
	        
	        maleRBRegis.setToggleGroup(genderTGRegis);
	        femaleRBRegis.setToggleGroup(genderTGRegis);
			
			genderHboxRegis = new HBox(15);
			genderHboxRegis.getChildren().add(maleRBRegis);
			genderHboxRegis.getChildren().add(femaleRBRegis);
			gridRegis.add(genderHboxRegis, 0, 4);
	        
//			Label Email
			emailLblRegis = new Label("Email :");
			gridRegis.add(emailLblRegis, 0, 5);
	        
//	      TextField Email
	        emailTFRegis  = new TextField();
	        emailTFRegis.setPromptText("Email Address");
	        emailTFRegis.setMinWidth(250);
	        gridRegis.add(emailTFRegis , 0, 6);
			
//			Label Password
	        passwordLblRegis = new Label("Password :");
	        gridRegis.add(passwordLblRegis, 0, 7);
	        
//	      Password Field Password
	        passwordPFRegis = new PasswordField();
	        passwordPFRegis.setPromptText("Password");
	        passwordPFRegis.setMinWidth(250);
	        gridRegis.add(passwordPFRegis, 0, 8);
	        
//	      Label Confirm Password
	        confirmPasswordLblRegis = new Label("Confirm Password :");
	        gridRegis.add(confirmPasswordLblRegis, 0, 9);
	        
//	      Password Field Confirm Password
	        confirmPasswordPFRegis = new PasswordField();
	        confirmPasswordPFRegis.setPromptText("Confirm Password");
	        confirmPasswordPFRegis.setMinWidth(250);
	        gridRegis.add(confirmPasswordPFRegis, 0, 10);
	        
//	      Register Button
	        regisBtn = new Button("Register");
	        regisBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
	        regisBtn.setTextFill(Color.WHITE);
	        regisBtn.setFont(Font.font("System", FontWeight.BOLD, 10));
	        regisBtn.setMinWidth(250);
	        gridRegis.add(regisBtn, 0, 11);
	        
//	      Back to Login Button
	        backToLoginBtn = new Button("Back to Login");
	        backToLoginBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
	        backToLoginBtn.setTextFill(Color.WHITE);
	        backToLoginBtn.setFont(Font.font("System", FontWeight.BOLD, 10));
	        backToLoginBtn.setMinWidth(250);
	        gridRegis.add(backToLoginBtn, 0, 12);

	        gridRegis.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	        
	        
	        bPaneRegis.setCenter(gridRegis);
	        bPaneRegis.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));
	        bPaneRegis.setMargin(gridRegis, new Insets (80));

	        sceneRegis = new Scene(bPaneRegis, 650, 600);
			
	        backToLoginBtn.setOnAction( (event) -> {
				System.out.println("Balik ke Login");
				bPaneRegis.setCenter(LoginFormPage);
//				stage.setTitle("Register");
			});           
        
//	        Validation
	        int atCount = emailTFRegis.getText().length() - emailTFRegis.getText().replace("@", "").length();
//	        int dotCount = emailTFRegis.getText().substring(emailTFRegis.getText().indexOf("@")).length() - emailTFRegis.getText().substring(emailTFRegis.getText().indexOf("@")).replace(".", "").length();
	        int dotCount = 1;
	        
//	        Validasi Name
	        regisBtn.setOnAction( (event) -> {
	        	if (nameTFRegis.getText().length() < 5 || nameTFRegis.getText().length() > 40) {
	  	    	  Alert alert = new Alert(Alert.AlertType.ERROR, "Name must be between 5 and 40 characters!");
	  	    	  alert.showAndWait();
	  	    	  return;
	  	    	}
	        
//	        	Validasi Gender
	        RadioButton pick = (RadioButton) genderTGRegis.getSelectedToggle();
		    	if (pick == null) {
	
		    		Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a gender!");
		    		alert.showAndWait();
		    	}
		    	
//		    	Valisadi Email
		    	String pattern = "^[a-zA-Z0-9_+&*-]+(?:\\." +
		    	                 "[a-zA-Z0-9_+&*-]+)*@" +
		    	                 "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
		    	                 "A-Z]{2,7}$";
		    	
//		    	if (!emailTFRegis.getText().matches(pattern)) {
//		    	  Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid email address!");
//		    	  alert.showAndWait();
//		    	  return;
//		    	  
//		    	}else if (emailTFRegis.getText().contains("@.") || emailTFRegis.getText().contains("..")) {
//		    		Alert alert = new Alert(Alert.AlertType.ERROR, "Character ‘@’ must not be next to ‘.’!");
//		    		  alert.showAndWait();
//		    		  return;
//				}else if (emailTFRegis.getText().contains("@.") || emailTFRegis.getText().contains("..")) {
//					Alert alert = new Alert(Alert.AlertType.ERROR, "Character ‘@’ must not be next to ‘.’!");
//		    		  alert.showAndWait();
//		    		  return;
//				}else if (emailTFRegis.getText().startsWith("@") || emailTFRegis.getText().startsWith(".") || emailTFRegis.getText().endsWith("@") || emailTFRegis.getText().endsWith(".")) {
////			    		Must not starts and ends with ‘@’ nor ‘.’.
//					Alert alert = new Alert(Alert.AlertType.ERROR, "Email must not start or end with '@' or '.'!");
//			    	  alert.showAndWait();
//			    	  return;
//				}else if (atCount != 1) {
////	    				Must contain exactly one ‘@’.
//					 Alert alert = new Alert(Alert.AlertType.ERROR, "Email must contain exactly one '@' symbol!");
//			    	  alert.showAndWait();
//			    	  return;
//				}else if (dotCount != 1) {
////	    				Must contain exactly one ‘.’ after ‘@’ for separating [provider] and “com”.
//					Alert alert = new Alert(Alert.AlertType.ERROR, "Email must contain exactly one '.' symbol after the '@' symbol!");
//			    	  alert.showAndWait();
//			    	  return;
//				}else if (!emailTFRegis.getText().endsWith(".com")) {
////	    				Must ends with ‘.com’
//					Alert alert = new Alert(Alert.AlertType.ERROR, "Email must end with '.com'!");
//			    	  alert.showAndWait();
//			    	  return;
//				}
		    	
		    	
//		    	Validasi Password
		    	if (passwordPFRegis.getText().length() < 6 || passwordPFRegis.getText().length() > 20) {
		    	  Alert alert = new Alert(Alert.AlertType.ERROR, "Password must be between 6 and 20 characters!");
		    	  alert.showAndWait();
		    	  return;
		    	}
		    	
//		    	Validasi Confirm Password
		    	if (!confirmPasswordPFRegis.getText().equals(passwordPFRegis.getText())) {
			    	  Alert alert = new Alert(Alert.AlertType.ERROR, "Password and confirm password do not match!");
			    	  alert.showAndWait();
			    	  return;
			    	}
		    	
		    	
		    	
	        });
	        
	}
	

	
//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void start(Stage stage) {
    	init();
        stage.setTitle("Register Form");
        stage.setScene(sceneRegis);
        stage.show();
        
    }
    
}