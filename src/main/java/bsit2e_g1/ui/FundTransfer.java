package bsit2e_g1.ui;

import bsit2e_g1.datastructures.AccountList;
import bsit2e_g1.nodes.AccountData;
import bsit2e_g1.utils.InputHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FundTransfer extends BankingOperation {
	private final AccountList bank;
	private final AccountData currentAccount;

	public FundTransfer(AccountData currentAccount, AccountList bank) {
		this.bank = bank;
		this.currentAccount = currentAccount;
	}

	@Override
	public void execute() {

		System.out.println("========================================");
		System.out.println("              FUND TRANSFER             ");
		System.out.println("========================================\n");

		AccountData sender = currentAccount;

		String recipientId = InputHelper.readString("Enter account number to transfer: ");
		System.out.println();

		AccountData recipient = bank.findAccount(recipientId);

		if (recipient == null) {
			System.out.println("[ALABPoint] Recipient account not found.\n");
			return;
		}

		if (sender.getAccountId().equalsIgnoreCase(recipient.getAccountId())) {
			System.out.println("[ALABPoint] You cannot transfer funds to your own account.\n");
			return;
		}

		// Confirm the recipient name
		System.out.printf("[ALABPoint] Recipient Name: %s\n\n", recipient.getName());

		if (!InputHelper.choice("Confirm transfer?")) {
			return;
		}

		System.out.println();

		double amount = InputHelper.readDouble("[ALABPoint] Enter the amount to transfer (Min PHP 1.00): PHP ", 1.0);
		System.out.println();

		if (sender.getBalance() < amount) {
			System.out.printf("[ALABPoint] Insufficient balance! Your current balance is PHP %.2f.\n\n", sender.getBalance());
			return;
		}

		// Pin verification
		System.out.println("[ALABPoint] Please verify your PIN to confirm transfer.\n");

		if (!bank.checkPin(sender)) {
			System.out.println("[ALABPoint] Transfer cancelled due to failed PIN verification.\n");
			return;
		}

		// Get the current time
		String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		bank.transferFunds(sender, recipient, amount, currentTime);

		// Print transaction receipt
		if (InputHelper.choice("Would you like to print/view a transaction receipt?")) {
			System.out.println();
			printTransferReceipt(sender, recipient, amount, sender.getBalance(), currentTime);
		}
	}
}


