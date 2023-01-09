package model;

public class Cart {

	protected int UserID, WatchID, Quantity;

	public Cart(int userID, int watchID, int quantity) {
		super();
		UserID = userID;
		WatchID = watchID;
		Quantity = quantity;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public int getWatchID() {
		return WatchID;
	}

	public void setWatchID(int watchID) {
		WatchID = watchID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	
}