package com.itemshop.money;

import java.util.ArrayList;
import com.badlogic.ashley.core.Component;

/**
 * A component representing an entities funds.
 */
public class WalletComponent implements Component {
	
	/** The pending payments. */
	public ArrayList<Payment> payments = new ArrayList<Payment>();
	
	/** The wallet owner. */
	public WalletOwner owner;
	
	/** The amount of money in the wallet. */
	public int money = 0;
	
	/**
	 * Create a new instance of the WalletComponent class.
	 * @param owner
	 */
	public WalletComponent(WalletOwner owner) {
		this.owner = owner;
	}
	
	/**
	 * Add a pending payment to this wallet.
	 * @param payment
	 */
	public void addPayment(Payment payment) {
		this.payments.add(payment);
	}
}
