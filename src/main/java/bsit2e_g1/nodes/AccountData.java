package bsit2e_g1.nodes;

import bsit2e_g1.datastructures.TransactionList;

public class AccountData {
	// Restrict access to sensitive information
	private final String accountId;
	private String name;
	private String address;
	private String email;
	private String contactNo;
	private String accountType;
	private String pin;
	private double balance;

	// Counts initial account created.
	public static int accountNo = 0;

	// Points to the next node.
	public AccountData next;

	// Stores users' transactions
	private TransactionList transactions;

	public AccountData(String name, String address, String email, String contactNo, String accountType, String pin, double balance) {
		accountNo++;

		this.accountId = String.format("ALAB-%03d", accountNo);
		this.name = name;
		this.address = address;
		this.email = email;
		this.contactNo = contactNo;
		this.accountType = accountType;
		this.pin = pin;
		this.balance = balance;
		this.transactions = new TransactionList();
		this.next = null;
	}

	// 3. Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getAccountId() {
		return accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public TransactionList getTransactions() {
		return transactions;
	}

}
