package com.itemshop.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itemshop.input.InputSystem;
import com.itemshop.state.IState;
import com.itemshop.state.TownState;

/**
 * Represents the game.
 */
public class Game extends ApplicationAdapter {
	/** The current game state. */
	IState state;
	/** The SpriteBatch to use throughout the application. */
	SpriteBatch batch;
	/**  The game engine */
	Engine engine;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		state = getInitialState();
		
		// Create the overall engine.
		engine = new Engine();
		
		// Add the input system.
		engine.addSystem(new InputSystem());
		
		// Add a test entity to verify the input system.
		com.itemshop.input.TestFactory.create(engine);
	}

	@Override
	public void render () {
		if (state != null) {
			// Render the current state.
			state.render();
			// Draw the current state.
			state.draw(batch);
		}
		
		// Update the engine.
		engine.update(Gdx.graphics.getDeltaTime());
	}
	
	/**
	 * Get the inital game state.
	 * @return initial game state.
	 */
	public IState getInitialState() { return new TownState(); }
}
