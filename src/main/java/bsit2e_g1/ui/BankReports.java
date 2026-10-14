package bsit2e_g1.ui;

import bsit2e_g1.datastructures.AccountList;
import bsit2e_g1.nodes.AccountData;
import bsit2e_g1.utils.InputHelper;

public class BankReports extends BankingOperation {
	private final AccountList bank;

	public BankReports(AccountList bank) {
		this.bank = bank;
	}

	public void execute() {
		while (true) {
			System.out.println("========================================");
			System.out.println("          ALABPOINT | BANK REPORTS      ");
			System.out.println("========================================");
			System.out.println("[1] Bank Reserve & Summary");
			System.out.println("[2] View All Registered Accounts");
			System.out.println("[3] Inspect Account & Transaction Audit");
			System.out.println("[0] Back to Main Menu");
			System.out.println("========================================\n");

			int choice = InputHelper.readInt("Choice: ", 0, 3);
			System.out.println();

			if (choice == 0) {
				return;
			} else if (choice == 1) {
				bank.displayBankSummary();
			} else if (choice == 2) {
				bank.displayAllAccounts();
			} else if (choice == 3) {
				String accountId = InputHelper.readString("[ALABPoint] Enter bank account ID to audit: ");
				System.out.println();

				AccountData currentAccount = bank.findAccount(accountId);

				if (currentAccount == null) {
					System.out.println("[ALABPoint] Account not found.\n");
				} else {
					bank.displayAccountDetails(currentAccount);

					System.out.println("================================================================================");
					System.out.println("                            TRANSACTION HISTORY                                 ");
					System.out.println("================================================================================");
					currentAccount.getTransactions().displayTransactions();
				}
			}
		}
	}
}
