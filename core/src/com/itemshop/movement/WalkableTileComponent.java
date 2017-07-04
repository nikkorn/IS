package com.itemshop.movement;

import com.badlogic.ashley.core.Component;

/**
 * A component that defines a walkable tile.
 */
public class WalkableTileComponent implements Component {
	
	/** The movement cost to walk on this tile. */
	public int movementCost;
	
	/**
	 * Create a new instance of MovementTileTransition.
	 */
	public WalkableTileComponent() { 
		this(1);
	}
	
	/**
	 * Create a new instance of MovementTileTransition.
	 * @param movementCost The movement cost to walk on this tile.
	 */
	public WalkableTileComponent(int movementCost) { 
		this.movementCost = movementCost;
	}
}
