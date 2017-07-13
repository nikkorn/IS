package com.itemshop.render;

import com.badlogic.ashley.core.Component;

/**
 * A size to to use when rendering the entity.
 */
public class SizeComponent implements Component {

	/** The width. */
	public float width = 0f;
	
	/** The height. */
	public float height = 0f;
	
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