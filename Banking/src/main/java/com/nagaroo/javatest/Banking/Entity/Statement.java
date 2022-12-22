package com.nagaroo.javatest.Banking.Entity;

import java.time.LocalDate;

public class Statement {
	private int ID;
	private int accountId;
	private String dateField;
	private String amount;
	
	

	public Statement() {
		super();
		// TODO Auto-generated constructor stub
	}








	public Statement(int iD, int accountId, String dateField, String amount) {
		super();
		ID = iD;
		this.accountId = accountId;
		this.dateField = dateField;
		this.amount = amount;
	}








	@Override
	public String toString() {
		return "Statement [ID=" + ID + ", accountid=" + accountId + ", dateField=" + dateField + ", amount=" + amount
				+ "]";
	}








	public int getID() {
		return ID;
	}








	public void setID(int iD) {
		ID = iD;
	}








	public int getAccountId() {
		return accountId;
	}








	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}








	public String getDateField() {
		return dateField;
	}








	public void setDateField(String dateField) {
		this.dateField = dateField;
	}








	public String getAmount() {
		return amount;
	}








	public void setAmount(String amount) {
		this.amount = amount;
	}









	
	
}
