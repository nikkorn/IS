package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.itemshop.money.Payment;
import com.itemshop.money.WalletComponent;
import com.itemshop.schedule.Activity;

/**
 * An activity which involves maying a pending payment.
 */
public class PaymentActivity extends Activity {
	
	/** The required component mappers. */
    private static ComponentMapper<WalletComponent> walletMapper = ComponentMapper.getFor(WalletComponent.class);
   
	/** The payment. */
	private Payment payment;
	
	/** The doer. */
	private Entity doer;
	
	/**
	 * Create a new instance of the PaymentActivity class.
	 * @param doer
	 * @param payment
	 */
	public PaymentActivity(Entity doer, Payment payment) {
		this.doer    = doer;
		this.payment = payment;
	}
	
	@Override
	public void onBegin() {
		// Attempt to get the wallet component of the entity doing the payment.
		WalletComponent walletComponent = walletMapper.get(doer);
		// If we have a wallet component ... 
		if (walletComponent != null) {
			// ... Add the pending payment to be processed by the payment system.
			walletComponent.addPayment(payment);
		}
	}
	
	@Override
	public void perform() {
		// We are done!
		this.finish();
	}
	
	@Override
	public void onEnd() {} 
	
	@Override
	public void onInterrupt() {}
}
