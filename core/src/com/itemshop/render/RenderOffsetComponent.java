package com.itemshop.render;

import com.badlogic.ashley.core.Component;

/**
 * An offset to apply when rendering the entity.
 */
public class RenderOffsetComponent implements Component {

	/** The X offset amount. 1f = Tile Size */
	public float offsetX = 0f;
	
	/** The Y offset amount. 1f = Tile Size */
	public float offsetY = 0f;
	
	/**
	 * Create a new instance of RenderOffsetComponent.
	 * @param offsetX
	 * @param offsetY
	 */
	public RenderOffsetComponent(float offsetX, float offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
}
