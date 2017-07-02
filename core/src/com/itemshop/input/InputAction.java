package com.itemshop.input;

/**
 * An action that can be performed in response to an input.
 */
public interface InputAction {
	
	/**
	 * Performs the action.
	 * @param deltaTime The amount of time that has passed.
	 */
	void perform(float deltaTime);
}
