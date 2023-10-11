package com.pmc.atm.dao;

import com.pmc.atm.model.Customer;
import com.pmc.atm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDao {

    private Connection connection;

    public CustomerDao () {
        this.connection = DatabaseConnection.getConnection();
    }

    public boolean isNewCustomerAdded (Customer customer) {
        boolean status = false;

        try {
            PreparedStatement pstmt = null;
            String insertAccountSql = "INSERT INTO customer (NAME, ACCOUNT_ID) VALUES (?, ?)";
            pstmt = this.connection.prepareStatement(insertAccountSql);
            pstmt.setString(2, customer.getName());
            pstmt.setInt(1, customer.getAccountId());
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
