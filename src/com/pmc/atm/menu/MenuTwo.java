package com.pmc.atm.menu;

import java.util.Scanner;

import com.pmc.atm.ApplicationMain;

public class MenuTwo {
	public static void menu(Scanner scan) {
		System.out.println("===============================================================");
		System.out.println(" Check Balance ");
		System.out.println("===============================================================");
		System.out.println("Enter 1 -> ATM Balance");
		System.out.println("Enter 2 -> Account Balance");
		System.out.println("Enter 9 -> back");
		System.out.println("Enter 0 -> Exit");

		System.out.print("Enter any number: ");
		int option = scan.nextInt();

		switch (option) {
		case 1:
			MenuFour.menu(scan);
			break;
		case 2:
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
