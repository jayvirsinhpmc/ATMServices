package com.pmc.atm.dao;

import com.pmc.atm.model.Atm;
import com.pmc.atm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtmDao {

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
