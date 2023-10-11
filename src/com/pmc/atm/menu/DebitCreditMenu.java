package com.pmc.atm.menu;

import com.pmc.atm.ApplicationMain;
import com.pmc.atm.dao.AccountDao;
import com.pmc.atm.model.Account;
import com.pmc.atm.model.Atm;
import com.pmc.atm.service.AccountService;
import com.pmc.atm.service.TransactionService;

import java.util.Scanner;

public class DebitCreditMenu {

    private Atm selectedAtm;
    private AccountDao accountDao;

    public DebitCreditMenu () {

    }
    public DebitCreditMenu(Atm selectedAtm) {
        this.selectedAtm = selectedAtm;
        this.accountDao = new AccountDao();
    }

    public void menu(Scanner scan) {
        System.out.println("===============================================================");
        System.out.println(" Select Transaction Operation ");
        System.out.println("===============================================================");
        System.out.println("Enter 1 -> Debit Money");
        System.out.println("Enter 2 -> Credit Money");
        System.out.println("Enter 9 -> Back");
        System.out.println("Enter 0 -> Exit");
        System.out.print("Enter any number: ");
        int option = scan.nextInt();

        switch (option) {
            case 1:
                performDebit(scan);
                break;
            case 2:
                performCredit(scan);
                break;
            case 9:
                SelectAtmMenu sam = new SelectAtmMenu();
                sam.menu(scan);
                break;
            case 0:
                ApplicationMain.exit();
                break;
            default:
                System.out.println("Please enter a valid option.");
                menu(scan);
        }
    }

    private void performDebit(Scanner scan) {
        int accountId = validateAndGetAccountId(scan);
        int atmId = selectedAtm.getId();
        TransactionService ts = new TransactionService();

        if (accountId != -1) {
            boolean debitSuccess = false;
            System.out.print("Enter the amount to debit: ");
            int amount = scan.nextInt();

            if (amount > 0) {
                debitSuccess = ts.performDebitOperation(atmId, accountId, amount);
                Account account = accountDao.getAccountByID(accountId);
                int updatedBalance = account.getBalance();
                System.out.println("Amount debited successfully!");
                System.out.println("Account Balance: " + updatedBalance);
            } else {
                System.out.println("Please enter amount greater than 0.");
            }
        }

        // After the transaction, return to the main menu
        MenuOne menuOne = new MenuOne();
        menuOne.menu(scan);
    }

    private void performCredit(Scanner scan) {
        int accountId = validateAndGetAccountId(scan);
        int atmId = selectedAtm.getId();
        TransactionService ts = new TransactionService();

        if (accountId != -1) {
            boolean creditSuccess = false;
            System.out.print("Enter the amount to credit: ");
            int amount = scan.nextInt();
            if (amount > 0) {
                creditSuccess = ts.performCreditOperation(atmId, accountId, amount);
                Account account = accountDao.getAccountByID(accountId);
                int updatedBalance = account.getBalance();
                System.out.println("Amount credited successfully!");
                System.out.println("Account Balance: " + updatedBalance);
            } else {
                System.out.println("Please enter amount greater than 0.");
            }
        }

        // After the transaction, return to the main menu
        MenuOne menuOne = new MenuOne();
        menuOne.menu(scan);
    }

    private int validateAndGetAccountId(Scanner scan) {
        System.out.println("===============================================================");
        System.out.println(" Enter Account ID and Password For Transaction ");
        System.out.println("===============================================================");
        System.out.print("Enter Account ID: ");
        int accountId = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter Account Password: ");
        String pwd = scan.nextLine();
        System.out.println("Please wait while we check your account...");

        AccountService as = new AccountService();
        boolean isValid = as.validateAccount(accountId, pwd);

        if (!isValid) {
            System.out.println("Account validation failed. Please try again.");
            return -1;
        }

        return accountId;
    }
}
