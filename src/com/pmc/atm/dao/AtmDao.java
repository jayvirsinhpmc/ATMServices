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

//	method to get details of all the atms
	public List<Atm> getAllAtmDetails() throws SQLException {
		List<Atm> list = new ArrayList<>();

		try (Connection connection = DatabaseConnection.getConnection()) {
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
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return list;
	}


	public Atm getAtmDetail(String name) {
		return null;
	}
}
