package com.itemshop.money;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Handles processing of each entity's wallet component.
 */
public class PaymentSystem extends IteratingSystem {
	
	/** Component mappers to get components from entities. */
    private static ComponentMapper<WalletComponent> walletMapper = ComponentMapper.getFor(WalletComponent.class);
    
	/**
	 * Constructs the payment system instance.
	 */
	public PaymentSystem() {
		super(Family.all(WalletComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// Get the wallet component of this entity.
		WalletComponent wallet = walletMapper.get(entity);
		// Process any pending payments for this wallet.
		for (Payment payment : wallet.payments) {
			// Try to get the wallet of the entity we are transferring money with.
			WalletComponent transfereeWallet = this.getWallet(payment.getTransferee());
			// How we handle this payment depends on the payment type.
			switch(payment.getType()) {
				case PAY:
					// We are removing funds from our wallet.
					wallet.money -= payment.getAmount();
					// And if there is a transferee ...
					if (transfereeWallet != null) {
						// ... Adding funds to theirs.
						transfereeWallet.money += payment.getAmount();
					}
					break;
				case RECEIVE:
					// We are adding funds to our wallet.
					wallet.money += payment.getAmount();
					// And if there is a transferee ...
					if (transfereeWallet != null) {
						// ... Removing funds from theirs.
						transfereeWallet.money -= payment.getAmount();
					}
					break;
			}
		}
		// We have processed the payments for this wallet so just clear them.
		wallet.payments.clear();
	}
	
	/**
	 * Gets the wallet component of the specified owner.
	 * Returns null if the owner has no wallet.
	 * @param owner
	 * @return wallet component
	 */
	public WalletComponent getWallet(WalletOwner owner) {
		// Iterate over all entities which have a wallet component.
		for (Entity entity : this.getEntities()) {
			// Is this the wallet we are looking for?
			if (walletMapper.get(entity).owner == owner) {
				return walletMapper.get(entity);
			}
		}
		// We did not find the wallet.
		return null;
	}
}
