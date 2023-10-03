package com.pmc.atm.dao;

import com.pmc.atm.model.Account;
import com.pmc.atm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDao {

//    get account details using id
    public Account getAccountByID(int id) {
        Account account = null;
        try (Connection connection = DatabaseConnection.getConnection()){
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String selectAllAtmSql = "SELECT * FROM account WHERE ID = ?";
            pstmt = connection.prepareStatement(selectAllAtmSql);
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

//    update account balance
    public boolean isAccountBalanceUpdated(int id, int balance) {
        boolean status = false;
        try (Connection connection = DatabaseConnection.getConnection()){
            PreparedStatement pstmt = null;
            String selectAllAtmSql = "UPDATE account SET BALANCE = ? WHERE ID = ?";
            pstmt = connection.prepareStatement(selectAllAtmSql);
            pstmt.setInt(1, balance);
            pstmt.setInt(2, id);
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
