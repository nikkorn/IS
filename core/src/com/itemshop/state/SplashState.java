package com.itemshop.state;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import factories.TestFactory;

/**
 * The splash screen state.
 */
public class SplashState implements IState {
	
	public State getState() { return State.Splash; }

	/**
	 * Performed when the game state is switched to.
	 * @param engine The game engine.
	 */
	public void beginState(Engine engine) {
		
		// Create the splash screen entities.
		System.out.println("Beginning Splash state");
		
		// We only have a test entity so use that for now.
		TestFactory.create(engine);
	}

	/**
	 * Performed when the game state is switched away from.
	 * @param engine The game engine.
	 */
	public void endState(Engine engine) {
		
		System.out.println("Ending Splash state");
		
		// Clear up the splash screen entities.
		for (Entity entity: engine.getEntities()) {
			engine.removeEntity(entity);
		}
	}
}
