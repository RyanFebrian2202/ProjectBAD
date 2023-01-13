package model;

public class DetailTransaction {
	private int TransactionID, WatchID, Quantity;
	private String WatchName, WatchBrand, SubTotal, WatchPrice;
	
	public DetailTransaction(int transactionID, int watchID, int quantity, String watchName, String watchBrand,
			String watchPrice, String subTotal) {
		super();
		TransactionID = transactionID;
		WatchID = watchID;
		Quantity = quantity;
		WatchName = watchName;
		WatchBrand = watchBrand;
		WatchPrice = watchPrice;
		SubTotal = subTotal;
	}

	public int getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
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

	public String getWatchName() {
		return WatchName;
	}

	public void setWatchName(String watchName) {
		WatchName = watchName;
	}

	public String getWatchBrand() {
		return WatchBrand;
	}

	public void setWatchBrand(String watchBrand) {
		WatchBrand = watchBrand;
	}

	public String getWatchPrice() {
		return WatchPrice;
	}

	public void setWatchPrice(String watchPrice) {
		WatchPrice = watchPrice;
	}

	public String getSubTotal() {
		return SubTotal;
	}

	public void setSubTotal(String subTotal) {
		SubTotal = subTotal;
	}
}
