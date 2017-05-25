package com.itemshop.render;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

/**
 * A component describing the texture to use in drawing the component.
 */
public class TextureComponent implements Component {
	
	/** The texture to use in drawing the component. */
	public Texture texture;
	
	/**
	 * Create a new instance of TextureComponent.
	 * @param texture
	 */
	public TextureComponent(Texture texture) { this.texture = texture; }
}
