package com.pmc.atm.dao;

import com.pmc.atm.model.Atm;
import com.pmc.atm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AtmDao {

	private Connection connection;

	public AtmDao() {
		this.connection = DatabaseConnection.getConnection();
	}

	//	method to get atm balance using atmId
	public int getAtmBalance(int atmId) {
		int atmBalance = 0;
		try  {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String selectAtmBalanceSql = "SELECT balance FROM atm WHERE ID = ?";
			pstmt = this.connection.prepareStatement(selectAtmBalanceSql);
			pstmt.setInt(1, atmId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				atmBalance = rs.getInt("BALANCE");
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return atmBalance;
	}
//	method to get details of all the atms
	public List<Atm> getAllAtmDetails() {
		List<Atm> list = new ArrayList<>();

		try {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String selectAllAtmSql = "SELECT * FROM atm";
			pstmt = this.connection.prepareStatement(selectAllAtmSql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("ATM_NAME");
				String pwd = rs.getString("ATM_PWD");
				int balance = rs.getInt("BALANCE");

				Atm atm = new Atm(id, name, pwd, balance);
				list.add(atm);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return list;
	}

//	    update atm balance
	public boolean isAtmBalanceUpdated(int atmId, int balance) {
		boolean status = false;
		try {
			PreparedStatement pstmt = null;
			String selectAllAtmSql = "UPDATE atm SET BALANCE = ? WHERE ID = ?";
			pstmt = this.connection.prepareStatement(selectAllAtmSql);
			pstmt.setInt(1, balance);
			pstmt.setInt(2, atmId);
			int rowUpdated = pstmt.executeUpdate();
			if (rowUpdated == 1) {
				status = true;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return status;
	}

//	method to get atm using atmId and password
	public Atm getAtmByIDAndPwd (int atmId, String pwd) {
		Atm atm = null;
		try{
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String selectATMSql = "SELECT * FROM atm WHERE ID = ? AND ATM_PWD = ?";
			pstmt = this.connection.prepareStatement(selectATMSql);
			pstmt.setInt(1, atmId);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				atm = new Atm();
				atm.setId(rs.getInt("ID"));
				atm.setName(rs.getString("ATM_NAME"));
				atm.setPwd(rs.getString("ATM_PWD"));
				atm.setBalance(rs.getInt("BALANCE"));
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return atm;
	}

//	insert new atm
	public boolean isNewATMInserted (Atm atm) {
		boolean status = false;

		try {
			PreparedStatement pstmt = null;
			String insertATMSql = "INSERT INTO atm (ATM_NAME, ATM_PWD, BALANCE) VALUES (?, ?, ?)";
			pstmt = this.connection.prepareStatement(insertATMSql);
			pstmt.setString(1, atm.getName());
			pstmt.setString(2, atm.getPwd());
			pstmt.setInt(3, atm.getBalance());
			int rowUpdated = pstmt.executeUpdate();
			if (rowUpdated == 1) {
				status = true;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return status;
	}
}

//------------------------------------------------------------------------------
