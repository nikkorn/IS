package com.itemshop.ui;

import com.badlogic.ashley.core.Component;

/**
 * A component describing a position on the screen.
 */
public class ScreenPositionComponent implements Component {
	
	/** The x position (as a percentage of the screen). */
	public float x;

	/** The y position (as a percentage of the screen). */
	public float y;

	/** The width position (as a percentage of the screen). */
	public float width;

	/** The height position (as a percentage of the screen). */
	public float height;
	
	/**
	 * 
	 * @param x The x position (as a percentage of the screen).
	 * @param y The y position (as a percentage of the screen).
	 * @param width The width position (as a percentage of the screen).
	 * @param height The height position (as a percentage of the screen).
	 */
	public ScreenPositionComponent(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}

