package com.itemshop.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;

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
		InputComponent inputComponent = new InputComponent();
		inputComponent.actions.put(Input.Keys.UP, (Entity target) -> { System.out.println("Up in yo grill!"); });
		inputComponent.actions.put(Input.Keys.DOWN, (Entity target) -> { System.out.println("Down to partay!"); });
		inputComponent.actions.put(Input.Keys.LEFT, (Entity target) -> { System.out.println("Left alone!"); });
		inputComponent.actions.put(Input.Keys.RIGHT, (Entity target) -> { System.out.println("Right on it!"); });
		
		// Link the component to the entity.
		entity.add(inputComponent);
		
		// Put the entity into the engine. 
		engine.addEntity(entity);
	}
}
