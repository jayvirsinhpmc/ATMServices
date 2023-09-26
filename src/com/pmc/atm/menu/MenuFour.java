package com.pmc.atm.menu;

import java.util.Scanner;

import com.pmc.atm.ApplicationMain;
import com.pmc.atm.service.AtmService;

public class MenuFour {
	public static void menu(Scanner scan) {
		System.out.println("===============================================================");
		System.out.println(" ATM Balance ");
		System.out.println("===============================================================");
		System.out.println("Enter 1 -> Enter ATM name");
		System.out.println("Enter 9 -> back");
		System.out.println("Enter 0 -> Exit");

		System.out.print("Enter any number: ");
		int option = scan.nextInt();

		switch (option) {
		case 1:
			enterAtmNumber(scan);
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

	private static void enterAtmNumber(Scanner scan) {
		System.out.print("Enter ATM name and Press ENTER: ");
		String atmName = scan.nextLine();
		if (null == atmName || atmName.isEmpty()) {
		} else {
			AtmService atmService = new AtmService();
			atmService.getAtmDetail(atmName);
		}
	}
}
