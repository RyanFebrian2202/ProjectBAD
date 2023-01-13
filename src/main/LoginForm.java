package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;

public class LoginForm{
	
	private static LoginForm instance;
	private static User user;
	
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
	Database db = Database.getConnection();
	
	public static LoginForm getInstance() {
		if (instance == null) {
			instance = new LoginForm();
		}
		return instance;
	}
	
	public static User getUser() {
		return user;
	}
	
	public static void setUser(User inputUser) {
		 user = inputUser;
	}
	
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
		
		
		// label
		TitleLogin = new Label("Watches Dealer Login");
		TitleLogin.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		emailLogin = new Label("Email :");
		pwLogin = new Label("Password :");
		emailTextField = new TextField();
		emailTextField.setPromptText("Email Address");
		pwLoginPF = new PasswordField();
		pwLoginPF.setPromptText("Password");
		
		//button
		LoginBtn = new Button("Login");
		LoginBtn.setMinWidth(200);
		LoginBtn.setBackground(bgL1);
		LoginBtn.setTextFill(Color.WHITE);
		RegisInsteadBtn = new Button("Register Instead");
		RegisInsteadBtn.setMinWidth(200);
		RegisInsteadBtn.setBackground(bgL1);
		RegisInsteadBtn.setTextFill(Color.WHITE);
		
		//posisi grid
		grid.add(TitleLogin, 0, 0);
		grid.add(emailLogin, 0, 1);
		grid.add(emailTextField, 0, 2);
		grid.add(pwLogin, 0, 3);
		grid.add(pwLoginPF, 0, 4);
		grid.add(LoginBtn, 0, 5);
		grid.add(RegisInsteadBtn, 0, 6);
		
		// posisi
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(20);
		grid.setHgap(20);	
		GridPane.setHalignment(TitleLogin, HPos.CENTER);
		grid.setBackground(bgL2);
		
		BorderPane.setMargin(grid, new Insets(120));
	
		border.setCenter(grid);
		border.setBackground(bgL);
		
		scene = new Scene(border, 500, 600);
	}
	

	public void showLogin(){
		init();		
		scene.setFill(Color.PALETURQUOISE);
		
		RegisInsteadBtn.setOnAction( (event) -> {
			RegisterForm rf = RegisterForm.getInstance();
			rf.showRegisterForm();
		});
    	
    	LoginBtn.setOnAction( (event1) -> {
    		
			if (emailTextField.getText().isEmpty()) {
				showErrorAlert("Email field must be filled.");
			} else if (pwLoginPF.getText().isEmpty()) {
				showErrorAlert("Password field must be filled.");
			} else {
	    		String query = String.format("SELECT * FROM `user` WHERE UserEmail = '%s' AND UserPassword = '%s'", emailTextField.getText(),pwLoginPF.getText());
	    		
//	    		PreparedStatement ps = db.prepareStatement(query);
//	    		try {
//					ps.setString(1, emailTextField.getText());
//					ps.setString(2, pwLoginPF.getText());
//					ResultSet rs = ps.executeQuery();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
	    		
	    		ResultSet rs = db.executeQuery(query);
	    		
	    		try {
					if(rs.next()) {
						int userID = rs.getInt("UserID");
						String userName = rs.getString("UserName");
						String userEmail = rs.getString("UserEmail");
						String userPassword = rs.getString("UserPassword");
						String userGender = rs.getString("UserGender");
						String userRole = rs.getString("UserRole");
						
						user = new User(userID, userName, userEmail, userPassword, userGender, userRole);
						
						if(userRole.equals("Customer")) {
							CustomerMain cm = CustomerMain.getInstance();
							cm.showCustomerPage();
						} else if(userRole.equals("Admin")) {
							AdminMain am = AdminMain.getInsance();
							am.showPage();
						}
					} else {
						showErrorAlert("Invalid Credential");
					}
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
    	});
    	
		
		Main.changeScene(scene, "Login Form");
		
		// Add Regis class ke stack pane
//		Regis otherClass = new Regis();
//        StackPane root = new StackPane();
//        root.getChildren().add(Regis.getRoot());		
	}		
	
	private void showErrorAlert(String string) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText("Invalid credential!");
        alert.showAndWait();		
	}
}
