package com.pmc.atm.menu;

import java.util.Scanner;

import com.pmc.atm.ApplicationMain;
import com.pmc.atm.model.Bank;

public class MenuThree {
	public static void menu(Scanner scan) {
		System.out.println("===============================================================");
		System.out.println(" Add Or Update - ATM/Bank/Customer detail ");
		System.out.println("===============================================================");
		System.out.println("Enter 1 -> Edit Customer Account");
		System.out.println("Enter 2 -> Edit ATM");
		System.out.println("Enter 3 -> Edit Bank");
		System.out.println("Enter 9 -> Back");
		System.out.println("Enter 0 -> Exit");

		System.out.print("Enter any number: ");
		int option = scan.nextInt();

		switch (option) {
		case 1:
			EditCustomerAccountMenu.menu(scan);
			break;
		case 2:
			EditATMMenu.menu(scan);
			break;
		case 3:
			EditBankMenu.menu(scan);
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
	}
}
