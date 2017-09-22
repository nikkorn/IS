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
import com.itemshop.item.ItemCategory;
import com.itemshop.item.ItemTypeComponent;
import com.itemshop.job.Job;
import com.itemshop.job.WorkQueueComponent;
import com.itemshop.schedule.ActivityPlanner;
import com.itemshop.schedule.activities.PickUpItemActivity;
import com.itemshop.schedule.activities.PlaceItemActivity;
import com.itemshop.schedule.activities.TalkActivity;
import com.itemshop.schedule.activities.WaitActivity;
import com.itemshop.utilities.lotto.Lotto;

/**
 * Handles the inner workings of the town shop.
 */
public class ShopSystem extends EntitySystem {
	
	/** The worker and work queue entities. */
	private static ImmutableArray<Entity> workQueues;
	
	/** The buyer entities. */
	private static ImmutableArray<Entity> buyers;

	/** The component mappers. */
	private static final ComponentMapper<WorkQueueComponent> workQueueMapper     = ComponentMapper.getFor(WorkQueueComponent.class);
	private static final ComponentMapper<ContainerComponent> containerMapper     = ComponentMapper.getFor(ContainerComponent.class);
	private static final ComponentMapper<ClaimedComponent> claimedMapper         = ComponentMapper.getFor(ClaimedComponent.class);
	private static final ComponentMapper<BuyIntentComponent> buyIntentMapper     = ComponentMapper.getFor(BuyIntentComponent.class);
	private static final ComponentMapper<ShopDisplayComponent> shopDisplayMapper = ComponentMapper.getFor(ShopDisplayComponent.class);
	private static final ComponentMapper<ItemTypeComponent> itemTypeMapper       = ComponentMapper.getFor(ItemTypeComponent.class);
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		workQueues = engine.getEntitiesFor(Family.all(WorkQueueComponent.class).get());
		buyers     = engine.getEntitiesFor(Family.all(BuyIntentComponent.class).get());
	}
	
	/**
	 * Update the shop work queue with any work that needs doing.
	 */
	@Override
	public void update(float deltaTime) {
		
		// Stock the shelves with any stock in the chests in the store room.
		// TODO This will eventually be taken care of by the player.
		stockShelves();
		
		// Handle any entities in the shop that have an intention to buy an item. 
		handleBuyers();
		
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
						// Make a comment.
						current.add(new TalkActivity(doer, new Lotto<String>()
								.add("That looks nice.")
								.add("Perfect.")
								.add("Great.")
								.add("Top notch.")
								.add("I'm happy with that.")
								.add("I can smell the money.")
								.draw()
						));
					};
					
					// ... And add it the the shop work queue.
					addWorkToQueue(itemRestockPlanner);
				}
			}
		}
	}
	
	/**
	 * Handle any entities in the shop that have an intention to buy an item. 
	 */
	private void handleBuyers() {
		
		for (Entity buyer : buyers) {
			
			// Get the items that the buyer is interested in.
			ArrayList<Entity> items = this.browseShop(buyIntentMapper.get(buyer));
			
			// Are there any items that interest the buyer?
			if (items.size() > 0) {
				
				// Pick a random item the buyer is interested in.
				Entity item = items.get(new Random().nextInt(items.size() - 1));
				
				// The buyer is going to buy this item so claim it for them.
				item.add(new ClaimedComponent());
				
				// TODO Go get the item and buy it!
				
			} else {
				// The shop is not selling anything the buyer wants.
				buyer.remove(BuyIntentComponent.class);
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
	 * Check the shop for any items on display and for sale that have not been claimed.
	 * @return unclaimed items
	 */
	private ArrayList<Entity> getUnclaimedItemsOnDisplay() {
		
		ArrayList<Entity> displays = new ArrayList<Entity>();
		
		// Get all the displays in the shop.
		for (Entity tile : this.getEngine().getSystem(AreaSystem.class).getTilesInArea(Area.SHOP)) {
			// Check to make sure this tile is a shop display.
			if (shopDisplayMapper.has(tile)) {
				displays.add(tile);
			}
		}
		
		ArrayList<Entity> items = new ArrayList<Entity>();
		
		// Get all the unclaimed items in the shop displays.
		for (Entity display : displays) {
			
			// Every display is a container for items.
			for (Entity item : containerMapper.get(display).getContents()) {
				
				// Do NOT include any items that have been claimed by other characters/processes.
				if (!claimedMapper.has(item)) {
					items.add(item);
				}
			}
		}
		
		return items;
	}
	
	/**
	 * Check the shop for any items for sale which are appropriate for a buy intent.
	 * @return appropriate items
	 */
	private ArrayList<Entity> browseShop(BuyIntentComponent buyIntent) {
		
		ArrayList<Entity> items = new ArrayList<Entity>();
		
		// Iterate over all available shops for sale.
		for (Entity item : this.getUnclaimedItemsOnDisplay()) {
			
			// Get the type of this item.
			ItemTypeComponent itemTypeComponent = itemTypeMapper.get(item);
			
			// TODO Check for whether the item is too expensive for the buyer.
			
			// Is this item suitable for the buyer?
			if (buyIntent.itemType != null) {
				// The buyer is looking for a particular item type.
				if (buyIntent.itemType == itemTypeComponent.type) {
					items.add(item);
				}
			} else if (buyIntent.itemCategory != null && buyIntent.itemCategory != ItemCategory.NONE) {
				// The buyer is after any old item with a particular category in their price range.
				if (buyIntent.itemCategory == itemTypeComponent.category) {
					items.add(item);
				}
			} else {
				 // The buyer is after any old item in their price range.
				items.add(item);
			}	
		}
		
		return items;
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