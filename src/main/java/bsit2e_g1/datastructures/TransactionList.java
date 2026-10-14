package bsit2e_g1.datastructures;

import bsit2e_g1.nodes.AccountData;
import bsit2e_g1.nodes.TransactionData;

public class TransactionList {
	TransactionData firstTransaction = null;

	// 	ADD NODE: Add latest transaction
	public void addTransaction(String transactionType, double amount, double balanceAfter, String currentTime) {
		TransactionData newTransaction = new TransactionData(transactionType, amount, balanceAfter, currentTime);

		if (firstTransaction == null) {
			firstTransaction = newTransaction;
		} else {
			// TRAVERSAL
			TransactionData current = firstTransaction;

			while (current.next != null) {
				current = current.next;
			}

			current.next = newTransaction;
		}
	}

	// 	VALIDATION: Check if transaction history is empty.
	public boolean isEmpty() {
		return firstTransaction == null;
	}

	// Get the data of the user's latest transaction.
	public TransactionData getLastTransaction() {
		if (firstTransaction == null) {
			return null;
		}

		// TRAVERSAL
		TransactionData current = firstTransaction;

		while (current.next != null) {
			current = current.next;
		}

		return current;
	}

	// Display user's transaction history
	public void displayTransactions() {
		// If head is null
		if (firstTransaction == null) {
			System.out.println("[SYSTEM] No transaction history found.");
			System.out.println("================================================================================\n");
			return;
		}

		// If head is not null, display transaction history
		System.out.printf("%-20s | %-16s | %-18s | %-20s\n", "Type", "Amount", "Balance After", "Time");
		System.out.println("--------------------------------------------------------------------------------------");

		TransactionData current = firstTransaction;

		while (current != null) {
			System.out.printf("%-20s | PHP %-12.2f | PHP %-14.2f | %s\n", current.getTransactionType(), current.getAmount(), current.getBalanceAfter(), current.getTimeStamp());
			current = current.next;
		}

		System.out.println("================================================================================\n");
	}
}

