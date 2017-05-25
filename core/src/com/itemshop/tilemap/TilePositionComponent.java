package com.itemshop.tilemap;

import com.badlogic.ashley.core.Component;

/**
 * A component describing the tile it is positioned at.
 */
public class TilePositionComponent implements Component {
	
	/** The x position. */
	public int x;
	
	/** The y position. */
	public int y;
	
	/**
	 * Create a new instance of TilePositionComponent.
	 * @param x
	 * @param y
	 */
	public TilePositionComponent(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
