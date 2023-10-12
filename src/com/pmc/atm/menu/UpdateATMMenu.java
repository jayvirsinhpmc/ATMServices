package com.pmc.atm.menu;

import com.pmc.atm.model.Atm;
import com.pmc.atm.service.AtmService;

import java.util.Scanner;

public class UpdateATMMenu {
    public void updateAtm (Scanner scan) {

        System.out.print("Enter ATM Name: ");
        String atmName = scan.nextLine();
        scan.nextLine();
        System.out.print("Enter ATM Password: ");
        String atmPassword = scan.nextLine();

        // Retrieve the ATM based on the provided name and password
        AtmService atmService = new AtmService();
        Atm atm = atmService.getAtmUsingNameAndPwd(atmName, atmPassword);

        // Check if the ATM exists
        if (atm != null) {
            System.out.println("ATM Found: " + atm.getName());
            System.out.println("ATM Balance: " + atm.getBalance());

            // Display a menu of fields to update
            System.out.println("Select fields to update:");
            System.out.println("1. Name");
            System.out.println("2. Password");
            System.out.println("3. Balance");
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
                    atm.setName(newName); // Update the ATM's name
                } else if (choice == 2) {
                    scan.nextLine(); // Consume newline
                    System.out.print("Enter new password: ");
                    String newPwd = scan.nextLine();
                    atm.setPwd(newPwd); // Update the ATM's password
                } else if (choice == 3) {
                    System.out.print("Enter new balance: ");
                    int newBalance = scan.nextInt();
                    atm.setBalance(newBalance); // Update the ATM's balance
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }

            // Save the updated ATM entity
            if (atmService.updateATM(atm)) {
                System.out.println("ATM updated successfully.");
                EditATMMenu.menu(scan);
            }
        } else {
            System.out.println("ATM not found. Please check your name and password.");
            updateAtm(scan);
        }
    }

}
