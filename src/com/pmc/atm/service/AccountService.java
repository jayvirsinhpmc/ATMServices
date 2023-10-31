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

    public int addNewAccount(int bankId, String accountType, String accountPwd, int accountBalance) {

        int accountId = 0;

        Account account = new Account();
        account.setBankId(bankId);
        account.setAccountType(accountType);
        account.setAccountStatus("Active");
        account.setAccountPwd(accountPwd);
        account.setBalance(accountBalance);

        accountId = accountDao.isNewAccountAdded(account);


        return accountId;
    }

    public Account getAccountByIdAndPwd (int accountId, String accountPwd) {
        Account account = accountDao.getAccountByIdAndPwd(accountId, accountPwd);

        return account;
    }

    public boolean isAccountUpdated (Account account) {
        boolean status = false;

        if (accountDao.isAccountUpdated(account)) {
            status = true;
        }
        return status;
    }

}
