package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginRegisForm extends Application{

	// Variabel Login Form
	GridPane grid;
	BorderPane bPane;
	
	Label title, email, pw;
	TextField emailTF;
	PasswordField pwPF;
	Button loginBtn, regisinsteadBtn;
	StackPane stackPane;
	Scene scene;
	Rectangle rectangle;
	
	// Variabel Register Form
	GridPane gridRegis;
	BorderPane bPaneRegis;
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
	
	
	public Scene initLogin() {
		
//		retangle blkngnnya pke stack pane
		
        
//    	Grid Pane
        
        grid = new GridPane();
        bPane = new BorderPane();
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
//      Label Title
        title = new Label("Watches Dealer Login");
        title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        title.setPadding(new Insets(200, 0, 0, 0));
        bPane.setTop(title);
        bPane.setAlignment(title, Pos.TOP_CENTER);
        
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
        regisinsteadBtn = new Button("Register Instead");
        regisinsteadBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        regisinsteadBtn.setTextFill(Color.WHITE);
        regisinsteadBtn.setFont(Font.font("System", FontWeight.BOLD, 10));
//        HBox hbBtn1 = new HBox(10);
//        hbBtn1.setAlignment(Pos.CENTER);
//        hbBtn1.getChildren().add(regisBtn);
        regisinsteadBtn.setMinWidth(250);
        regisinsteadBtn.setAlignment(Pos.CENTER);
        grid.add(regisinsteadBtn, 1, 6);
        
//        kalo ditambah backgroup kotak putihnya ilang
//        grid.setStyle("-fx-background-color: lightcyan;");
        
        grid.setPadding(new Insets(0, 0, 150, 0));
        bPane.setCenter(grid);
        
//      Rectangle Belakang
        rectangle = new Rectangle(400, 400);
        rectangle.setFill(Color.WHITE);
        
//      StackPane (Buat numpuk rectangle sama gridpane)
        stackPane = new StackPane();
        stackPane.getChildren().add(rectangle);
        stackPane.getChildren().add(bPane);
        
//      Scene
        scene = new Scene(stackPane, 600, 650);
        
        return scene;
	}
	
	public Scene initRegis() {
		
//      Grid Pane
		gridRegis = new GridPane();
		gridRegis.setAlignment(Pos.CENTER);
		gridRegis.setHgap(15);
		gridRegis.setVgap(15);
        
        bPaneRegis = new BorderPane();
        
//      Label Title
        titleLblRegis = new Label("Register");
        titleLblRegis.setAlignment(Pos.CENTER);
        titleLblRegis.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        titleLblRegis.setPadding(new Insets(150, 0, 0, 0));
        bPaneRegis.setTop(titleLblRegis);
        bPaneRegis.setAlignment(titleLblRegis, Pos.CENTER);
        

//      Label Name
        nameLblRegis = new Label("Name :");
        gridRegis.add(nameLblRegis, 1, 1);
        
//      TextField Name
        nameTFRegis = new TextField();
        nameTFRegis.setPromptText("Name");
        nameTFRegis.setMinWidth(250);
        gridRegis.add(nameTFRegis, 1, 2);
        
//      Label Gender
        genderLblRegis = new Label("Gender :");
        gridRegis.add(genderLblRegis, 1, 3);
        
//      Radio Button Gender
        maleRBRegis = new RadioButton("Male");
        femaleRBRegis = new RadioButton("Female");
        
        genderTGRegis = new ToggleGroup();
        
        maleRBRegis.setToggleGroup(genderTGRegis);
        femaleRBRegis.setToggleGroup(genderTGRegis);
		
		genderHboxRegis = new HBox(15);
		genderHboxRegis.getChildren().add(maleRBRegis);
		genderHboxRegis.getChildren().add(femaleRBRegis);
		gridRegis.add(genderHboxRegis, 1, 4);
        
//		Label Email
		emailLblRegis = new Label("Email :");
		gridRegis.add(emailLblRegis, 1, 5);
        
//      TextField Email
        emailTFRegis  = new TextField();
        emailTFRegis.setPromptText("Email Address");
        emailTFRegis.setMinWidth(250);
        gridRegis.add(emailTFRegis , 1, 6);
		
//		Label Password
        passwordLblRegis = new Label("Password :");
        gridRegis.add(passwordLblRegis, 1, 7);
        
//      Password Field Password
        passwordPFRegis = new PasswordField();
        passwordPFRegis.setPromptText("Password");
        passwordPFRegis.setMinWidth(250);
        gridRegis.add(passwordPFRegis, 1, 8);
        
//      Label Confirm Password
        confirmPasswordLblRegis = new Label("Confirm Password :");
        gridRegis.add(confirmPasswordLblRegis, 1, 9);
        
//      Password Field Confirm Password
        confirmPasswordPFRegis = new PasswordField();
        confirmPasswordPFRegis.setPromptText("Confirm Password");
        confirmPasswordPFRegis.setMinWidth(250);
        gridRegis.add(confirmPasswordPFRegis, 1, 10);
        
//      Register Button
        regisBtn = new Button("Register");
        regisBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        regisBtn.setTextFill(Color.WHITE);
        regisBtn.setFont(Font.font("System", FontWeight.BOLD, 10));
        regisBtn.setMinWidth(250);
        gridRegis.add(regisBtn, 1, 11);
        
//      Back to Login Button
        backToLoginBtn = new Button("Back to Login");
        backToLoginBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        backToLoginBtn.setTextFill(Color.WHITE);
        backToLoginBtn.setFont(Font.font("System", FontWeight.BOLD, 10));
        backToLoginBtn.setMinWidth(250);
        gridRegis.add(backToLoginBtn, 1, 12);
        
		
//        gridRegis.setStyle("-fx-background-color: lightcyan;");
        
        bPaneRegis.setCenter(gridRegis);
        gridRegis.setPadding(new Insets(0, 0, 150, 0));
        
//      Rectangle Belakang
        rectangleRegis = new Rectangle(400, 550);
        rectangleRegis.setFill(Color.WHITE);
        
//      StackPane (Buat numpuk rectangle sama gridpane)
        stackPaneRegis = new StackPane();
        stackPaneRegis.getChildren().add(rectangleRegis);
        stackPaneRegis.getChildren().add(bPaneRegis);
		
        sceneRegis = new Scene(stackPaneRegis, 600, 650);        
        
		return sceneRegis;
	}
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initLogin();
		
		initRegis();
		
		regisinsteadBtn.setOnAction( (event) -> {
			 primaryStage.setScene(sceneRegis);
		});
	    	
	    loginBtn.setOnAction( (event) -> {
	    	System.out.println("Masuk Aplikasi");
	    	AdminMain am = new AdminMain();
	    	try {
				am.start(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	primaryStage.show();
	    });
	    	
	    regisBtn.setOnAction( (event) -> {
	    	System.out.println("Masuk Aplikasis");
	    });
	    	
	    backToLoginBtn.setOnAction( (event) -> {
	    	primaryStage.setScene(scene);
	    });
		
		
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		//primaryStage.show();
    	
		
	}

}
