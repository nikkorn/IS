package com.itemshop.shop;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.itemshop.area.Area;
import com.itemshop.area.AreaSystem;
import com.itemshop.area.TileType;
import com.itemshop.container.ContainerComponent;
import com.itemshop.item.ClaimedComponent;
import com.itemshop.job.Job;
import com.itemshop.job.WorkQueueComponent;
import com.itemshop.schedule.ActivityPlanner;
import com.itemshop.schedule.activities.PickUpItemActivity;
import com.itemshop.schedule.activities.PlaceItemActivity;
import com.itemshop.schedule.activities.WaitActivity;

/**
 * Handles the inner workings of the town shop.
 */
public class ShopSystem extends EntitySystem {
	
	/** The worker and work queue entities. */
	private static ImmutableArray<Entity> workQueues;

	/** The component mappers. */
	private static final ComponentMapper<WorkQueueComponent> workQueueMapper = ComponentMapper.getFor(WorkQueueComponent.class);
	private static final ComponentMapper<ContainerComponent> containerMapper = ComponentMapper.getFor(ContainerComponent.class);
	private static final ComponentMapper<ClaimedComponent> claimedMapper     = ComponentMapper.getFor(ClaimedComponent.class);
	
	@Override
	public void addedToEngine(Engine engine) {
		workQueues = engine.getEntitiesFor(Family.all(WorkQueueComponent.class).get());
	}
	
	/**
	 * Update the shop work queue with any work that needs doing.
	 */
	@Override
	public void update(float deltaTime) {
		
		// Stock the shelves with any stock in the chests in the store room.
		// TODO This will eventually be taken care of by the player.
		stockShelves();
		
		// Handle sale by interacting with a till that a customer is at.
		handleSales();
	}
	
	/**
	 * Stock the shelves with any stock in the chests in the store room.
	 */
	private void stockShelves() {
		
		// Get the chests in the shop store room.
		ArrayList<Entity> chests = this.getEngine().getSystem(AreaSystem.class).getTilesOfType(TileType.CHEST, Area.STOREROOM);
		
		// Iterate over each chest.
		for (Entity chest : chests) {
			
			// Get the container component for the chest.
			ContainerComponent container = containerMapper.get(chest);
			
			// For every item in the container ...
			for (Entity item : container.getContents()) {
				
				// ... That is not claimed.
				if (!claimedMapper.has(item)) {
					
					// Claim the item.
					item.add(new ClaimedComponent());
					
					// ... Create a work item to move it to a table in the shop ...
					ActivityPlanner itemRestockPlanner = (doer, current) -> {
						// All other activities should be disposed of.
						current.clear();
						// Find a nice table to put it on.
						ArrayList<Entity> tables = this.getEngine().getSystem(AreaSystem.class).getTilesOfType(TileType.TABLE, Area.SHOP);
						Entity targetTable       = tables.get(new Random().nextInt(tables.size() - 1));
						// Grab the item from the chest.
						current.add(new PickUpItemActivity(doer, chest, item));
						// Wait for a second.
						current.add(new WaitActivity(1000));
						// Place the item on a table in the shop.
						current.add(new PlaceItemActivity(doer, targetTable, item));
						// Remove the claim on this item.
						item.remove(ClaimedComponent.class);
					};
					
					// ... And add it the the shop work queue.
					addWorkToQueue(itemRestockPlanner);
				}
			}
		}
	}
	
	/**
	 * Handle sale by interacting with a till that a customer is at.
	 */
	private void handleSales() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Add a work item to the work queue for the shop, if it exists.
	 */
	private void addWorkToQueue(ActivityPlanner planner) {
		
		// Go over all entities with work queue components.
		for (Entity workQueue : workQueues) {
			
			// Get the work queue component.
			WorkQueueComponent workQueueComponent = workQueueMapper.get(workQueue);
			
			// Is this work queue the one for the shop?
			if (workQueueComponent.getJobRole() == Job.SHOPWORKER) {
				
				// Then add the work.
				workQueueComponent.addWorkItem(planner);
			}
		}
	}
}