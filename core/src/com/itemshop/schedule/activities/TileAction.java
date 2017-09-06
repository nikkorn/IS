package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.Entity;

/**
 * An action that can be performed on a tile.
 */
public interface TileAction {

	/**
	 * Performs the action.
	 * @param tile
	 */
    void perform(Entity tile);
}
