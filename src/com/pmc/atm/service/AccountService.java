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

    public boolean addNewAccount(int bankId, String accountType, String accountPwd, int accountBalance) {
        boolean status = false;

        Account account = new Account();
        account.setBankId(bankId);
        account.setAccountType(accountType);
        account.setAccountStatus("Active");
        account.setAccountPwd(accountPwd);
        account.setBalance(accountBalance);

        int accountId = accountDao.isNewAccountAdded(account);
        if(accountId >= 0) {
            status = true;
        } else {
            System.out.println("Something went wrong..");
        }
        return status;
    }
}
