package com.pmc.atm.menu;

import com.pmc.atm.ApplicationMain;
import com.pmc.atm.dao.AccountDao;
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
        System.out.println(" Enter Account ID and Password For Transaction ");
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
        AccountService accountService = new AccountService();
        int accountId = validateAndGetAccountId(scan);

        if (accountId != -1) {
            System.out.print("Enter the amount to debit: ");
            int amount = scan.nextInt();
            TransactionService ts = new TransactionService();
            boolean debitSuccess = ts.performDebit(accountId, amount);

            if (debitSuccess) {
                System.out.println("Debit successful.");
            } else {
                System.out.println("Debit failed.");
            }
        }

        // After the transaction, return to the main menu
        SelectAtmMenu sam = new SelectAtmMenu();
        sam.menu(scan);
    }

    private void performCredit(Scanner scan) {
        TransactionService ts = new TransactionService();
        int accountId = validateAndGetAccountId(scan);

        if (accountId != -1) {
            System.out.print("Enter the amount to credit: ");
            int amount = scan.nextInt();
            boolean creditSuccess = ts.performCredit(accountId, amount);

            if (creditSuccess) {
                System.out.println("Credit successful.");
            } else {
                System.out.println("Credit failed.");
            }
        }

        // After the transaction, return to the main menu
        SelectAtmMenu sam = new SelectAtmMenu();
        sam.menu(scan);
    }

    private int validateAndGetAccountId(Scanner scan) {
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
