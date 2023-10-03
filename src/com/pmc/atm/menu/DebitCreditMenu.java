package com.pmc.atm.menu;

import com.pmc.atm.ApplicationMain;
import com.pmc.atm.dao.AccountDao;
import com.pmc.atm.model.Atm;
import com.pmc.atm.service.AccountService;
import com.pmc.atm.service.AtmService;

import java.util.Scanner;

public class DebitCreditMenu {

    Atm selectedAtm;
    AccountDao accountDao;
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
        System.out.println("Enter 9 -> back");
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
                System.out.println("Please enter valid option.");
                menu(scan);

        }
    }

    private static boolean isAccountValidate(Scanner scan) {
        AccountService as = new AccountService();

        System.out.print("Enter Account ID: ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter Account Password: ");
        String pwd = scan.nextLine();
        System.out.println("Please wait while we check your account...");

        boolean isAccountActive = as.isAccountActive(id);
        boolean isIDAndPasswordMatch = as.isAccountAndPasswordMatch(id, pwd);

        if (isAccountActive) {
            System.out.println("Your account is inactive. Please contact your bank.");
            return false; // Return false if validation fails
        } else if (isIDAndPasswordMatch) {
            System.out.println("Your Account ID and Password do not match. Try again.");
            return false; // Return false if validation fails
        }
        return true; // Return true if validation passes
    }

    private static void performCredit(Scanner scan){

        boolean isAccountValidate = isAccountValidate(scan);
        System.out.println(isAccountValidate);

        if (isAccountValidate) {
            AccountService accService = new AccountService();
            AtmService atmService = new AtmService();
        } else {
            System.out.println("Look's good.");
        }
    }

    private static void performDebit(Scanner scan) {
        AccountService accsservice = new AccountService();
        AtmService atmservice = new AtmService();

        System.out.print("Enter Account ID: ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter Account Password: ");
        String pwd = scan.nextLine();
    }
}
