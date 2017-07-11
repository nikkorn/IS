package com.itemshop.movement;

import com.badlogic.ashley.core.Component;

/**
 * A component that defines a walkable tile.
 */
public class WalkableTileComponent implements Component {
	
	/** The movement cost to walk on this tile. */
	public int movementCost;
	
	/** The walkable tile event handlers. */
	public WalkableTileEvents events;
	
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
		this(movementCost, new WalkableTileEvents() {
			@Override
			public void onEntry() {}

			@Override
			public void onExit() {}
		} );
	}
	
	/**
	 * Create a new instance of MovementTileTransition.
	 * @param movementCost The movement cost to walk on this tile.
	 * @param events the event handlers for this component
	 */
	public WalkableTileComponent(int movementCost, WalkableTileEvents events) { 
		this.movementCost = movementCost;
		this.events       = events;
	}
}
