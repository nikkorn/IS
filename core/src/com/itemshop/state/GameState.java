package com.itemshop.state;

import com.badlogic.ashley.core.Engine;
import com.itemshop.input.TestFactory;

/**
 * The game state in which the player is in town.
 */
public class GameState implements IState {
	
	public State getState() { return State.Game; }
	
	/**
	 * Performed when the game state is switched to.
	 * @param engine The game engine.
	 */
	public void beginState(Engine engine) {
		
		// Create the game screen entities.
		System.out.println("Beginning Game state");
		
		// We only have a test entity so use that for now.
		TestFactory.create(engine);
	}

	/**
	 * Performed when the game state is switched away from.
	 * @param engine The game engine.
	 */
	public void endState(Engine engine) {
		
		System.out.println("Ending Game state");
		
		// Clear up the town screen entities.
		engine.removeAllEntities();
	}
}
