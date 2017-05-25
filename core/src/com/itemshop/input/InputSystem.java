package com.itemshop.input;

import java.util.Map;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;

/**
 * Handles processing of each entity's input component.
 */
public class InputSystem extends EntitySystem {
	
	/**
	 * The set of entities the system is interested in.
	 */
	static final Family family = Family.one(InputComponent.class).get();
	
	/**
	 * The mechanism used to retrieve each entity's input component.
	 */
	static final ComponentMapper<InputComponent> mapper = ComponentMapper.getFor(InputComponent.class);
	
	/**
	 * Updates the system.
	 * @param Time since last update.
	 */
    public void update(float deltaTime) {
    	
    	// Loop through each entity.
    	for (Entity entity : this.getEngine().getEntitiesFor(family)) {
    		
    		// Get the input component.
    		InputComponent input = (InputComponent) mapper.get(entity);
    		
    		// Loop through each input action.
    		for (Map.Entry<Integer, InputAction> entry : input.actions.entrySet()) {
    			
    			// Check if the key was pressed.
    			if(Gdx.input.isKeyJustPressed(entry.getKey())) {
    				
    				// Perform the action.
    				entry.getValue().perform(entity);
    			}
    		}
    	}
    }
}
