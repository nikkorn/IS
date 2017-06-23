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
	
	/** The z position. */
	public int z;
	
	/**
	 * Create a new instance of PositionComponent.
	 * @param x
	 * @param y
	 */
	public PositionComponent(float x, float y) {
		this(x, y, 0);
	}
	
	/**
	 * Create a new instance of PositionComponent.
	 * @param x
	 * @param y
	 * @param z
	 */
	public PositionComponent(float x, float y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
