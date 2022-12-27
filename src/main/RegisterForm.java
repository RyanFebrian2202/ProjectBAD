package main;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
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
	Scene scene;
	GridPane grid;
	StackPane stackPane;

	Label titleLbl, nameLbl, genderLbl, emailLbl, passwordLbl, confirmPasswordLbl;
	TextField emailTF, nameTF;
	RadioButton maleRB, femaleRB;
	ToggleGroup genderTG;
	PasswordField passwordPF, confirmPasswordPF;
	Button regisBtn, backToLoginBtn;
	HBox genderHbox;
	Rectangle rectangle;
	
	public void init() {
		
//      Grid Pane
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
//      Label Title
        titleLbl = new Label("Register");
        titleLbl.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        grid.add(titleLbl, 1, 0, 2, 1);

//      Label Name
        nameLbl = new Label("Name :");
        grid.add(nameLbl, 1, 1);
        
//      TextField Name
        nameTF = new TextField();
        nameTF.setPromptText("Name");
        nameTF.setMinWidth(250);
        grid.add(nameTF, 1, 2);
        
//      Label Gender
        Label gender = new Label("Gender :");
        grid.add(gender, 1, 3);
        
//      Radio Button Gender
        maleRB = new RadioButton("Male");
        femaleRB = new RadioButton("Female");
        
        genderTG = new ToggleGroup();
        
		maleRB.setToggleGroup(genderTG);
		femaleRB.setToggleGroup(genderTG);
		
		genderHbox = new HBox(15);
		genderHbox.getChildren().add(maleRB);
		genderHbox.getChildren().add(femaleRB);
        grid.add(genderHbox, 1, 4);
        
//		Label Email
		emailLbl = new Label("Email :");
        grid.add(emailLbl, 1, 5);
        
//      TextField Email
        emailTF  = new TextField();
        emailTF.setPromptText("Email Address");
        emailTF.setMinWidth(250);
        grid.add(emailTF , 1, 6);
		
//		Label Password
        passwordLbl = new Label("Password :");
        grid.add(passwordLbl, 1, 7);
        
//      Password Field Password
        passwordPF = new PasswordField();
        passwordPF.setPromptText("Password");
        passwordPF.setMinWidth(250);
        grid.add(passwordPF, 1, 8);
        
//      Label Confirm Password
        confirmPasswordLbl = new Label("Confirm Password :");
        grid.add(confirmPasswordLbl, 1, 9);
        
//      Password Field Confirm Password
        confirmPasswordPF = new PasswordField();
        confirmPasswordPF.setPromptText("Confirm Password");
        confirmPasswordPF.setMinWidth(250);
        grid.add(confirmPasswordPF, 1, 10);
        
//      Register Button
        regisBtn = new Button("Register");
        regisBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        regisBtn.setTextFill(Color.WHITE);
        regisBtn.setFont(Font.font("System", FontWeight.BOLD, 10));
        regisBtn.setMinWidth(250);
        grid.add(regisBtn, 1, 11);
        
//      Back to Login Button
        backToLoginBtn = new Button("Back to Login");
        backToLoginBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        backToLoginBtn.setTextFill(Color.WHITE);
        backToLoginBtn.setFont(Font.font("System", FontWeight.BOLD, 10));
        backToLoginBtn.setMinWidth(250);
        grid.add(backToLoginBtn, 1, 12);
        
		
		grid.setStyle("-fx-background-color: lightcyan;");
		
//      Rectangle Belakang
        rectangle = new Rectangle(400, 400);
        rectangle.setFill(Color.WHITE);
        
//      StackPane (Buat numpuk rectangle sama gridpane)
        stackPane = new StackPane();
        stackPane.getChildren().add(rectangle);
        stackPane.getChildren().add(grid);
		
        scene = new Scene(stackPane, 600, 650);        
        
	}

	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
    	init();
        stage.setTitle("Register Form");
        stage.setScene(scene);
        stage.show();
        


        // mid rectangle
//        Rectangle rectangle = new Rectangle(400,600);
//        rectangle.setFill(Color.WHITE);
//        
////        rectangle.setArcWidth(1);
//        grid.getChildren().add(rectangle);
        
        
        
        
//        final Text actiontarget = new Text();
//        grid.add(actiontarget, 1, 6);
//        
        
        // masuk registration page
//        btn1.setOnAction(event -> {
//        	try {
//        	    FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
//        	    Parent root = loader.load();
//
//        	    Scene scene = new Scene(root);
//
//        	    Stage stage = (Stage) btn1.getScene().getWindow();
//        	    stage.setScene(scene);
//        	} catch (IOException e) {
//        	    // handle the exception
//        	}
//        	
//        });
        


        // set the action for the back to login button
//        backToLoginBTN.setOnAction(event -> {
//            // navigate back to the login page
//        });
        
        
    }
}