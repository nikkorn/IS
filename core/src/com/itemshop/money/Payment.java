package com.itemshop.money;

/**
 * An object representing a payment to be made.
 */
public class Payment {
	
	/** The wallet owner to be transfer money with. */
	private WalletOwner transferee;
	
	/** The payment type. */
	private PaymentType type;
	
	/** The amount to transfer. */
	private int amount;
	
	/**
	 * Create a new instance of the Payment class.
	 * @param transferee
	 * @param type
	 * @param amount
	 */
	public Payment(WalletOwner transferee, PaymentType type, int amount) {
		this.transferee = transferee;
		this.type       = type;
		this.amount     = amount;
	}

	/**
	 * Get the transferee with whom we are moving money.
	 * @return transferee
	 */
	public WalletOwner getTransferee() {
		return transferee;
	}

	/**
	 * Get the type of the payment.
	 * @return type
	 */
	public PaymentType getType() {
		return type;
	}

	/**
	 * Get the amount to transfer.
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}
}
