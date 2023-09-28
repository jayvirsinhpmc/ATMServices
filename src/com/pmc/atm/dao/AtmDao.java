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
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
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
			throw new SQLException(exc);
		} finally {
			connection.close();
			if (null != pstmt) {
				pstmt.close();
			}
		}
		return list;
	}


	public Atm getAtmDetail(String name) {
		return null;
	}

	public void updateAtmDetail(String name) throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		boolean autoCommit = false;
		PreparedStatement pstmt = null;
		try {

			String updatePositionSql = "UPDATE employees SET position=? WHERE emp_id=?";
			pstmt = connection.prepareStatement(updatePositionSql);
			pstmt.setString(1, "lead developer");
			pstmt.setInt(2, 1);

			autoCommit = connection.getAutoCommit();

			connection.setAutoCommit(false);

			pstmt.executeUpdate();

			connection.commit();

		} catch (Exception exc) {
			connection.rollback();
			throw new SQLException(exc);
		} finally {
			connection.setAutoCommit(autoCommit);
			connection.close();

			if (null != pstmt) {
				pstmt.close();
			}
		}

	}
}
