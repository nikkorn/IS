package com.itemshop.movement;

import com.badlogic.ashley.core.Component;

/**
 * A component that defines a transition between tiles.
 */
public class MovementTileTransitionComponent implements Component {
	
	/** The direction of the transition */
	public Direction direction;
	
	/** The offset amount. 1f = Tile Size */
	public float offset = 0f;
	
	/**
	 * Create a new instance of MovementTileTransition.
	 * @param direction
	 */
	public MovementTileTransitionComponent(Direction direction) { 
		this(direction, 0f); 
	}
	
	/**
	 * Create a new instance of MovementTileTransition.
	 * @param direction
	 * @param offset
	 */
	public MovementTileTransitionComponent(Direction direction, float offset) {
		this.direction = direction;
		this.offset    = offset;
	}
}