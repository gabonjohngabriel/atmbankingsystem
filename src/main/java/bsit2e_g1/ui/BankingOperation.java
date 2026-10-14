package bsit2e_g1.ui;

import bsit2e_g1.nodes.AccountData;

public abstract class BankingOperation {
	public abstract void execute();

	public static void printReceipt(String transactionType, String accountId, String holder, double amount, double balance, String currentTime) {
		System.out.println("========================================");
		System.out.println("          TRANSACTION RECEIPT           ");
		System.out.println("========================================");
		System.out.printf("Transaction Type  : %s\n", transactionType);
		System.out.printf("Account ID        : %s\n", accountId);
		System.out.printf("Account Holder    : %s\n", holder);
		if (amount > 0) {
			System.out.printf("Amount            : PHP %.2f\n", amount);
		}
		System.out.printf("Available Balance : PHP %.2f\n", balance);
		System.out.printf("Date/Time         : %s\n", currentTime);
		System.out.println("Status            : SUCCESS / PRINTED");
		System.out.println("========================================\n");
	}

	public static void printTransferReceipt(AccountData sender, AccountData recipient, double amount, double senderBalanceAfter, String currentTime) {
		System.out.println("========================================");
		System.out.println("         FUND TRANSFER RECEIPT          ");
		System.out.println("========================================");
		System.out.printf("Transaction Type  : Fund Transfer\n");
		System.out.printf("Sender Account    : %s\n", sender.getAccountId());
		System.out.printf("Sender Name       : %s\n", sender.getName());
		System.out.printf("Recipient Account : %s\n", recipient.getAccountId());
		System.out.printf("Recipient Name    : %s\n", recipient.getName());
		System.out.printf("Transfer Amount   : PHP %.2f\n", amount);
		System.out.printf("Remaining Balance : PHP %.2f\n", senderBalanceAfter);
		System.out.printf("Date/Time         : %s\n", currentTime);
		System.out.println("Status            : SUCCESS / PRINTED");
		System.out.println("========================================\n");
	}
}
