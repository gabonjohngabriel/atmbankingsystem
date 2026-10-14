package bsit2e_g1.ui;

import bsit2e_g1.datastructures.AccountList;
import bsit2e_g1.nodes.AccountData;

public class AccountInformation extends BankingOperation {
	private final AccountList bank;
	private final AccountData currentAccount;

	public AccountInformation(AccountData currentAccount, AccountList bank) {
		this.bank = bank;
		this.currentAccount = currentAccount;
	}

	@Override
	public void execute() {
		// Display current account's details
		bank.displayAccountDetails(currentAccount);

		// Update current account
		bank.updateAccount(currentAccount, bank);

		System.out.println();
	}
}


