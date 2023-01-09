package model;

public class User {
	private int UserID;
	private String UserName, UserEmail, UserPassword, UserGender, UserRole;
	
	public User(int userID, String userName, String userEmail, String userPassword, String userGender,
			String userRole) {
		super();
		UserID = userID;
		UserName = userName;
		UserEmail = userEmail;
		UserPassword = userPassword;
		UserGender = userGender;
		UserRole = userRole;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public String getUserGender() {
		return UserGender;
	}

	public void setUserGender(String userGender) {
		UserGender = userGender;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}
	
	
}
