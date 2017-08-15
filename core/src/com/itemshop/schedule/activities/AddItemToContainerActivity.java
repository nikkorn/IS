package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.itemshop.container.ContainerComponent;

/**
 * An activity which involves walking to a container an placing the specified item into it.
 */
public class AddItemToContainerActivity extends TileInteractionActivity {
	
	/** The required component mappers. */
    private static ComponentMapper<ContainerComponent> containerMapper = ComponentMapper.getFor(ContainerComponent.class);
    
    /** The target container. */
    private Entity container;
    
    /** The item to transfer to the container. */
    private Entity item;

    /**
     * Create a new instance of the AddItemToContainerActivity class.
     * @param character
     * @param container
     * @param item
     */
	public AddItemToContainerActivity(Entity character, Entity container, Entity item) {
		super(character, container);
		this.container = container;
		this.item      = item;
	}
	
	/**
	 * Called when the tile to interact with has been reached.
	 */
	@Override
	public void interact() {
		// Add the item to the container.
		try {
			containerMapper.get(container).add(item);
		} catch (Exception e) {
			// If an exception was thrown it was because there is no room for this item. Do nothing for now.
		}
	}
}
