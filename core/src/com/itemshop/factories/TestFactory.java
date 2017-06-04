package com.itemshop.factories;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.itemshop.input.KeyboardComponent;
import com.itemshop.state.State;
import com.itemshop.state.StateSystem;

/**
 * Factory for producing entities configured to test the input system. 
 */
public class TestFactory {
	
	/**
	 * Creates the test entity.
	 * @param engine The engine to add the entity to.
	 */
	public static void create(Engine engine) {

		// Create the entity.
		Entity entity = new Entity();
		
		// Configure an input component with some simple actions.
		KeyboardComponent inputComponent = new KeyboardComponent();
		inputComponent.actions.put(Input.Keys.UP, (Entity target) -> { System.out.println("Up in yo grill!"); });
		inputComponent.actions.put(Input.Keys.DOWN, (Entity target) -> { System.out.println("Down to partay!"); });
		inputComponent.actions.put(Input.Keys.LEFT, (Entity target) -> { System.out.println("Left alone!"); });
		inputComponent.actions.put(Input.Keys.RIGHT, (Entity target) -> { System.out.println("Right on it!"); });
		
		// Add some more keys to switch the states, just for a laugh.
		StateSystem stateSystem = engine.getSystem(StateSystem.class);
		inputComponent.actions.put(Input.Keys.J, (Entity target) -> {
			System.out.println("Switching to Game state");
			stateSystem.setState(State.Game);
		});
		inputComponent.actions.put(Input.Keys.K, (Entity target) -> {
			System.out.println("Switching to Splash state");
			stateSystem.setState(State.Splash);
		});
		inputComponent.actions.put(Input.Keys.L, (Entity target) -> {
			System.out.println("Switching to Title state");
			stateSystem.setState(State.Title);
		});
		
		// Link the component to the entity.
		entity.add(inputComponent);
		
		// Put the entity into the engine. 
		engine.addEntity(entity);
	}
}
