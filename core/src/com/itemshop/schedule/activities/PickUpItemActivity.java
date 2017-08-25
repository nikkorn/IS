package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.itemshop.container.ContainerComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.RenderOffsetComponent;
import com.itemshop.render.RenderSizeComponent;

/**
 * An activity which involves picking up an item from a tile, or out of a tile if it is a container.
 */
public class PickUpItemActivity extends TileInteractionActivity {
	
	/** The required component mappers. */
    private static ComponentMapper<ContainerComponent> containerMapper = ComponentMapper.getFor(ContainerComponent.class);
    
    /** The character. */
    private Entity character;
    
    /** The target tile, could be a container. */
    private Entity tile;
    
    /** The item to pick up. */
    private Entity item;

    /**
     * Create a new instance of the PickUpItemActivity class.
     * @param character
     * @param tile
     * @param item
     */
	public PickUpItemActivity(Entity character, Entity tile, Entity item) {
		super(character, tile);
		this.character = character;
		this.tile      = tile;
		this.item      = item;
	}
	
	/**
	 * Called when the tile to interact with has been reached.
	 */
	@Override
	public void interact() {
		// We cannot do anything if the character does not have the inventory space to hold another item.
		if (containerMapper.get(character).hasSpace()) {
			// Determine whether this tile is a container. If it is a container then
			// we will taking the item out of it and adding it to the container component
			// of the character entity if there is space. If it is not a container then
			// we are just picking it up off of the floor.
			if (containerMapper.has(tile)) {
				// The tile is a container, remove the item from it.
				containerMapper.get(tile).remove(item);
			} else {
				// We are picking the item up from the floor.
				// It no longer has a position in the world.
				// It now also has no render offset and size.
				tile.remove(PositionComponent.class);
				tile.remove(RenderOffsetComponent.class);
				tile.remove(RenderSizeComponent.class);
			}
			try {
				// Add the item to the characters inventory.
				containerMapper.get(character).add(item);
			} catch (Exception e) {}
		} else {
			// We have no space in our inventory for this item!
		}
	}
}
