package com.pmc.atm.menu;

import com.pmc.atm.ApplicationMain;
import com.pmc.atm.dao.BankDao;
import com.pmc.atm.model.Bank;
import com.pmc.atm.service.AccountService;
import com.pmc.atm.service.CustomerServices;

import java.util.List;
import java.util.Scanner;

public class EditCustomerAccountMenu {
    public static void menu(Scanner scan) {
        System.out.println("===============================================================");
        System.out.println(" Add Or Update - Customer detail ");
        System.out.println("===============================================================");
        System.out.println("Enter 1 -> Add New Customer Account");
        System.out.println("Enter 2 -> Update Customer Account");
        System.out.println("Enter 9 -> Back");
        System.out.println("Enter 0 -> Exit");

        System.out.print("Enter any number: ");
        int option = scan.nextInt();

        switch (option) {
            case 1:
                EditCustomerAccountMenu menu = new EditCustomerAccountMenu();
                menu.getAndAddCustomerDetails(scan);
                break;
            case 2:
                UpdateAccountMenu updateAccountMenu = new UpdateAccountMenu();
                updateAccountMenu.updateAccount(scan);
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

    private void getAndAddCustomerDetails(Scanner scan) {
        BankDao bankDao = new BankDao();
        List<Bank> banks = bankDao.getAllBankDetails();
        displayBank(banks);

        System.out.print("Select a Bank (Enter Bank number): ");
        int selectedBankNumber = scan.nextInt();
        scan.nextLine();

        // Validate the selected ATM number
        if (selectedBankNumber >= 1 && selectedBankNumber <= banks.size()) {
            Bank selectedBank = banks.get(selectedBankNumber - 1); // Adjust for 0-based index
            System.out.println("Selected Bank: " + selectedBank.getName());

            System.out.print("Enter your Name: ");
            String name = scan.nextLine();

            System.out.println("Enter 1 for Saving and any other number for Salary");
            System.out.print("Enter number: ");
            int accountType = scan.nextInt();
            scan.nextLine(); // Consume the newline character

            System.out.print("Enter your account password: ");
            String accountPwd = scan.nextLine();

            System.out.print("Enter amount you want to add in the account: ");
            int accountBalance = scan.nextInt();

            AccountService accountService = new AccountService();
            int accountId = accountService.addNewAccount(selectedBank.getId(), accountType == 1 ? "Saving" : "Salary", accountPwd, accountBalance);
            if(accountId > 0) {
                CustomerServices customerServices = new CustomerServices();
                boolean status = customerServices.addNewCustomer(name, accountId);
                if(status) {
                    System.out.println("Your account is created");
                    menu(scan);
                }
            } else {
                System.out.println("Something went wrong..");
            }

        } else {
            System.out.println("Invalid Bank selection. Please enter a valid Bank number.");
            getAndAddCustomerDetails(scan);
        }
    }
    private void displayBank(List<Bank> banks) {
        System.out.println("Available Banks:");
        for (int i = 0; i < banks.size(); i++) {
            System.out.println((i + 1) + ": " + banks.get(i).getName());
        }
    }
}
