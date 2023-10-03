package com.pmc.atm.model;

public class Account {

	private int id;
	private int bankId;
	private String accountType;
	private String accountStatus;
	private String accountPwd;
	private int balance;

	public Account() {
	}

	public Account(int id, String accountPwd) {
		this.id = id;
		this.accountPwd = accountPwd;
	}

	public Account(int id, int bankId, String accountType, String accountStatus, String accountPwd, int balance) {
		this.id = id;
		this.bankId = bankId;
		this.accountType = accountType;
		this.accountStatus = accountStatus;
		this.accountPwd = accountPwd;
		this.balance = balance;
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
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
