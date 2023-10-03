package com.pmc.atm.service;

import com.pmc.atm.dao.AccountDao;
import com.pmc.atm.model.Account;

public class AccountService {

    private AccountDao accountDao;

    public AccountService() {
        accountDao = new AccountDao();
    }
    public boolean isAccountActive(int id) {
        Account account = accountDao.getAccountByID(id);

        return account != null && account.getAccountStatus().equals("Active");
    }

    public boolean isAccountAndPasswordMatch(int id, String pwd) {
        Account account = accountDao.getAccountByID(id);

        return account != null && account.getAccountPwd().equals(pwd);
    }
}
