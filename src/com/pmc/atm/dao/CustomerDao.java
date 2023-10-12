package com.pmc.atm.dao;

import com.pmc.atm.model.Customer;
import com.pmc.atm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
            pstmt.setString(1, customer.getName());
            pstmt.setInt(2, customer.getAccountId());
            int rowUpdated = pstmt.executeUpdate();
            if (rowUpdated == 1) {
                status = true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return status;
    }

    public String getCustomerName(int accountId) {
        String name = null;
        try{
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String selectNameSql = "SELECT NAME FROM customer WHERE ACCOUNT_ID = ?";
            pstmt = this.connection.prepareStatement(selectNameSql);
            pstmt.setInt(1, accountId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                name = rs.getString("NAME");
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return name;
    }

    public boolean isCustomerUpdated (Customer customer) {
        boolean status = false;
        try {
            PreparedStatement pstmt = null;
            String updateSql = "UPDATE customer SET NAME = ? WHERE ID = ?";
            pstmt = this.connection.prepareStatement(updateSql);

            pstmt.setString(1, customer.getName());
            pstmt.setInt(2, customer.getId());

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
