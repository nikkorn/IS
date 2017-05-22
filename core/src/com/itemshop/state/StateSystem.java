package com.itemshop.state;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

/**
 * Handles processing of each entity's render component.
 * 
 * I am not really sure if this belongs as a "System" as
 * nothing will have a StateComponent (I think?)

 * On the other hand their is only 1 "State" at a time per
 * game and it is really easy to access if it is a "System".
 */
public class StateSystem extends EntitySystem {

	/** The current state. */
	private IState currentState;

	/**
	 * Initialize the State system.
	 */
	public void initialize() {
		
		// I had to add this initialize method separately as the states do
		// engine.getSystem(StateSystem...) which will fail if we are still
		// inside the StateSystem constructor D:
		
		// Get the engine.
		Engine engine = this.getEngine();
		
		currentState = new SplashState();
		
		// Begin the new state.
		currentState.beginState(engine);
	}
	
	/**
	 * Changes the game state.
	 */
	public void setState(State state) {
		
		if (state == currentState.getState())
		{
			return;
		}
		
		// Get the engine.
		Engine engine = this.getEngine();
		
		// End the old state .
		currentState.endState(engine);
		
		// Create the new state.
		switch(state) {
			case Splash:
				currentState = new SplashState();
				break;
			case Title:
				currentState = new TitleState();
				break;
			case Game:
				currentState = new GameState();
				break;
		}
		
		// Begin the new state.
		currentState.beginState(engine);
	}
}
