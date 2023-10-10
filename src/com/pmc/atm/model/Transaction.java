package com.pmc.atm.model;

import java.util.Date;

public class Transaction {
	private int id;
	private int atmId;
	private int accountid;
	private String transactionType;
	private int amount;
	private Date dateTimeCreated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAtmId() {
		return atmId;
	}

	public void setAtmId(int atmId) {
		this.atmId = atmId;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDateTimeCreated() {
		return dateTimeCreated;
	}

	public void setDateTimeCreated(Date dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", atmId=" + atmId + ", accountid=" + accountid + ", transactionType=" + transactionType
				+ ", amount=" + amount + ", dateTimeCreated=" + dateTimeCreated + "]";
	}

}
