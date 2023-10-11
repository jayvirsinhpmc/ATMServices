package com.pmc.atm.menu;

import java.util.List;
import java.util.Scanner;

import com.pmc.atm.ApplicationMain;
import com.pmc.atm.dao.AtmDao;
import com.pmc.atm.model.Atm;
import com.pmc.atm.service.AtmService;

public class MenuFour {

	private AtmDao atmDao;

	public MenuFour() {

		atmDao = new AtmDao();
	}

	public static void menu(Scanner scan) {
		System.out.println("===============================================================");
		System.out.println(" ATM Balance ");
		System.out.println("===============================================================");
		System.out.println("Enter 1 -> To select ATM");
		System.out.println("Enter 9 -> back");
		System.out.println("Enter 0 -> Exit");

		System.out.print("Enter any number: ");
		int option = scan.nextInt();

		switch (option) {
		case 1:
			MenuFour menuFour = new MenuFour();
			menuFour.enterAtmNumber(scan);
//			MenuFour.menu(scan);
			break;
		case 9:
			MenuTwo.menu(scan);
			break;
		case 0:
			ApplicationMain.exit();
			break;
		default:
			System.out.println("Please enter valid option.");
			menu(scan);
		}
	}

	private void enterAtmNumber(Scanner scan) {
		List<Atm> atms = atmDao.getAllAtmDetails();
		displayAtms(atms);

		System.out.print("Select an ATM (Enter ATM number): ");
		int selectedAtmNumber = scan.nextInt();

		// Validate the selected ATM number
		if (selectedAtmNumber >= 1 && selectedAtmNumber <= atms.size()) {
			Atm selectedAtm = atms.get(selectedAtmNumber - 1); // Adjust for 0-based index
			System.out.println("Selected ATM: " + selectedAtm.getName());
			scan.nextLine();
			System.out.print("Enter ATM Password: ");
			String atmPwd = scan.nextLine();

			AtmService atmService = new AtmService();
			boolean isAtmValidate = atmService.isAtmValidate(selectedAtm.getId(), atmPwd);
			if(isAtmValidate) {
				Atm atm = atmDao.getAtmByIDAndPwd(selectedAtm.getId(), atmPwd);
				int atmBalance = atm.getBalance();
				System.out.println("ATM balance is: " + atmBalance);
				MenuTwo.menu(scan);
			} else {
				System.out.println("Unable to find account details. Please try again.");
				enterAtmNumber(scan);
			}
		} else {
			System.out.println("Invalid ATM selection. Please enter a valid ATM number.");
			enterAtmNumber(scan);
		}
	}

	private void displayAtms(List<Atm> atms) {
		System.out.println("Available ATMs:");
		for (int i = 0; i < atms.size(); i++) {
			System.out.println((i + 1) + ": " + atms.get(i).getName());
		}
	}
}
