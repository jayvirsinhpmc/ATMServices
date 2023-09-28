package com.pmc.atm.menu;

import java.util.Scanner;

import com.pmc.atm.ApplicationMain;

public class MainMenu {
	public static void mainMenu(Scanner scan) {
		System.out.println("===============================================================");
		System.out.println(" Welcome to PMC ATM Service ");
		System.out.println("===============================================================");
		System.out.println("Enter 1 -> Do Transaction");
		System.out.println("Enter 2 -> Check Balance");
		System.out.println("Enter 3 -> Add Or Update - ATM/Bank/Customer detail");
		System.out.println("Enter 0 -> Exit");

		System.out.print("Enter any number: ");
		int option = scan.nextInt();

		switch (option) {
		case 1:
			MenuOne mo = new MenuOne();
			mo.menu(scan);
			break;
		case 2:
			MenuTwo.menu(scan);
			break;
		case 3:
			MenuThree.menu(scan);
			break;
		case 0:
			ApplicationMain.exit();
			break;
		default:
			System.out.println("Please enter valid option.");
			mainMenu(scan);
		}

	}
}
