package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.itemshop.container.ContainerComponent;
import com.itemshop.item.ClaimedComponent;

/**
 * An activity which involves placing an item on a tile, or in a tile if it is a container.
 */
public class PlaceItemActivity extends TileInteractionActivity {
	
	/** The required component mappers. */
    private static ComponentMapper<ContainerComponent> containerMapper = ComponentMapper.getFor(ContainerComponent.class);
    
    /** The target tile, could be a container. */
    private Entity tile;
    
    /** The item to place in/on the tile. */
    private Entity item;

    /**
     * Create a new instance of the PlaceItemActivity class.
     * @param character
     * @param tile
     * @param item
     */
	public PlaceItemActivity(Entity character, Entity tile, Entity item) {
		super(character, tile);
		this.tile = tile;
		this.item = item;
	}
	
	/**
	 * Called when the tile to interact with has been reached.
	 */
	@Override
	public void interact() {
		// Determine whether this tile is a container. If it is a container then
		// we will be placing the item inside it if there is space. Otherwise, we
		// will simple be dropping it on the floor on the tile.
		if (containerMapper.has(tile)) {
			try {
				
				// The tile is a container, add the item to it.
				containerMapper.get(tile).add(item);
				
				// Remove any claim on this item so that it can be targeted by other characters.
				item.remove(ClaimedComponent.class);
				
			} catch (Exception e) {
				// If an exception was thrown it was because there is no room for this item. Do nothing for now.
			}
		} else {
			// TODO We are just putting the item on the floor.
		}
	}
}
