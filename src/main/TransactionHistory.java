package main;

public class TransactionHistory {

	private int transactionId;
	private int userId;
	private String transactionDate;
	
	
	public TransactionHistory(int transactionId, int userId, String transactionDate) {
		this.transactionId = transactionId;
		this.userId = userId;
		this.transactionDate = transactionDate;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
}
