package com.pmc.atm.dao;

import com.pmc.atm.model.Atm;
import com.pmc.atm.model.Bank;
import com.pmc.atm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BankDao {

    private Connection connection;

    public BankDao() {
        this.connection = DatabaseConnection.getConnection();
    }

//    get all bank details
    public List<Bank> getAllBankDetails() {
        List<Bank> list = new ArrayList<>();

        try {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String selectAllBankSql = "SELECT * FROM bank";
            pstmt = this.connection.prepareStatement(selectAllBankSql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("BANK_NAME");
                String pwd = rs.getString("BANK_PWD");

                Bank bank = new Bank(id, name, pwd);
                list.add(bank);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return list;
    }

//    add new bank
    public boolean addNewBank(Bank bank) {
        boolean status = false;

        try{
            PreparedStatement pstmt = null;
            String insertBankSql = "INSERT INTO bank (BANK_NAME, BANK_PWD) VALUES (?, ?)";
            pstmt = this.connection.prepareStatement(insertBankSql);
            pstmt.setString(1, bank.getName());
            pstmt.setString(2, bank.getPwd());
            int rowUpdated = pstmt.executeUpdate();
            if(rowUpdated == 1){
                status = true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return status;
    }

//    method to get bank using bankname and password
    public Bank getBankByNameAndPwd (String bankName, String bankPwd) {
        Bank bank = null;
        try{
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String selectATMSql = "SELECT * FROM bank WHERE BANK_NAME = ? AND BANK_PWD = ?";
            pstmt = this.connection.prepareStatement(selectATMSql);
            pstmt.setString(1, bankName);
            pstmt.setString(2, bankPwd);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                bank = new Bank();
                bank.setId(rs.getInt("ID"));
                bank.setName(rs.getString("BANK_NAME"));
                bank.setPwd(rs.getString("BANK_PWD"));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return bank;
    }


//    update bank details
    public boolean isBankUpdated (Bank bank) {
        boolean status = false;
        try {
            PreparedStatement pstmt = null;
            String updateSql = "UPDATE bank SET BANK_NAME = ?, BANK_PWD = ? WHERE ID = ?";
            pstmt = this.connection.prepareStatement(updateSql);

            pstmt.setString(1, bank.getName());
            pstmt.setString(2, bank.getPwd());
            pstmt.setInt(3, bank.getId());

            int rowUpdated = pstmt.executeUpdate();
            if (rowUpdated == 1) {
                status = true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return status;
    }

//    get bank name using bank id

    public String bankName(int bankId) {
        String name = null;
        try{
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String selectATMSql = "SELECT BANK_NAME FROM bank WHERE Id = ?";
            pstmt = this.connection.prepareStatement(selectATMSql);
            pstmt.setInt(1, bankId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                name = rs.getString("BANK_NAME");
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return name;
    }
}
