package com.pmc.atm.service;

import com.pmc.atm.menu.DebitCreditMenu;
import com.pmc.atm.model.Account;

import java.util.Scanner;

public class TransactionService {
    public void performCredit(int , int atmId){

        DebitCreditMenu dcm = new DebitCreditMenu();

        boolean isAccountValidate = dcm.isAccountValidate(scan);
        System.out.println(isAccountValidate);

        if (isAccountValidate) {
            Account account = dcm.takeInputs(scan);

        } else {
            System.out.println("Look's good.");
        }
    }

    public void performDebit(Scanner scan, int amount) {
        AccountService accsservice = new AccountService();
        AtmService atmservice = new AtmService();

        System.out.print("Enter Account ID: ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter Account Password: ");
        String pwd = scan.nextLine();
    }
}
