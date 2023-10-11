package com.pmc.atm.menu;

import com.pmc.atm.ApplicationMain;
import com.pmc.atm.dao.AccountDao;
import com.pmc.atm.dao.BankDao;
import com.pmc.atm.dao.CustomerDao;
import com.pmc.atm.model.Account;
import com.pmc.atm.model.Atm;
import com.pmc.atm.model.Bank;
import com.pmc.atm.model.Customer;
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
        System.out.println("Enter 2 -> Edit Customer Account");
        System.out.println("Enter 9 -> Back");
        System.out.println("Enter 0 -> Exit");

        System.out.print("Enter any number: ");
        int option = scan.nextInt();

        switch (option) {
            case 1:
                EditCustomerAccountMenu menu = new EditCustomerAccountMenu();
                menu.getCustomerAccountDetails(scan);
                break;
            case 2:
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

    private void getCustomerAccountDetails(Scanner scan) {
        BankDao bankDao = new BankDao();
        List<Bank> banks = bankDao.getAllBankDetails();
        displayBank(banks);

        System.out.print("Select a Bank (Enter Bank number): ");
        int selectedBankNumber = scan.nextInt();

        // Validate the selected ATM number
        if (selectedBankNumber >= 1 && selectedBankNumber <= banks.size()) {
            Bank selectedBank = banks.get(selectedBankNumber - 1); // Adjust for 0-based index
            System.out.println("Selected Bank: " + selectedBank.getName());

            System.out.print("Enter your Name: ");
            String name = scan.nextLine();

            System.out.println("Enter 1 for Saving and any other number for Salary");
            System.out.print("Enter your choice: ");
            int accountType = scan.nextInt();
            scan.nextLine(); // Consume the newline character

            System.out.print("Enter your account password: ");
            String accountPwd = scan.nextLine();

            System.out.println("Enter amount you want to add in the account: ");
            int accountBalance = scan.nextInt();

            AccountService accountService = new AccountService();
            accountService.addNewAccount(selectedBank.getId(), accountType == 1 ? "Saving" : "Salary", accountPwd, accountBalance);

            CustomerServices customerServices = new CustomerServices();
            customerServices.addNewCustomer(name, )

//            Account account = new Account();
//            account.setBankId(selectedBank.getId());
//            account.setAccountType(accountType == 1 ? "Saving" : "Salary");
//            account.setAccountStatus("Active");
//            account.setAccountPwd(accountPwd);
//            account.setBalance(accountBalance);
//
//            AccountDao accountDao = new AccountDao();
//            boolean isNewAccountAdded = accountDao.isNewAccountAdded(account);
//            int accountId = account.getId();

//            Customer customer = new Customer();
//            customer.setName(name);
//            customer.setAccountId(accountId);
//
//            CustomerDao customerDao = new CustomerDao();
//            boolean isNewCustomerAdded = customerDao.isNewCustomerAdded(customer);

        } else {
            System.out.println("Invalid Bank selection. Please enter a valid Bank number.");
            menu(scan);
        }
    }
    private void displayBank(List<Bank> banks) {
        System.out.println("Available Banks:");
        for (int i = 0; i < banks.size(); i++) {
            System.out.println((i + 1) + ": " + banks.get(i).getName());
        }
    }
}
