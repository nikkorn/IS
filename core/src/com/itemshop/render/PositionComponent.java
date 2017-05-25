package com.itemshop.render;

import com.badlogic.ashley.core.Component;

/**
 * A component describing a position.
 */
public class PositionComponent implements Component {
	
	/** The x position. */
	public float x;
	
	/** The y position. */
	public float y;
	
	/**
	 * Create a new instance of PositionComponent.
	 * @param x
	 * @param y
	 */
	public PositionComponent(float x, float y) {
		this.x = x;
		this.y = y;
	}
}

