package bsit2e_g1.main;

import bsit2e_g1.datastructures.AccountList;
import bsit2e_g1.ui.AccountCreation;
import bsit2e_g1.ui.BankReports;
import bsit2e_g1.ui.BankingOperation;
import bsit2e_g1.ui.Menu;
import bsit2e_g1.utils.InputHelper;

public class BankingSystem {
	public static void main(String[] args) throws InterruptedException {

		AccountList bank = new AccountList();

		System.out.println("ALABPoint ATM Banking System initialized...\n");

		Thread.sleep(1000);

		while (true) {
			System.out.println("========================================");
			System.out.println("      ALABPOINT | ATM BANKING SYSTEM");
			System.out.println("========================================\n");
			System.out.println("[1] Login");
			System.out.println("[2] Sign Up");
			System.out.println("[3] Bank Reports");
			System.out.println("[0] Exit\n");

			int choice = InputHelper.readInt("Choice: ", 0, 3);

			System.out.println();

			BankingOperation menu = null;

			if (choice == 0) {
				break;
			} else if (choice == 1) {
				menu = new Menu(bank);
			} else if (choice == 2) {
				menu = new AccountCreation(bank);
			} else if (choice == 3) {
				menu = new BankReports(bank);
			}

			if (menu != null) {
				menu.execute();
			}

			System.out.println();
		}

		System.out.println("[ALABPoint] Thank you for banking with us.\n");

		Thread.sleep(1000);
	}
}
