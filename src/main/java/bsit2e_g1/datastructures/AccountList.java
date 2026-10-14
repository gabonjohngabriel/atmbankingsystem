package bsit2e_g1.datastructures;

import bsit2e_g1.nodes.AccountData;
import bsit2e_g1.utils.InputHelper;

public class AccountList {
	AccountData firstAccount = null;

	// Create new account
	public AccountData createAccount(String name, String address, String email, String contactNo, String accountType, String pin, double balance) {
		// Store the data
		AccountData newAccount = new AccountData(name, address, email, contactNo, accountType, pin, balance);

		// If the first account is null, then it must be the first account
		if (isEmpty()) {
			firstAccount = newAccount;
		} else {
			// Initialize the current account
			AccountData current = firstAccount;

			// Traverse the list until the end
			while (current.next != null) {
				current = current.next;
			}

			// If the next account is null, then it must be the last account
			current.next = newAccount;
		}
		return newAccount;
	}

	// Check if the account exists.
	public AccountData findAccount(String accountId) {
		AccountData currentAccount = firstAccount;

		while (currentAccount != null) {
			if (currentAccount.getAccountId().equalsIgnoreCase(accountId)) {
				// If it exists, it must return the current node.
				return currentAccount;
			}
			currentAccount = currentAccount.next;
		}

		// If it does not exist, it must return null.
		return null;
	}

	public boolean checkPin(AccountData currentAccount) {
		String pin;
		int tryCtr = 3;

		do {
			// Enter users' current PIN
			pin = InputHelper.readString("[ALABPoint] Enter your PIN (4 digits): ");

			System.out.println();

			// PIN is correct
			if (pin.equals(currentAccount.getPin())) {
				return true;
			}

			tryCtr--;

			// If the user has more tries left, prompt them to try again
			if (tryCtr > 0) {
				System.out.println("[ALABPoint] Wrong PIN! Please try again. [" + tryCtr + " tries left.]\n");
			} else {
				System.out.println("[ALABPoint] Wrong PIN! You have run out of attempts.");
			}
		} while (tryCtr > 0);

		// If the PIN is incorrect, return false
		return false;
	}

	// Transfer balance from sender account to recipient account ID
	public void transferFunds(AccountData sender, AccountData recipient, double amount, String currentTime) {
		if (amount <= 0) {
			System.out.println("[ALABPoint] Invalid transfer amount.\n");
			return;
		}

		if (sender.getBalance() < amount) {
			System.out.println("[ALABPoint] Insufficient balance for this transfer.\n");
			return;
		}

		sender.setBalance(sender.getBalance() - amount);
		recipient.setBalance(recipient.getBalance() + amount);

		// Add into both accounts' transaction list
		sender.getTransactions().addTransaction("Fund Transfer to (" + recipient.getAccountId() + ")", amount, sender.getBalance(), currentTime);
		recipient.getTransactions().addTransaction("Fund Received (" + sender.getAccountId() + ")", amount, recipient.getBalance(), currentTime);

		System.out.println("[ALABPoint] Fund transfer successful.\n");
	}

	// Deposit into current account
	public void deposit(AccountData currentAccount, double amount, String currentTime) {
		if (currentAccount == null) {
			System.out.println("[ALABPoint] Account not found.");
			return;
		}
		if (amount <= 0) {
			System.out.println("[ALABPoint] Invalid deposit amount.");
			return;
		}

		currentAccount.setBalance(currentAccount.getBalance() + amount);

		currentAccount.getTransactions().addTransaction("Deposit", amount, currentAccount.getBalance(), currentTime);
	}

	// Withdraw from current account
	public void withdraw(AccountData currentAccount, double amount, String currentTime) {
		if (currentAccount == null) {
			System.out.println("[ALABPoint] Account not found.");
			return;
		}
		if (amount <= 0) {
			System.out.println("[ALABPoint] Invalid withdrawal amount.");
			return;
		}

		currentAccount.setBalance(currentAccount.getBalance() - amount);
		currentAccount.getTransactions().addTransaction("Withdrawal", amount, currentAccount.getBalance(), currentTime);
	}


	// Remove current account
	public AccountData deleteAccount(String accountId) {
		// If the account list is empty or the account ID is null, return null.
		if (isEmpty() || accountId == null) {
			return null;
		}

		// It checks if the first account has the same account ID as the one to be deleted.
		if (firstAccount.getAccountId().equalsIgnoreCase(accountId)) {
			AccountData deletedAccount = firstAccount;
			firstAccount = firstAccount.next;
			return deletedAccount;
		}

		// Instantiate the current account.
		AccountData currentAccount = firstAccount;

		// Traverse the list to find the account to be deleted.
		while (currentAccount.next != null) {
			// Checks if the next account has the same account ID as the one to be deleted.
			if (currentAccount.next.getAccountId().equalsIgnoreCase(accountId)) {
				AccountData deletedAccount = currentAccount.next;
				currentAccount.next = deletedAccount.next;
				deletedAccount.next = null; // Remove the reference to the next node to avoid memory leaks.
				return deletedAccount;
			}
			currentAccount = currentAccount.next;
		}

		return null;
	}

