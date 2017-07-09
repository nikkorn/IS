package com.itemshop.character;

import com.badlogic.ashley.core.Component;
import com.itemshop.movement.Direction;

/**
 * A component that defines a face-able direction.
 */
public class FacingDirectionComponent implements Component {
	
	/** The facing direction. Default is down to face camera. */
	public Direction direction = Direction.DOWN;
	
	/**
	 * Create a new instance of FacingDirection.
	 * @param direction
	 */
	public FacingDirectionComponent(Direction direction) { this.direction = direction; }
}
