package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.Entity;

/**
 * An activity which involves simply walking to a tile.
 */
public class PerformTileActionActivity extends TileInteractionActivity {
	
	/** The tile action to perform on reaching the specified tile. */
	private TileAction action;

    /**
     * Create a new instance of the PerformTileActionActivity class.
     * @param character
     * @param tile
     * @param action
     */
	public PerformTileActionActivity(Entity character, Entity tile, TileAction action) {
		super(character, tile);
		this.action = action;
	}
	
	/**
	 * Called when the tile to interact with has been reached.
	 */
	@Override
	public void interact() {
		// Perform the tile action.
		this.action.perform(this.getTile());
	}
}
