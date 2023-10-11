package com.pmc.atm.dao;

import com.pmc.atm.model.Atm;
import com.pmc.atm.model.Bank;
import com.pmc.atm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankDao {

    private Connection connection;

    public BankDao() {
        this.connection = DatabaseConnection.getConnection();
    }

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

    public void updateBankDetail() {

    }
}
