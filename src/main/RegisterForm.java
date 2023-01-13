package main;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterForm {
	private static RegisterForm instance;
	
	String gendervalue, roledefault;
	Scene scene;
	GridPane grid1;
	BorderPane border1;
	Background bg, bg1, bg2;
	BackgroundFill bf, bf1, bf2;
	
	Label TitleRegis, NameRegis, GenderRegis, EmailRegis, pwRegis, ConfirmPwRegis;
	TextField NameTF, EmailTF;
	PasswordField pwRegisPF, ConfirmPwRegisPF;
	RadioButton MaleRB, FemaleRB;
	ToggleGroup tG;
	Button RegisBtn, BackBtn;
	HBox HBX;
	Database db = Database.getConnection();
	
	public static RegisterForm getInstance() {
		if(instance == null) {
			instance = new RegisterForm();
		}
		
		return instance;
	}
	
	public void init() {
		
		// background putih
		bf = new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY);
		bf1 = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
		bf2 = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
		bg2 = new Background(bf2);
		bg = new Background(bf);
		bg1 = new Background(bf1);
		roledefault = "Customer";
		
		// std
		grid1 = new GridPane();
		border1 = new BorderPane();
		
		// label
		TitleRegis = new Label("Register");
		TitleRegis.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		NameRegis = new Label("Name : ");
		GenderRegis = new Label("Gender :");
		EmailRegis = new Label("Email : ");
		pwRegis = new Label("Password :"); 
		ConfirmPwRegis = new Label("Confirm Password");
		
		// TF
		NameTF = new TextField();
		NameTF.setPromptText("Name");
		EmailTF = new TextField();
		EmailTF.setPromptText("Email Address");
		
		// PF
		pwRegisPF = new PasswordField();
		pwRegisPF.setPromptText("Password");
		ConfirmPwRegisPF = new PasswordField();
		ConfirmPwRegisPF.setPromptText("Confirm Password");
		
		// gender
		MaleRB = new RadioButton("Male");
		FemaleRB = new RadioButton("Female");		
		tG = new ToggleGroup();
		MaleRB.setToggleGroup(tG);
		FemaleRB.setToggleGroup(tG);	
		
		MaleRB.setOnAction((event) -> {
			gendervalue = MaleRB.getText();
		});
		
		FemaleRB.setOnAction((event) -> {
			gendervalue = FemaleRB.getText();
		});
		
		
		
		HBX = new HBox(10);
		HBX.getChildren().addAll(MaleRB, FemaleRB);
		
		// set warna
		RegisBtn = new Button("Register");
		RegisBtn.setMinWidth(200);
		RegisBtn.setBackground(bg1);
		RegisBtn.setTextFill(Color.WHITE);		
		BackBtn = new Button("Back to Login");
		BackBtn.setMinWidth(200);
		BackBtn.setBackground(bg1);
		BackBtn.setTextFill(Color.WHITE);
		
		// posisi grid
				grid1.add(TitleRegis, 0, 0);
				grid1.add(NameRegis, 0, 1);
				grid1.add(NameTF, 0, 2);
				grid1.add(GenderRegis, 0, 3);
				grid1.add(HBX, 0, 4);
				grid1.add(EmailRegis, 0, 5);
				grid1.add(EmailTF, 0, 6);
				grid1.add(pwRegis, 0, 7);
				grid1.add(pwRegisPF, 0, 8);
				grid1.add(ConfirmPwRegis, 0, 9);
				grid1.add(ConfirmPwRegisPF, 0, 10);
				grid1.add(RegisBtn, 0, 11);
				grid1.add(BackBtn, 0, 12);

				grid1.setAlignment(Pos.CENTER);
				GridPane.setHalignment(TitleRegis, HPos.CENTER);
				grid1.setVgap(10);
				grid1.setHgap(20);
				grid1.setBackground(bg2);
				
				BorderPane.setMargin(grid1, new Insets(80));

				border1.setCenter(grid1);
				border1.setBackground(bg);
		
				scene = new Scene(border1, 500, 600);
	}
	
	
	public void valid() {
		// validasi Regis
				RadioButton pick = (RadioButton) tG.getSelectedToggle();		
		
				// Name length must be between 5 - 40 characters.
				String name = NameTF.getText();
				String email = EmailTF.getText();
				String pattern = "^[a-zA-Z0-9_+&*-]+(?:\\." +
				                 "[a-zA-Z0-9_+&*-]+)*@" +
				                 "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
				                 "A-Z]{2,7}$";
				int atCount = email.length() - email.replace("@", "").length();
				String password = pwRegisPF.getText();
				String confirm = ConfirmPwRegisPF.getText();
				
				
				if (name.length() < 5 || name.length() > 40) {
				  Alert alert0 = new Alert(AlertType.ERROR);
				  alert0.setContentText("Name must be between 5 and 40 characters!");
				  alert0.showAndWait();
				  
				}else if (pick == null) {
					// Gender must be chosen, either â€œMaleâ€� or â€œFemaleâ€�.
					  Alert alert1 = new Alert(AlertType.ERROR, "Please select a gender!");
					  alert1.showAndWait();
				}else if (!email.matches(pattern)) {
					// validasi email
					  Alert alert2 = new Alert(AlertType.ERROR, "Please enter a valid email address!");
					  alert2.showAndWait();
				}else if (email.contains("@.") || email.contains("..")) {
					// Character â€˜@â€™ must not be next to â€˜.â€™.
						  Alert alert3 = new Alert(AlertType.ERROR, "Character â€˜@â€™ must not be next to â€˜.â€™!");
						  alert3.showAndWait();
				}else if (email.startsWith("@") || email.startsWith(".") || email.endsWith("@") || email.endsWith(".")) {
//					Must not starts and ends with â€˜@â€™ nor â€˜.â€™.
					  Alert alert4 = new Alert(AlertType.ERROR, "Email must not start or end with '@' or '.'!");
					  alert4.showAndWait();
				}else if (atCount != 1) {
//					Must contain exactly one â€˜@â€™.
					  Alert alert5 = new Alert(AlertType.ERROR, "Email must contain exactly one '@' symbol!");
					  alert5.showAndWait();
				}else if (!email.endsWith(".com")) {
//					Must ends with â€˜.comâ€™
					  Alert alert7 = new Alert(AlertType.ERROR, "Email must end with '.com'!");
					  alert7.showAndWait();
				}else if (password.length() < 6 || password.length() > 20) {
					//	Password length must be between 6 - 20 characters.
						  Alert alert8 = new Alert(AlertType.ERROR, "Password must be between 6 and 20 characters!");
						  alert8.showAndWait();
				}else if (!password.equals(confirm)) {
//					Confirm Password must be same as Password.
					  Alert alert9 = new Alert(AlertType.ERROR, "Password and confirm password do not match!");
					  alert9.showAndWait();
				}else {
					System.out.println(name);
					System.out.println(gendervalue);
					System.out.println(email);
					System.out.println(password);
					System.out.println(roledefault);
					
					String query = String.format("INSERT INTO `user` " + "(`UserName`, `UserEmail`, `UserPassword`, `UserGender`, `UserRole`) " + "VALUES ('%s','%s','%s','%s','%s')"
					, name, email, password, gendervalue, roledefault);
					
					db.executeUpdate(query);
					
					Alert alertsucess = new Alert(AlertType.INFORMATION, "Register successful!");
					alertsucess.showAndWait();
					
					LoginForm lf = LoginForm.getInstance();
					lf.showLogin();
				}

				// Must contain exactly one â€˜.â€™ after â€˜@â€™ for separating [provider] and â€œcomâ€�.
//				int dotCount = email.substring(email.indexOf("@")) - email.substring(email.indexOf("@")).replace(".", "").length();
//				if (dotCount != 1) {
//				  Alert alert6 = new Alert(AlertType.ERROR, "Email must contain exactly one '.' symbol after the '@' symbol!");
//				  alert6.showAndWait();
//				  
//				}
				
		
	}
	
	public void insertUser() {
		
	}
	
	public void showRegisterForm(){
		init();
		scene.setFill(Color.PALETURQUOISE);
		
		BackBtn.setOnMouseClicked((event)->{
			LoginForm lf = LoginForm.getInstance();
			lf.showLogin();
		});
		
		RegisBtn.setOnMouseClicked(e -> {
			valid();
		});
		
		Main.changeScene(scene, "Register Form");
	}

}
