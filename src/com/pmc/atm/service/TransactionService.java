package com.pmc.atm.service;

import com.pmc.atm.dao.AccountDao;
import com.pmc.atm.dao.AtmDao;
import com.pmc.atm.dao.TransactionDao;
import com.pmc.atm.model.Account;
import com.pmc.atm.model.Transaction;

import java.util.Date;

public class TransactionService {

    private AtmDao atmDao;
    private AccountDao accountDao;
    private TransactionDao transactionDao;

    public TransactionService() {
        atmDao = new AtmDao();
        accountDao = new AccountDao();
        transactionDao = new TransactionDao();
    }

    public boolean performCreditOperation(int atmID, int accountId, int amount){
        boolean status = false;
        int atmBalance = atmDao.getAtmBalance(atmID);
        Account account = accountDao.getAccountByID(accountId);

//        check if account is not null and
        if( account != null ) {

//            update account balance
            int newAccountBalance = account.getBalance() + amount;

//            update atm balance
            int newAtmBalance = atmBalance + amount;

//            check if account balance updated
            boolean isAccountBalanceUpdated = accountDao.isAccountBalanceUpdated(accountId, newAccountBalance);

//            check if atm balance updated
            boolean isAtmBalanceUpdated = atmDao.isAtmBalanceUpdated(atmID, newAtmBalance);

//            check if account balance and atm balance is updated or not
            if (isAccountBalanceUpdated && isAtmBalanceUpdated) {

//                create transaction
                Transaction transaction = new Transaction();
                transaction.setAccountid(accountId);
                transaction.setAtmId(atmID);
                transaction.setTransactionType("credit");
                transaction.setAmount(amount);
                transaction.setDateTimeCreated(new Date());

                if(transactionDao.isTransactionAdded(transaction)) {
                    status = true;
                }
            } else {
                System.out.println("Something went wrong..");
                status = false;
            }

        } else {
            System.out.println("Account not found.");
            status = false;
        }
        return status;
    }

    public boolean performDebitOperation(int atmID, int accountId, int amount) {
        boolean status = false;
        int atmBalance = atmDao.getAtmBalance(atmID);
        Account account = accountDao.getAccountByID(accountId);

//        check if account is not null and
//        account balance and atm balance is greater than amount user wants to debit
        if( account != null && account.getBalance() >= amount && atmBalance >= amount) {

//            update account balance
            int newAccountBalance = account.getBalance() - amount;

//            update atm balance
            int newAtmBalance = atmBalance - amount;

//            check if account balance updated
            boolean isAccountBalanceUpdated = accountDao.isAccountBalanceUpdated(accountId, newAccountBalance);

//            check if atm balance updated
            boolean isAtmBalanceUpdated = atmDao.isAtmBalanceUpdated(atmID, newAtmBalance);

//            check if account balance and atm balance is updated or not
            if (isAccountBalanceUpdated && isAtmBalanceUpdated) {

//                create transaction
                Transaction transaction = new Transaction();
                transaction.setAccountid(accountId);
                transaction.setAtmId(atmID);
                transaction.setTransactionType("debit");
                transaction.setAmount(amount);
                transaction.setDateTimeCreated(new Date());

                if(transactionDao.isTransactionAdded(transaction)) {
                    status = true;
                }
            } else {
                System.out.println("Something went wrong..");
                status = false;
            }

        } else {
            System.out.println("Insufficient Atm Balance.");
            status = false;
        }
        return status;
    }
}
