package model;

public class Cart {

	protected String UserID, WatchID;
	protected Integer Quantity;

	public Cart(String userID, String watchID, Integer quantity) {
		super();
		UserID = userID;
		WatchID = watchID;
		Quantity = quantity;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getWatchID() {
		return WatchID;
	}

	public void setWatchID(String watchID) {
		WatchID = watchID;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

}
