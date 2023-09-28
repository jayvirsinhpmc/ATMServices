package com.pmc.atm.model;

public class Account {

	private int id;
	private int bankId;
	private int accountType;
	private int accountStatus;
	private String accountPwd;
	private int balance;

	public Account() {
	}

	public Account(int id, String accountPwd) {
		this.id = id;
		this.accountPwd = accountPwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public int getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", bankId=" + bankId + ", accountType=" + accountType + ", accountStatus=" + accountStatus
				+ ", balance=" + balance + "]";
	}

}
