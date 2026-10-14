package bsit2e_g1.ui;

import bsit2e_g1.datastructures.AccountList;
import bsit2e_g1.nodes.AccountData;
import bsit2e_g1.utils.InputHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Menu extends BankingOperation {
	private final AccountList bank;

	public Menu(AccountList bank) {
		this.bank = bank;
	}

	@Override
	public void execute() {

		System.out.println("========================================");
		System.out.println("           ALABPOINT | LOGIN");
		System.out.println("========================================\n");

		String accountId = InputHelper.readString("Enter your account ID: ");
		System.out.println();

		AccountData currentAccount = bank.findAccount(accountId);

		// Account validation
		if (currentAccount == null) {
			System.out.println("[ALABPoint] Account not found. Please try again.");
			return;
		}

		// PIN validation
		if (bank.checkPin(currentAccount)) {
			MainMenu(currentAccount, bank);
		}

	}

	private static void MainMenu(AccountData currentAccount, AccountList bank) {

		while (true) {
			System.out.println("========================================");
			System.out.println("     ALABPOINT | ATM BANKING SYSTEM");
			System.out.println("========================================");
			System.out.printf("Hello, %s! (%s)\n", currentAccount.getName(), currentAccount.getAccountId());
			System.out.printf("Time: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			System.out.println("----------------------------------------");
			System.out.println("[1] Balance Inquiry");
			System.out.println("[2] Deposit");
			System.out.println("[3] Withdrawal");
			System.out.println("[4] Fund Transfer");
			System.out.println("[5] Transaction History");
			System.out.println("[6] Account Information");
			System.out.println("[7] Delete Account");
			System.out.println("[0] Sign Out");
			System.out.println("========================================");
			System.out.println();

			int choice = InputHelper.readInt("Choice: ", 0, 7);
			System.out.println();

			BankingOperation menu = null;

			if (choice == 0) {
				return;
			} else if (choice == 1) {
				menu = new BalanceInquiry(currentAccount); // DONE
			} else if (choice == 2) {
				menu = new Deposit(currentAccount, bank);
			} else if (choice == 3) {
				menu = new Withdrawal(currentAccount, bank);
			} else if (choice == 4) {
				menu = new FundTransfer(currentAccount, bank); // DONE
			} else if (choice == 5) {
				menu = new TransactionHistory(currentAccount); // DONE
			} else if (choice == 6) {
				menu = new AccountInformation(currentAccount, bank); // DONE
			} else if (choice == 7) {
				menu = new DeleteAccount(currentAccount, bank); // DONE
			}

			if (menu != null) {
				menu.execute();
			}

			// If account was deleted, log out automatically
			if (bank.findAccount(currentAccount.getAccountId()) == null) {
				System.out.println("[ALABPoint] Logging out...");
				return;
			}
		}
	}
}

