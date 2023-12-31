package com.pmc.atm.dao;

import com.pmc.atm.model.Account;
import com.pmc.atm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountDao {

    private Connection connection;

    public AccountDao () {
        this.connection = DatabaseConnection.getConnection();
    }


    //    get account details using id
    public Account getAccountByID(int id) {
        Account account = null;
        try {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String selectAllAtmSql = "SELECT * FROM account WHERE ID = ?";
            pstmt = this.connection.prepareStatement(selectAllAtmSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                account = new Account();
                account.setId(rs.getInt("id"));
                account.setBankId(rs.getInt("bank_id"));
                account.setAccountType(rs.getString("account_type"));
                account.setAccountStatus(rs.getString("account_status"));
                account.setAccountPwd(rs.getString("account_pwd"));
                account.setBalance(rs.getInt("balance"));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return account;
    }


    //    get account details using name and pwd
    public Account getAccountByIdAndPwd(int accountId, String accountPwd) {
        Account account = null;
        try {
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            String selectAllAtmSql = "SELECT * FROM account WHERE ID = ? AND ACCOUNT_PWD = ?";

            pstmt = this.connection.prepareStatement(selectAllAtmSql);
            pstmt.setInt(1, accountId);
            pstmt.setString(2, accountPwd);

            rs = pstmt.executeQuery();
            while (rs.next()){
                account = new Account();
                account.setId(rs.getInt("id"));
                account.setBankId(rs.getInt("bank_id"));
                account.setAccountType(rs.getString("account_type"));
                account.setAccountStatus(rs.getString("account_status"));
                account.setAccountPwd(rs.getString("account_pwd"));
                account.setBalance(rs.getInt("balance"));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return account;
    }


    //    update account balance
    public boolean isAccountBalanceUpdated(int accountId, int balance) {
        boolean status = false;
        try {
            PreparedStatement pstmt = null;
            String selectAllAtmSql = "UPDATE account SET BALANCE = ? WHERE ID = ?";
            pstmt = this.connection.prepareStatement(selectAllAtmSql);
            pstmt.setInt(1, balance);
            pstmt.setInt(2, accountId);
            int rowUpdated = pstmt.executeUpdate();
            if (rowUpdated == 1) {
                status = true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return status;
    }


    //    insert new account
    public int isNewAccountAdded(Account account) {
        int accountId = 0;
        try {
            PreparedStatement pstmt = null;
            String insertAccountSql = "INSERT INTO account (BANK_ID, ACCOUNT_TYPE, ACCOUNT_STATUS, ACCOUNT_PWD, BALANCE) VALUES (?, ?, ?, ?, ?)";
            pstmt = this.connection.prepareStatement(insertAccountSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, account.getBankId());
            pstmt.setString(2, account.getAccountType());
            pstmt.setString(3, account.getAccountStatus());
            pstmt.setString(4, account.getAccountPwd());
            pstmt.setInt(5, account.getBalance());
            int rowUpdated = pstmt.executeUpdate();

            if (rowUpdated == 1) {
                // Retrieve the generated keys
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    accountId = generatedKeys.getInt(1);
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return accountId;
    }

    //    update account
    public boolean isAccountUpdated (Account account) {
        boolean status = false;
        try {
            PreparedStatement pstmt = null;
            String updateSql = "UPDATE account SET ACCOUNT_TYPE = ?, ACCOUNT_STATUS = ?, ACCOUNT_PWD = ? WHERE ID = ?";
            pstmt = this.connection.prepareStatement(updateSql);

            pstmt.setString(1, account.getAccountType());
            pstmt.setString(2, account.getAccountStatus());
            pstmt.setString(3, account.getAccountPwd());
            pstmt.setInt(4, account.getId());

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

//-------------------------------------------------------------------------
