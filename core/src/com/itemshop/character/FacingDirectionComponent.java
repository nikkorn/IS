package com.itemshop.character;

import com.badlogic.ashley.core.Component;

/**
 * A component that defines a face-able direction.
 */
public class FacingDirectionComponent implements Component {
	
	/** The facing direction. Default is down to face camera. */
	public FacingDirection direction = FacingDirection.DOWN;
	
	/**
	 * Create a new instance of FacingDirection.
	 * @param direction
	 */
	public FacingDirectionComponent(FacingDirection direction) { this.direction = direction; }
}
