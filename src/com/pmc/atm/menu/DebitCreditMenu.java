package com.pmc.atm.menu;

import com.pmc.atm.ApplicationMain;
import com.pmc.atm.dao.AccountDao;
import com.pmc.atm.model.Atm;

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
        System.out.print("Enter Account ID: ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter Account Password: ");
        String pwd = scan.nextLine();

        boolean status = false;
        try {
            status = accountDao.checkAccountStatus(id, pwd);
        } catch (Exception e){
            e.printStackTrace();
        }

        if(status){
            System.out.println("Enter 1 -> Debit Money");
            System.out.println("Enter 2 -> Credit Money");
            System.out.println("Enter 9 -> back");
            System.out.println("Enter 0 -> Exit");
            System.out.print("Enter any number: ");
            int option = scan.nextInt();

            switch (option) {
                case 1:
                    System.out.println("debit select");
                    break;
                case 2:
                    System.out.println("credit select");
                    break;
                case 9:
                    MainMenu.mainMenu(scan);
                    break;
                case 0:
                    ApplicationMain.exit();
                    break;
                default:
                    System.out.println("Please enter valid option.");
                    menu(scan);
            }
        } else {
            System.out.println("Account and Password does not match.");
        }
    }
}
