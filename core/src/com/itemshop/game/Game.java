package com.itemshop.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		state = getInitialState();
	}

	@Override
	public void render () {
		if (state != null) {
			// Render the current state.
			state.render();
			// Draw the current state.
			state.draw(batch);
		}
	}
	
	/**
	 * Get the inital game state.
	 * @return initial game state.
	 */
	public IState getInitialState() { return new TownState(); }
}
