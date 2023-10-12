package com.pmc.atm.menu;

import com.pmc.atm.ApplicationMain;
import com.pmc.atm.service.BankService;

import java.util.Scanner;

public class EditBankMenu {
    public static void menu(Scanner scan) {
        System.out.println("===============================================================");
        System.out.println(" Add Or Update - Bank detail ");
        System.out.println("===============================================================");
        System.out.println("Enter 1 -> Add New Bank");
        System.out.println("Enter 2 -> Update Bank");
        System.out.println("Enter 9 -> Back");
        System.out.println("Enter 0 -> Exit");

        System.out.print("Enter any number: ");
        int option = scan.nextInt();

        switch (option) {
            case 1:
                EditBankMenu menu = new EditBankMenu();
                menu.getAndAddBankDetails(scan);
                break;
            case 2:
                UpdateBankMenu updateBankMenu = new UpdateBankMenu();
                updateBankMenu.updateBank(scan);
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

    private void getAndAddBankDetails(Scanner scan) {
        scan.nextLine();
        System.out.print("Enter Bank Name: ");
        String bankName = scan.nextLine();

        System.out.print("Enter Bank password: ");
        String bankPwd = scan.nextLine();

        BankService bankService = new BankService();

        if(bankService.addNewBank(bankName, bankPwd)) {
            System.out.println("Your Bank is created");
            menu(scan);
        } else {
            System.out.println("Something went wrong..");
        }
    }
}