	// 	Update current account
	public void updateAccount(AccountData currentAccount, AccountList bank) {
		if (!InputHelper.choice("Would you like to edit your account details?")) {
			return;
		}

		System.out.println();

		while (true) {
			System.out.println("Select field to update:");
			System.out.println("[1] Contact Number");
			System.out.println("[2] Home Address");
			System.out.println("[3] Email Address");
			System.out.println("[4] PIN");
			System.out.println("[0] Cancel\n");

			int choice = InputHelper.readInt("Choice: ", 0, 4);
			System.out.println();

			if (choice == 0) {
				return;
			} else if (choice == 1) {
				String newContact = InputHelper.readString("[ALABPoint] Enter new contact number (+63 / 09): ");
				System.out.println();

				if (newContact.startsWith("+63") || newContact.startsWith("09")) {
					currentAccount.setContactNo(newContact);
					System.out.println("[ALABPoint] Contact number updated successfully!\n");
				} else {
					System.out.println("[ALABPoint] Invalid contact number format. Please try again.\n");
				}
			} else if (choice == 2) {
				String newAddress = InputHelper.readString("[ALABPoint] Enter new home address: ");
				System.out.println();

				if (!newAddress.isBlank()) {
					currentAccount.setAddress(newAddress);
					System.out.println("[ALABPoint] Address updated successfully!\n");
				}
			} else if (choice == 3) {
				String newEmail = InputHelper.readString("[ALABPoint] Enter new email address: ");
				System.out.println();

				if (!newEmail.isBlank()) {
					currentAccount.setEmail(newEmail);
					System.out.println("[ALABPoint] Email updated successfully!\n");
				}
			} else if (choice == 4) {
				System.out.println("[ALABPoint] Verify current PIN first.");
				System.out.println();

				if (bank.checkPin(currentAccount)) {
					String newPin = InputHelper.readString("[ALABPoint] Enter new 4-digit PIN: ");
					System.out.println();

					String newPinConfirmation = InputHelper.readString("[ALABPoint] Confirm new 4-digit PIN: ");
					System.out.println();

					if (newPin.matches("^\\d{4}$") && newPin.equals(newPinConfirmation)) {
						currentAccount.setPin(newPin);

						System.out.println("[ALABPoint] PIN changed successfully!\n");
					} else {
						System.out.println("[ALABPoint] PIN must be exactly 4 digits (0-9).\n");
					}
				}
			}
		}
	}

	public boolean isEmpty() {
		return firstAccount == null;
	}


	// Display methods
	public void displayBankSummary() {
		if (isEmpty()) {
			System.out.println("[ALABPoint] No accounts currently registered in the system.\n");
			return;
		}

		int totalAccounts = 0;
		int savingsCount = 0;
		int currentCount = 0;
		double totalBankFunds = 0.0;

		AccountData currentAccount = firstAccount;

		while (currentAccount != null) {
			totalAccounts++;
			totalBankFunds += currentAccount.getBalance();

			if ("Savings".equalsIgnoreCase(currentAccount.getAccountType())) {
				savingsCount++;
			} else {
				currentCount++;
			}

			currentAccount = currentAccount.next;
		}
		System.out.println("========================================");
		System.out.println("      ALABPOINT | FINANCIAL SUMMARY     ");
		System.out.println("========================================");
		System.out.printf("Total Active Accounts : %d\n", totalAccounts);
		System.out.printf("Savings Accounts      : %d\n", savingsCount);
		System.out.printf("Current Accounts      : %d\n", currentCount);
		System.out.println("----------------------------------------");
		System.out.printf("Total Bank Reserve    : PHP %.2f\n", totalBankFunds);
		System.out.printf("Average Account Funds : PHP %.2f\n", (totalBankFunds / totalAccounts));
		System.out.println("========================================\n");
	}

	public void displayAllAccounts() {
		if (isEmpty()) {
			System.out.println("[ALABPoint] No accounts found in the system.\n");
			return;
		}

		System.out.println("=========================================================================================");
		System.out.println("                           ALABPOINT | ACTIVE BANK ACCOUNTS");
		System.out.println("=========================================================================================");
		System.out.printf("%-10s | %-20s | %-10s | %-15s | %-16s\n", "Account ID", "Name", "Type", "Contact", "Balance");
		System.out.println("-----------------------------------------------------------------------------------------");

		AccountData currentAccount = firstAccount;

		while (currentAccount != null) {
			System.out.printf("%-10s | %-20s | %-10s | %-15s | PHP %-12.2f\n", currentAccount.getAccountId(), currentAccount.getName(), currentAccount.getAccountType(), currentAccount.getContactNo(), currentAccount.getBalance());
			currentAccount = currentAccount.next;
		}

		System.out.println("=========================================================================================\n");
	}


	public void displayAccountDetails(AccountData currentAccount) {
		System.out.println("========================================");
		System.out.println("           ACCOUNT INFORMATION");
		System.out.println("========================================");
		System.out.println("Account ID     : " + currentAccount.getAccountId());
		System.out.println("Account Type   : " + currentAccount.getAccountType());
		System.out.println("----------------------------------------");
		System.out.println("Name           : " + currentAccount.getName());
		System.out.println("Address        : " + currentAccount.getAddress());
		System.out.println("Email          : " + currentAccount.getEmail());
		System.out.println("Contact Number : " + currentAccount.getContactNo());
		System.out.println("========================================\n");
	}
}
