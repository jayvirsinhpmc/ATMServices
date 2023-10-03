package com.pmc.atm.service;

import com.pmc.atm.dao.AccountDao;
import com.pmc.atm.model.Account;

public class AccountService {

    private AccountDao accountDao;

    public AccountService() {
        accountDao = new AccountDao();
    }

    public boolean validateAccount(int id, String pwd) {
        Account account = accountDao.getAccountByID(id);

        return account != null && account.getAccountStatus().equals("Active") && account.getAccountPwd().equals(pwd);
    }
}
