package com.itemshop.render;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A component describing the texture to use in drawing the component.
 */
public class TextureComponent implements Component {
	
	/** The texture to use in drawing the component. */
	public TextureRegion region;
	
	/**
	 * Create a new instance of TextureComponent.
	 * @param region
	 */
	public TextureComponent(TextureRegion region) { this.region = region; }
}
