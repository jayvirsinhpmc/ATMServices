package com.pmc.atm.menu;

import com.pmc.atm.model.Bank;
import com.pmc.atm.service.BankService;

import java.util.Scanner;

public class UpdateBankMenu {
    public void updateBank (Scanner scan) {

        System.out.print("Enter Bank Name: ");
        String bankName = scan.nextLine();
        scan.nextLine();
        System.out.print("Enter Bank Password: ");
        String bankPassword = scan.nextLine();

        // Retrieve the ATM based on the provided name and password
        BankService bankService = new BankService();
        Bank bank = bankService.getBankUsingNameAndPwd(bankName, bankPassword);

        // Check if the ATM exists
        if (bank != null) {
            System.out.println("ATM Found: " + bank.getName());

            // Display a menu of fields to update
            System.out.println("Select fields to update:");
            System.out.println("1. Name");
            System.out.println("2. Password");
            System.out.println("0. Done");

            while (true) {
                System.out.print("Enter your choice: ");
                int choice = scan.nextInt();

                if (choice == 0) {
                    break; // User is done updating fields
                } else if (choice == 1) {
                    scan.nextLine(); // Consume newline
                    System.out.print("Enter new name: ");
                    String newName = scan.nextLine();
                    bank.setName(newName); // Update the ATM's name
                } else if (choice == 2) {
                    scan.nextLine(); // Consume newline
                    System.out.print("Enter new password: ");
                    String newPwd = scan.nextLine();
                    bank.setPwd(newPwd); // Update the ATM's password
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }

            // Save the updated ATM entity
            if (bankService.isBankUpdate(bank)) {
                System.out.println("Bank updated successfully.");
                EditATMMenu.menu(scan);
            }
        } else {
            System.out.println("Bank not found. Please check your name and password.");
            updateBank(scan);
        }
    }

}
