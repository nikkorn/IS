package com.itemshop.character.walking;

import com.badlogic.ashley.core.Component;

/**
 * A component describing a target position.
 */
public class TargetPositionComponent implements Component {

	/** The x position. */
	public float x;

	/** The y position. */
	public float y;

	/**
	 * Create a new instance of TargetPositionComponent.
	 * @param x
	 * @param y
	 */
	public TargetPositionComponent(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
