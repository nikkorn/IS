package com.itemshop.area;

import com.badlogic.ashley.core.Component;

/**
 * A component which defines a tile type.
 */
public class TileTypeComponent implements Component {
	
	/** The tile type. */
	public TileType type;
	
	/**
	 * Create a new instance of TileTypeComponent.
	 * @param type
	 */
	public TileTypeComponent(TileType type) {
		this.type = type;
	}
}
