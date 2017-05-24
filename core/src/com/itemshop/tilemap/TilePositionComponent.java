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
}
