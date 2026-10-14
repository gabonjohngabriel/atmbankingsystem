package bsit2e_g1.ui;

import bsit2e_g1.datastructures.AccountList;
import bsit2e_g1.nodes.AccountData;
import bsit2e_g1.utils.InputHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Withdrawal extends BankingOperation {
	private final AccountList bank;
	private final AccountData currentAccount;

	public Withdrawal(AccountData currentAccount, AccountList bank) {
		this.currentAccount = currentAccount;
		this.bank = bank;
	}

	@Override
	public void execute() {

		if (currentAccount.getBalance() < 100.0) {
			System.out.printf("[ALABPoint] Insufficient funds. Your balance is PHP %.2f (Min. PHP 100.00).\n\n", currentAccount.getBalance());
			return;
		}

		System.out.println("========================================");
		System.out.println("                WITHDRAW                 ");
		System.out.println("========================================\n");
		System.out.println("Account Number  : " + currentAccount.getAccountId());
		System.out.println("Account Name    : " + currentAccount.getName());
		System.out.printf("Account Balance : PHP %.2f\n", currentAccount.getBalance());
		System.out.println("========================================\n");

		// Enter withdrawal amount
		double withdrawalAmount = InputHelper.readDouble("[ALABPoint] Enter withdrawal amount (Min. PHP 100.00): PHP ", 100.0);
		System.out.println();

		if (withdrawalAmount % 100 != 0) {
			System.out.println("[ALABPoint] The system currently dispenses paper bills only in multiples of PHP 100.\n");
			return;
		}

		if (withdrawalAmount > currentAccount.getBalance()) {
			System.out.printf("[ALABPoint] Insufficient balance. Current balance: PHP %.2f\n\n", currentAccount.getBalance());
			return;
		}

		if (InputHelper.choice(String.format("Confirm withdrawal of PHP %.2f?", withdrawalAmount))) {
			System.out.println();

			// Store the balance before the withdrawal
			double balanceBefore = currentAccount.getBalance();

			// Get the current time
			String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			// Set balance after withdrawal
			bank.withdraw(currentAccount, withdrawalAmount, currentTime);

			System.out.println("[ALABPoint] Withdrawal successful!\n");

			System.out.println("========================================");
			System.out.println("                BALANCE");
			System.out.println("========================================");
			System.out.printf("Withdrawn Amount : PHP %.2f\n", withdrawalAmount);
			System.out.printf("Previous Balance : PHP %.2f\n", balanceBefore);
			System.out.printf("Updated Balance  : PHP %.2f\n", currentAccount.getBalance());
			System.out.println("========================================\n");

			System.out.printf("[ALABPoint] Please take your cash: PHP %.2f\n", withdrawalAmount);
			System.out.println();

			if (InputHelper.choice("Would you like to print/view a transaction receipt?")) {
				System.out.println();
				printReceipt("Withdrawal", currentAccount.getAccountId(), currentAccount.getName(), withdrawalAmount, currentAccount.getBalance(), currentTime);
			}
		} else {
			System.out.println("[ALABPoint] Withdrawal cancelled.\n");
		}

	}
}


