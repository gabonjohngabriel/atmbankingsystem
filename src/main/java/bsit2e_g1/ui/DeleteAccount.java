package bsit2e_g1.ui;

import bsit2e_g1.datastructures.AccountList;
import bsit2e_g1.nodes.AccountData;
import bsit2e_g1.utils.InputHelper;

public class DeleteAccount extends BankingOperation {
	private final AccountList bank;
	private final AccountData currentAccount;

	public DeleteAccount(AccountData currentAccount, AccountList bank) {
		this.bank = bank;
		this.currentAccount = currentAccount;
	}

	@Override
	public void execute() {
		AccountData deletedAccount;

		System.out.println("========================================");
		System.out.println("            DELETE ACCOUNT");
		System.out.println("========================================\n");

		// Verify user's PIN
		if (bank.checkPin(currentAccount)) {

			// Ask user's confirmation to delete
			if (!InputHelper.choice("Are you sure you want to delete your account?")) {
				System.out.println();
				return;
			}

			// Remove current account
			deletedAccount = bank.deleteAccount(currentAccount.getAccountId());

			System.out.printf("[ALABPoint] Account successfully deleted! ID: %s\n", deletedAccount.getAccountId());

			System.out.println();
		}
	}
}


