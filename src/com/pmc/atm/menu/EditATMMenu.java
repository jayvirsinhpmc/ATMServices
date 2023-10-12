package com.pmc.atm.menu;

import com.pmc.atm.ApplicationMain;
import com.pmc.atm.service.AtmService;
import java.util.Scanner;

public class EditATMMenu {
    public static void menu(Scanner scan) {
        System.out.println("===============================================================");
        System.out.println(" Add Or Update - ATM detail ");
        System.out.println("===============================================================");
        System.out.println("Enter 1 -> Add New ATM");
        System.out.println("Enter 2 -> Update ATM");
        System.out.println("Enter 9 -> Back");
        System.out.println("Enter 0 -> Exit");

        System.out.print("Enter any number: ");
        int option = scan.nextInt();

        switch (option) {
            case 1:
                EditATMMenu menu = new EditATMMenu();
                menu.getAndAddATMDetails(scan);
                break;
            case 2:
                UpdateATMMenu updateATMMenu = new UpdateATMMenu();
                updateATMMenu.updateAtm(scan);
                break;
            case 9:
                MenuThree.menu(scan);
                break;
            case 0:
                ApplicationMain.exit();
                break;
            default:
                System.out.println("Please enter valid option.");
                menu(scan);
        }
    }

    private void getAndAddATMDetails(Scanner scan) {
        scan.nextLine();
        System.out.print("Enter ATM Name: ");
        String atmName = scan.nextLine();

        System.out.print("Enter ATM password: ");
        String atmPwd = scan.nextLine();

        System.out.print("Enter amount you want to add in the ATM: ");
        int atmBalance = scan.nextInt();

        AtmService atmService = new AtmService();

        if(atmService.addNewATM(atmName, atmPwd, atmBalance)) {
            System.out.println("Your ATM is created");
            menu(scan);
        } else {
            System.out.println("Something went wrong..");
        }
    }
}
