package com.pmc.atm.model;

public class Atm {
	private int id;
	private String name;
	private String pwd;
	private int balance;

	public Atm() {
	}

	public Atm(int id, String name, String pwd, int balance) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
