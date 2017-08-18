package com.itemshop.shop;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.itemshop.job.Job;
import com.itemshop.job.WorkQueueComponent;

/**
 * Handles the inner workings of the town shop.
 */
public class ShopSystem extends EntitySystem {
	
	/** The worker and work queue entities. */
	private static ImmutableArray<Entity> workQueues;

	/** The component mappers. */
	private static final ComponentMapper<WorkQueueComponent> workQueueComponent = ComponentMapper.getFor(WorkQueueComponent.class);

	@Override
	public void addedToEngine(Engine engine) {
		workQueues = engine.getEntitiesFor(Family.all(WorkQueueComponent.class).get());
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Update the shop work queue when we need to.
	}
	
	/**
	 * Get the work queue for the shop.
	 * @return shop work queue.
	 */
	private Entity getShopWorkQueue() {
		for (Entity workQueue : workQueues) {
			// Is this work queue the one for the shop?
			if (workQueueComponent.get(workQueue).getJobRole() == Job.SHOPWORKER) {
				return workQueue;
			}
		}
		// There was no work queue for the shop.
		return null;
	}
}