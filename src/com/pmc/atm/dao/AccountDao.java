package com.pmc.atm.dao;

import com.pmc.atm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {

//    checking whether account is active or deactive using id and pwd
    public boolean checkAccountStatus(int id, String pwd) throws SQLException {
        boolean status = false;
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String selectAllAtmSql = "SELECT ACCOUNT_STATUS FROM account WHERE ID = ? AND ACCOUNT_PWD = ?";
            pstmt = connection.prepareStatement(selectAllAtmSql);
            pstmt.setInt(1, id);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String stat = rs.getString("ATM_NAME");

                if ("Active".equals(stat)) {
                    status = true;
                }
            }
        } catch (Exception exc) {
            throw new SQLException(exc);
        } finally {

            if (null != pstmt) {
                pstmt.close();
            }
            if (null != connection) {
                connection.close();
            }
        }

        return status;
    }
}
