package main;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginForm extends Application {
	
	GridPane grid;
	Stage RegisPage;
	
	Label title, email, pw;
	TextField emailTF;
	PasswordField pwPF;
	Button loginBtn, regisBtn;
	StackPane stackPane;
	Scene scene, sceneRegis;
	Rectangle rectangle;
	
	public void init() {
//		retangle blkngnnya pke stack pane
		
        
//    	Grid Pane
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
//      Label Title
        title = new Label("Watches Dealer Login");
        title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        title.setAlignment(Pos.CENTER);
        grid.add(title, 1, 0, 1, 1);
        
//      Label Email
        email = new Label("Email :");
        grid.add(email, 1, 1);

//      Email Text Field
        emailTF = new TextField();
        emailTF.setPromptText("Email Address");
        emailTF.setMinWidth(250);
        grid.add(emailTF, 1, 2);
        

//      Label Password
        pw = new Label("Password :");
        grid.add(pw, 1, 3);

//      Password Field
        pwPF = new PasswordField();
        pwPF.setPromptText("Password");
        pwPF.setMinWidth(250);
        grid.add(pwPF, 1, 4);

//      Login Button
        loginBtn = new Button("Login");
        loginBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        loginBtn.setTextFill(Color.WHITE);
        loginBtn.setFont(Font.font("System", FontWeight.BOLD, 10));
//        HBox hbBtn = new HBox(10);
//        hbBtn.setAlignment(Pos.CENTER);
//        hbBtn.getChildren().add(loginBtn);
        loginBtn.setMinWidth(250);
        loginBtn.setAlignment(Pos.CENTER);
        grid.add(loginBtn, 1, 5);
        
//      Regis Button
        regisBtn = new Button("Register Instead");
        regisBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        regisBtn.setTextFill(Color.WHITE);
        regisBtn.setFont(Font.font("System", FontWeight.BOLD, 10));
//        HBox hbBtn1 = new HBox(10);
//        hbBtn1.setAlignment(Pos.CENTER);
//        hbBtn1.getChildren().add(regisBtn);
        regisBtn.setMinWidth(250);
        regisBtn.setAlignment(Pos.CENTER);
        grid.add(regisBtn, 1, 6);
        
//        kalo ditambah backgroup kotak putihnya ilang
//        grid.setStyle("-fx-background-color: lightcyan;");
        
//      Rectangle Belakang
        rectangle = new Rectangle(400, 400);
        rectangle.setFill(Color.WHITE);
        
//      StackPane (Buat numpuk rectangle sama gridpane)
        stackPane = new StackPane();
        stackPane.getChildren().add(rectangle);
        stackPane.getChildren().add(grid);
        
//      Scene
        scene = new Scene(stackPane, 600, 650);
	}
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
    	init();	
    	
    	regisBtn.setOnAction( (event) -> {
			System.out.println("Pindah ke regis");
		});
    	
    	loginBtn.setOnAction( (event) -> {
    		System.out.println("Masuk Aplikasi");
    	});
    	
    	stage.setTitle("Login");
    	stage.setScene(scene);
    	stage.show();
    	
    	
//    	j = stage;
//        j.setMaximized(true);
//        
//        
//        Pane l1 = new Pane ();
//
//        c1 = new Scene(l1, Color.WHITE);
//
//        StackPane root = new StackPane();
//        root.setStyle("-fx-background-color:white;");
//        root.getChildren().add(l1);
//        c1 = new Scene(root);
//        
//        TextField consulta = new TextField();
//        l1.setPadding(new Insets(10));
//        l1.getChildren().add(consulta);
//        consulta.setLayoutX(50);
//        consulta.setLayoutY(110);
//
//        Rectangle r = new Rectangle(0, 0, 50, 50);
//        r.setFill(Color.WHITE);
//        l1.getChildren().addAll(r);
    	


        

        
//        BorderPane mainPane = new BorderPane();
//		BorderPane loginValidate = new Login();
//		BorderPane registerPage = new Register();
//        // ke main form
//        btn.setOnAction((event) -> {
//			mainPane.setCenter(loginValidate);
//		});
//		
//        // ke register form
//		btn1.setOnAction((event1) ->{
//			mainPane.setCenter(registerForm);
//		});
        
//     	
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
        
//        boolean field = false;
//        private boolean isFieldEmpty(String field) {
//            return field == null || field.trim().isEmpty();
//            if (isFieldEmpty(emailTextField.getText())) {
//                // show an error message indicating that the field is required
//            } else {
//                // perform further processing or validation on the input
//            }
//        }
        
        // validate data
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // validate the input and authenticate the user
//                String email = emailTextField.getText();
//                String password = pwBox.getText();
//                if (isValidEmail(email) && isValidPassword(password)) {
//                    // authenticate the user using the email and password
//                    boolean authenticated = authenticate(email, password);
//                    if (authenticated) {
//                        // navigate to the home page
//                    } else {
//                        // show an error message indicating that the email or password is incorrect
//                    }
//                } else {
//                    // show an error message indicating that the email or password is invalid
//                }
//            }
//        });        
        
//        regisBtn.setOnAction(event -> {
//            // navigate to the registration page
//        });
        
      
        
       
        
//        j.setScene(c1);
    }
}
