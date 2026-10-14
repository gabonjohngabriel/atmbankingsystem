package bsit2e_g1.ui;

import bsit2e_g1.datastructures.AccountList;
import bsit2e_g1.nodes.AccountData;
import bsit2e_g1.utils.InputHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deposit extends BankingOperation {
	private final AccountList bank;
	private AccountData currentAccount;

	public Deposit(AccountData currentAccount, AccountList bank) {
		this.bank = bank;
		this.currentAccount = currentAccount;
	}

	@Override
	public void execute() {

		System.out.println("========================================");
		System.out.println("                DEPOSIT                 ");
		System.out.println("========================================\n");
		System.out.println("Account Number  : " + currentAccount.getAccountId());
		System.out.println("Account Name    : " + currentAccount.getName());
		System.out.printf("Account Balance : PHP %.2f\n", currentAccount.getBalance());
		System.out.println("========================================\n");

		// Enter deposit amount
		double depositedAmount = InputHelper.readDouble("[ALABPoint] Enter deposit amount (Min PHP 1.00): PHP ", 1.0);
		System.out.println();

		if (InputHelper.choice("Do you want to deposit this amount?")) {
			System.out.println();

			// Store the balance before the deposit
			double balanceBefore = currentAccount.getBalance();

			// Get the current time
			String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			// Set balance after deposit
			bank.deposit(currentAccount, depositedAmount, currentTime);

			System.out.println("[ALABPoint] Deposit transaction successful!\n");

			System.out.println("========================================");
			System.out.println("                BALANCE");
			System.out.println("========================================");
			System.out.printf("Deposited Amount : PHP %.2f\n", depositedAmount);
			System.out.printf("Previous Balance : PHP %.2f\n", balanceBefore);
			System.out.printf("Updated Balance  : PHP %.2f\n", currentAccount.getBalance());
			System.out.println("========================================\n");


			if (InputHelper.choice("Would you like to print/view a transaction receipt?")) {
				System.out.println();
				printReceipt("Deposit", currentAccount.getAccountId(), currentAccount.getName(), depositedAmount, currentAccount.getBalance(), currentTime);
			}
		} else {
			System.out.println("[ALABPoint] Deposit transaction cancelled.\n");
		}

	}
}


