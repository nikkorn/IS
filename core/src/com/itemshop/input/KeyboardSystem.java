package com.itemshop.input;

import java.util.Map;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;

/**
 * Handles processing of each entity's input component.
 */
public class KeyboardSystem extends IteratingSystem {
	
	/** The mechanism used to retrieve each entity's input component. */
	static final ComponentMapper<KeyboardComponent> mapper = ComponentMapper.getFor(KeyboardComponent.class);

	/**
	 * Creates the MouseSystem instance.
	 * @param camera The camera.
	 */
	public KeyboardSystem() {
		super(Family.all(KeyboardComponent.class).get());
	}
	
	/**
	 * Processes a single entity's keyboard machine.
	 * @param entity The entity.
	 * @param deltaTime The time that has passed since the last update.
	 */
	public void processEntity(Entity entity, float deltaTime) {
		// Get the input component.
		KeyboardComponent input = mapper.get(entity);
		
		// Loop through each input action.
		for (Map.Entry<Integer, InputAction> entry : input.actions.entrySet()) {
			
			// Check if the key was pressed.
			if(Gdx.input.isKeyJustPressed(entry.getKey())) {
				
				// Perform the action.
				entry.getValue().perform(deltaTime);
			}
		}
	}
}
