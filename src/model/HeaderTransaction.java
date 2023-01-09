package model;

public class HeaderTransaction {

	protected String TransactionID, UserID, TransactionDate;

	public HeaderTransaction(String transactionID, String userID, String transactionDate) {
		super();
		TransactionID = transactionID;
		UserID = userID;
		TransactionDate = transactionDate;
	}

	public String getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}

}
