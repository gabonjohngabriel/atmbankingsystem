package bsit2e_g1.ui;

import bsit2e_g1.nodes.AccountData;
import bsit2e_g1.datastructures.AccountList;
import bsit2e_g1.utils.InputHelper;

public class AccountCreation extends BankingOperation {
	private final AccountList bank;

	public AccountCreation(AccountList bank) {
		this.bank = bank;
	}

	@Override
	public void execute() {
		String name, firstName, lastName, address, email, contactNo, pin;
		double balance;
		String accountType;

		System.out.println("========================================");
		System.out.println("            ACCOUNT CREATION");
		System.out.println("========================================\n");

		// First name validation
		while (true) {
			firstName = InputHelper.readString("[ALABPoint] Enter your first name: ");

			System.out.println();

			if (firstName.isBlank()) {
				System.out.println("[ALABPoint] First name is required. Please try again.\n");
			} else if (!firstName.matches("^[a-zA-Z\\s'-]+$")) { // Contain only letters, spaces, apostrophes, and hyphens
				System.out.println("[ALABPoint] First name must only contain letters. Please try again.\n");
			} else {
				break;
			}
		}

		// Second name validation
		while (true) {
			lastName = InputHelper.readString("[ALABPoint] Enter your last name: ");

			System.out.println();

			if (lastName.isBlank()) {
				System.out.println("[ALABPoint] Last name is required. Please try again.\n");
			} else if (!lastName.matches("^[a-zA-Z\\s'-]+$")) {
				System.out.println("[ALABPoint] Last name must only contain letters. Please try again.\n");
			} else {
				break;
			}
		}

		name = firstName + " " + lastName;

		// Home address validation
		while (true) {
			address = InputHelper.readString("[ALABPoint] Enter your home address: ");

			System.out.println();

			if (address.isBlank()) {
				System.out.println("[ALABPoint] Home address is required. Please try again.\n");
			} else {
				break;
			}
		}

		// Email validation (Optional)
		while (true) {
			email = InputHelper.readString("[ALABPoint] Enter your email address (Optional): ");
			System.out.println();

			if (email.isBlank() || email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
				break;
			}

			System.out.println("[ALABPoint] Invalid email address format. Please try again.\n");
		}

		// Contact number validation
		while (true) {
			contactNo = InputHelper.readString("[ALABPoint] Enter your contact number (+63 / 09): ");
			System.out.println();

			if (contactNo.isBlank()) {
				System.out.println("[ALABPoint] Contact number is required. Please try again.\n");
				continue;
			}

			if (contactNo.matches("^(09\\d{9}|\\+639\\d{9})$")) { // 09 9-digits OR +639 9-digits
				break;
			}

			System.out.println("[ALABPoint] Invalid contact number! Must be 11 digits (09XXXXXXXXX) or 13 chars (+639XXXXXXXXX).\n");
		}

		// Account type validation
		accountType = InputHelper.choice("Enter account type (1. Savings / 2. Current): ", "Savings", "Current");
		System.out.println();

		// Initial deposit validation
		balance = InputHelper.readDouble("[ALABPoint] Enter initial deposit (Min. PHP 100): PHP ", 100.0);
		System.out.println();

		// PIN validation
		while (true) {
			pin = InputHelper.readString("[ALABPoint] Enter your PIN (4 digits): ");
			System.out.println();

			// \\d = int, similar to %d
			// 4 digits

			if (!pin.matches("^\\d{4}$")) {
				System.out.println("[ALABPoint] PIN must be exactly 4 digits (0-9). Please try again.\n");
				continue;
			}

			String pinCheck = InputHelper.readString("[ALABPoint] Please enter your PIN again (4 digits): ");
			System.out.println();

			if (pin.equals(pinCheck)) {
				break;
			}

			System.out.println("[ALABPoint] PINs do not match. Please try again.\n");
		}

		// Create new account store it to the bank/linked list
		AccountData newAccount = bank.createAccount(name, address, email, contactNo, accountType, pin, balance);

		System.out.printf("[ALABPoint] Account successfully created! ID: %s\n", newAccount.getAccountId());
	}
}


