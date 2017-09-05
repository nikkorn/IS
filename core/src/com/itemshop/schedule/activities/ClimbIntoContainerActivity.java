package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.itemshop.container.ContainerComponent;

/**
 * An activity which involves placing the character into a container.
 */
public class ClimbIntoContainerActivity extends TileInteractionActivity {
	
	/** The required component mappers. */
    private static ComponentMapper<ContainerComponent> containerMapper = ComponentMapper.getFor(ContainerComponent.class);
    
    /** The target tile, could be a container. */
    private Entity tile;
    
    /** The character to place in the tile. */
    private Entity character;

    /**
     * Create a new instance of the ClimbIntoContainerActivity class.
     * @param character
     * @param tile
     */
	public ClimbIntoContainerActivity(Entity character, Entity tile) {
		super(character, tile);
		this.tile      = tile;
		this.character = character;
	}
	
	/**
	 * Called when the tile to interact with has been reached.
	 */
	@Override
	public void interact() {
		// Determine whether this tile is a container. If it is a container then we will be climbing inside. Otherwise, do nothing.
		if (containerMapper.has(tile)) {
			try {
				
				// The tile is a container, add the character to it.
				containerMapper.get(tile).add(character);
				
			} catch (Exception e) {
				// If an exception was thrown it was because there is no room for this item. Do nothing for now.
			}
		}
	}
}
