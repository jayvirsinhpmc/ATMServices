package com.pmc.atm.menu;

import com.pmc.atm.ApplicationMain;
import com.pmc.atm.dao.AtmDao;
import com.pmc.atm.model.Atm;

import java.util.List;
import java.util.Scanner;

public class SelectAtmMenu {

    public void menu(Scanner scan) {
        AtmDao atmDao = new AtmDao();

        System.out.println("===============================================================");
        System.out.println("Select ATM");
        System.out.println("===============================================================");
        List<Atm> atms = atmDao.getAllAtmDetails();
        displayAtms(atms);

        System.out.print("Select an ATM (Enter ATM number): ");
        int selectedAtmNumber = scan.nextInt();

        // Validate the selected ATM number
        if (selectedAtmNumber >= 1 && selectedAtmNumber <= atms.size()) {
            Atm selectedAtm = atms.get(selectedAtmNumber - 1); // Adjust for 0-based index
            System.out.println("Selected ATM: " + selectedAtm.getName());

            // Pass the selected ATM to the debit or credit menu
            DebitCreditMenu debitCreditMenu = new DebitCreditMenu(selectedAtm);
            debitCreditMenu.menu(scan);
        } else {
            System.out.println("Invalid ATM selection. Please enter a valid ATM number.");
            menu(scan);
        }
        System.out.println("Enter 9 -> Back");
        System.out.println("Enter 0 -> Exit");

        int option = scan.nextInt();

        switch (option) {
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
    private void displayAtms(List<Atm> atms) {
        System.out.println("Available ATMs:");
        for (int i = 0; i < atms.size(); i++) {
            System.out.println((i + 1) + ": " + atms.get(i).getName());
        }
    }
}
