package com.pmc.atm;

import java.util.Scanner;

import com.pmc.atm.menu.MainMenu;

public class ApplicationMain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		MainMenu.mainMenu(scan);
		scan.close();
	}

	public static void exit() {
		System.out.println("Thanks for using PMC ATM service.");
		System.exit(0);
	}

}
