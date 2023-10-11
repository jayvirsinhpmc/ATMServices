package com.pmc.atm.menu;

import com.pmc.atm.dao.AccountDao;
import com.pmc.atm.model.Account;
import com.pmc.atm.service.AccountService;

import java.util.Scanner;

public class AccountBalanceCheckMenu {

    private AccountDao accountDao;

    public AccountBalanceCheckMenu() {

        accountDao = new AccountDao();
    }

    public void validateAndGetBalance(Scanner scan) {
        int accountBalance = 0;
        System.out.println("===============================================================");
        System.out.println(" Account Balance ");
        System.out.println("===============================================================");
        System.out.print("Enter Account ID: ");
        int accountId = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter Account Password: ");
        String pwd = scan.nextLine();
        System.out.println("Please wait while we check your balance...");

        AccountService as = new AccountService();
        boolean isValid = as.validateAccount(accountId, pwd);

        if (isValid) {
            Account account = accountDao.getAccountByID(accountId);
            accountBalance = account.getBalance();
            System.out.println("Your Account balance is: " + accountBalance);
            MenuTwo.menu(scan);
        } else {
            System.out.println("Unable to find account details. Please try again.");
            validateAndGetBalance(scan);
        }
    }
}
