package com.itemshop.state;

import com.badlogic.ashley.core.Engine;

/**
 * Represents a game state.
 */
public interface IState {
	
	public State getState();
	
	/**
	 * Performed when the game state is switched to.
	 * @param engine The game engine.
	 */
	void beginState(Engine engine);

	/**
	 * Performed when the game state is switched away from.
	 * @param engine The game engine.
	 */
	void endState(Engine engine);
}
