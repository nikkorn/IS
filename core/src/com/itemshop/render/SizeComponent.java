package com.itemshop.render;

import com.badlogic.ashley.core.Component;

/**
 * A component describing an entities size.
 */
public class SizeComponent implements Component {
	
	/** The width. */
	public float width;
	
	/** The height. */
	public float height;
	
	/**
	 * Create a new instance of SizeComponent.
	 * @param width
	 * @param height
	 */
	public SizeComponent(float width, float height) {
		this.width  = width;
		this.height = height;
	}
}
