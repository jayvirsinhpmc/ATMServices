package com.pmc.atm.dao;

import com.pmc.atm.model.Atm;
import com.pmc.atm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtmDao {

//	method to get atm balance using atmId
	public int getAtmBalance(int atmId) {
		int atmBalance = 0;
		try  {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String selectAtmBalanceSql = "SELECT balance FROM atm WHERE ID = ?";
			pstmt = connection.prepareStatement(selectAtmBalanceSql);
			pstmt.setInt(1, atmId);
			rs = pstmt.executeQuery();
			atmBalance = rs.getInt("BALANCE");

			connection.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return atmBalance;
	}
//	method to get details of all the atms
	public List<Atm> getAllAtmDetails() {
		List<Atm> list = new ArrayList<>();

		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String selectAllAtmSql = "SELECT * FROM atm";
			pstmt = connection.prepareStatement(selectAllAtmSql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("ATM_NAME");
				String pwd = rs.getString("ATM_PWD");
				int balance = rs.getInt("BALANCE");

				Atm atm = new Atm(id, name, pwd, balance);
				list.add(atm);
			}
			connection.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return list;
	}

//	    update atm balance
	public boolean isAtmBalanceUpdated(int atmId, int balance) {
		boolean status = false;
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pstmt = null;
			String selectAllAtmSql = "UPDATE atm SET BALANCE = ? WHERE ID = ?";
			pstmt = connection.prepareStatement(selectAllAtmSql);
			pstmt.setInt(1, balance);
			pstmt.setInt(2, atmId);
			int rowUpdated = pstmt.executeUpdate();
			if (rowUpdated == 1) {
				status = true;
			}
			connection.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return status;
	}

//	method to get atm using atmId and password
	public Atm getAtmByIDAndPwd (int atmId, String pwd) {
		Atm atm = null;
		try{
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String selectATMSql = "SELECT * FROM atm WHERE ID = ? AND ATM_PWD = ?";
			pstmt = connection.prepareStatement(selectATMSql);
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
			connection.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return atm;
	}
}
