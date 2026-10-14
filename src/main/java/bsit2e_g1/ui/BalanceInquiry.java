package bsit2e_g1.ui;

import bsit2e_g1.nodes.AccountData;
import bsit2e_g1.utils.InputHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BalanceInquiry extends BankingOperation {
	private final AccountData currentAccount;

	public BalanceInquiry(AccountData currentAccount) {
		this.currentAccount = currentAccount;
	}

	public void execute() {

		System.out.println("========================================");
		System.out.println("            BALANCE INQUIRY");
		System.out.println("========================================");
		System.out.printf("Account ID : %s\n", currentAccount.getAccountId());
		System.out.printf("Name       : %s\n", currentAccount.getName());
		System.out.printf("Balance    : PHP %.2f\n", currentAccount.getBalance());
		System.out.println("========================================");
		System.out.println();

		String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		currentAccount.getTransactions().addTransaction("Balance Inquiry", 0.0, currentAccount.getBalance(), currentTime);

		// Receipt generation
		if (InputHelper.choice("Would you like to print/view a transaction receipt?")) {
			System.out.println();
			printReceipt("Balance Inquiry", currentAccount.getAccountId(), currentAccount.getName(), 0.0, currentAccount.getBalance(), currentTime);
		}

		System.out.println();
	}
}
