package com.itemshop.factories;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.job.Job;
import com.itemshop.job.WorkQueueComponent;
import com.itemshop.money.WalletComponent;
import com.itemshop.money.WalletOwner;

/**
 * Factory for producing shop entities.
 */
public class ShopFactory {
	
	/**
     * Creates the entity.
     * @param engine The engine to add the entity to.
     */
    public static void create(Engine engine) {
        
    	// Create the shop entity.
        Entity entity = new Entity();

        // Add the entities components.
        entity.add(new WorkQueueComponent(Job.SHOPWORKER));
        entity.add(new WalletComponent(WalletOwner.SHOP));

        // Add the shop entity to the engine.
        engine.addEntity(entity);
    }
}
