package com.pmc.atm.service;

import com.pmc.atm.dao.BankDao;
import com.pmc.atm.model.Bank;

import java.security.PublicKey;

public class BankService {
    private BankDao bankDao;

    public BankService() {
        bankDao = new BankDao();
    }

    public boolean addNewBank(String bankName, String bankPwd) {
        boolean status = false;

        Bank bank = new Bank();
        bank.setName(bankName);
        bank.setPwd(bankPwd);

        if(bankDao.addNewBank(bank)) {
            status = true;
        }
        return status;
    }

    public Bank getBankUsingNameAndPwd(String bankName, String bankPwd) {
        Bank bank = bankDao.getBankByNameAndPwd(bankName, bankPwd);
        return bank;
    }

    public boolean isBankUpdate (Bank bank) {
        boolean status = false;

        if (bankDao.isBankUpdated(bank)) {
            status = true;
        }
        return status;
    }

    public String getBankName (int bankId) {
        String bankName = bankDao.bankName(bankId);

        return bankName;
    }
}
