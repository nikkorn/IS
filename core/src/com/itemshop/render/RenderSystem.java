package com.itemshop.render;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Handles processing of each entity's render component.
 */
public class RenderSystem extends EntitySystem {

	/** The SpriteBatch to use throughout the application. */
	SpriteBatch batch;
	
	/**
	 * Constructs the render system instance.
	 */
	public RenderSystem()
	{
		// Don't really have a plan for this file, just threw in
		// a few bits that didn't fit inside the Game class any more.
		
		batch = new SpriteBatch();
	}

	/**
	 * Updates the system.
	 * @param Time since last update.
	 */
    public void update(float deltaTime) {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
