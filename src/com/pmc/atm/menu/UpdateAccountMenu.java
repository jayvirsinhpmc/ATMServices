package com.pmc.atm.menu;

import com.pmc.atm.model.Account;
import com.pmc.atm.model.Customer;
import com.pmc.atm.service.AccountService;
import com.pmc.atm.service.BankService;
import com.pmc.atm.service.CustomerServices;

import java.util.Scanner;

public class UpdateAccountMenu {
    public void updateAccount (Scanner scan) {

        System.out.print("Enter Account Id: ");
        int accountId = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter Account Password: ");
        String accountpwd = scan.nextLine();

        // Retrieve the Account based on the provided Id and password
        AccountService accountService = new AccountService();
        Account account = accountService.getAccountByIdAndPwd(accountId, accountpwd);

        // Retrieve the Bank name
        int bankId = account.getBankId();
        BankService bankService = new BankService();
        String bankName = bankService.getBankName(bankId);

        // Retrieve customer name
        int fatchedAccountId = account.getId();
        CustomerServices customerServices = new CustomerServices();
        String customerName = customerServices.getCustomerName(fatchedAccountId);

        Customer customer = new Customer();

        // Check if the ATM exists
        if (account != null) {
            System.out.println("Account Found: " + account.getId());
            System.out.println("Customer Name: " + customerName);
            System.out.println("Bank Name: " + bankName);
            System.out.println("Account Balance = " + account.getBalance());
            System.out.println("Account Status = " + account.getAccountStatus());
            System.out.println("Account Type = " + account.getAccountType());

            // Display a menu of fields to update
            System.out.println("Select fields to update:");
            System.out.println("1. Name");
            System.out.println("2. Status");
            System.out.println("3. Type");
            System.out.println("4. Password");
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

                    customer.setName(newName);
                    customer.setId(fatchedAccountId);

                } else if (choice == 2) {

                    System.out.println("Enter 1 for Active and any other number for Deactive");
                    System.out.print("Enter number: ");
                    int accountStatus = scan.nextInt();
                    scan.nextLine();
                    account.setAccountStatus(accountStatus == 1 ? "Active" : "Deactive"); // Update the account's status

                } else if (choice == 3) {

                    System.out.println("Enter 1 for Saving and any other number for Salary");
                    System.out.print("Enter number: ");
                    int accountType = scan.nextInt();
                    scan.nextLine();
                    account.setAccountType(accountType == 1 ? "Saving" : "Salary"); // Update the account's type

                } else if (choice == 4) {

                    System.out.print("Enter new password: ");
                    String newPwd = scan.nextLine();
                    account.setAccountPwd(newPwd); // Update the account's password

                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }

            System.out.println("---------------------------------------------------");
            System.out.println("Account Found: " + account.getId());
            System.out.println("Customer Name: " + newN);
            System.out.println("Bank Name: " + bankName);
            System.out.println("Account Balance = " + account.getBalance());
            System.out.println("Account Status = " + account.getAccountStatus());
            System.out.println("Account Type = " + account.getAccountType());

            System.out.println("---------------------------------------------------");

            // Save the updated ATM entity
            System.out.println(accountService.isAccountUpdated(account));
            System.out.println(customerServices.updateCustomer(customer));


            if (accountService.isAccountUpdated(account)) {
                System.out.println("Account updated successfully.");
                // Now, update the associated customer information
                if (customerServices.updateCustomer(customer)) {
                    System.out.println("Customer information updated successfully.");
                } else {
                    System.out.println("Failed to update customer information.");
                }
                EditCustomerAccountMenu.menu(scan);
            } else {
                System.out.println("Account is not updated. Please try again.");
                updateAccount(scan);
            }
        } else {
            System.out.println("Account not found. Please check your ID and password.");
            updateAccount(scan);
        }
    }
}
