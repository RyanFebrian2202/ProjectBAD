package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HeaderTransaction {

	protected int TransactionID, UserID;
	protected Date TransactionDate;

	public HeaderTransaction(int transactionID, int userID, Date transactionDate) {
		super();
		TransactionID = transactionID;
		UserID = userID;
		TransactionDate = transactionDate;
	}

	public int getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public Date getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		TransactionDate = transactionDate;
	}

}
