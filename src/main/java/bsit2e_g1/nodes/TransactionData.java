package bsit2e_g1.nodes;

public class TransactionData {
	// Encapsulation
	// Hiding sensitive information
	private String transactionType;
	private double amount;
	private double balanceAfter;
	private String timeStamp;

	public TransactionData next;

	public TransactionData(String transactionType, double amount, double balanceAfter, String timeStamp) {
		this.transactionType = transactionType;
		this.amount = amount;
		this.balanceAfter = balanceAfter;
		this.timeStamp = timeStamp;
		this.next = null;
	}

	// Getters
	public String getTransactionType() {
		return transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public double getBalanceAfter() {
		return balanceAfter;
	}

	public double setBalanceAfter(double balanceAfter) {
		return this.balanceAfter = balanceAfter;
	}

	public String getTimeStamp() {
		return timeStamp;
	}
}
