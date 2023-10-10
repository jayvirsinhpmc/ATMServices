package com.pmc.atm.dao;

import com.pmc.atm.model.Transaction;
import com.pmc.atm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionDao {

    public boolean isTransactionAdded (Transaction transaction) {
        boolean status = false;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement pstmt = null;
            String insertTransactionSql = "INSERT INTO transaction (ACCOUNT_ID, ATM_ID, TRANSACTION_TYPE, AMOUNT, datetimecreated) values (?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(insertTransactionSql);
            pstmt.setInt(1, transaction.getAccountid());
            pstmt.setInt(2, transaction.getAtmId());
            pstmt.setString(3, transaction.getTransactionType());
            pstmt.setInt(4, transaction.getAmount());
            pstmt.setTimestamp(5, new java.sql.Timestamp(transaction.getDateTimeCreated().getTime()));
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
}
