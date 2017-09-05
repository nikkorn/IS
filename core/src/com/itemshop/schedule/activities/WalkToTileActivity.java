package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.Entity;

/**
 * An activity which involves simply walking to a tile.
 */
public class WalkToTileActivity extends TileInteractionActivity {

    /**
     * Create a new instance of the WalkToTileActivity class.
     * @param character
     * @param tile
     */
	public WalkToTileActivity(Entity character, Entity tile) {
		super(character, tile);
	}
	
	/**
	 * Called when the tile to interact with has been reached.
	 */
	@Override
	public void interact() {}
}
