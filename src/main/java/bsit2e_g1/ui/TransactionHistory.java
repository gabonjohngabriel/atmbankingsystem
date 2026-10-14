package bsit2e_g1.ui;

import bsit2e_g1.nodes.AccountData;
import bsit2e_g1.nodes.TransactionData;
import bsit2e_g1.utils.InputHelper;

public class TransactionHistory extends BankingOperation {
	private final AccountData currentAccount;

	public TransactionHistory(AccountData currentAccount) {
		this.currentAccount = currentAccount;
	}

	@Override
	public void execute() {

		System.out.println("================================================================================");
		System.out.println("                              TRANSACTION HISTORY                               ");
		System.out.println("================================================================================");

		// Traverses and prints transactions from the user's account
		currentAccount.getTransactions().displayTransactions();

		if (!currentAccount.getTransactions().isEmpty()) {
			if (InputHelper.choice("Would you like to reprint your latest receipt?")) {
				System.out.println();

				TransactionData latestTransaction = currentAccount.getTransactions().getLastTransaction();

				if (latestTransaction != null) {
					printReceipt(latestTransaction.getTransactionType(), currentAccount.getAccountId(), currentAccount.getName(), latestTransaction.getAmount(), latestTransaction.getBalanceAfter(), latestTransaction.getTimeStamp());
				}
			}
		}

		System.out.println();
	}
}
