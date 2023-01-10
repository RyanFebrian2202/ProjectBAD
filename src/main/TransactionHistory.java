package main;

public class TransactionHistory {

	private String transactionId;
	private String userId;
	private String transactionDate;
	
	
	public TransactionHistory(String transactionId, String userId, String transactionDate) {
		this.transactionId = transactionId;
		this.userId = userId;
		this.transactionDate = transactionDate;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
}
