package com.pmc.atm.menu;

import java.util.Scanner;
import com.pmc.atm.ApplicationMain;

public class MenuOne {
	public void menu(Scanner scan) {
		System.out.println("===============================================================");
		System.out.println(" Do Transaction ");
		System.out.println("===============================================================");
		System.out.println("Enter 1 -> Select ATM");
		System.out.println("Enter 9 -> back");
		System.out.println("Enter 0 -> Exit");
		System.out.print("Enter any number: ");
		int option = scan.nextInt();

		switch (option) {
		case 1:
			SelectAtmMenu sam = new SelectAtmMenu();
			sam.menu(scan);
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
