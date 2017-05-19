package com.itemshop.input;

import com.badlogic.ashley.core.Entity;

/**
 * An action that can be performed in response to an input.
 */
public interface InputAction {
	
	/**
	 * Performs the action.
	 * @param entity The entity performing the action.
	 */
	void perform(Entity entity);
}
