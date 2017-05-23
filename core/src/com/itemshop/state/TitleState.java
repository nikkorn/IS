package com.itemshop.state;

import com.badlogic.ashley.core.Engine;
import factories.TestFactory;

/**
 * The title screen state.
 */
public class TitleState implements IState {
	
	public State getState() { return State.Title; }
	
	/**
	 * Performed when the game state is switched to.
	 * @param engine The game engine.
	 */
	public void beginState(Engine engine) {
		
		// Create the title screen entities.
		System.out.println("Beginning Title state");
		
		// We only have a test entity so use that for now.
		TestFactory.create(engine);
	}

	/**
	 * Performed when the game state is switched away from.
	 * @param engine The game engine.
	 */
	public void endState(Engine engine) {
		
		System.out.println("Ending Title state");
		
		// Clear up the title screen entities.
		engine.removeAllEntities();
	}
}
