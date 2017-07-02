package com.itemshop.input;

import com.badlogic.gdx.math.Vector2;

/**
 * An action that can be performed in response to mouse interaction.
 */
public interface MouseInputAction {
	
	/**
	 * Performs the action.
	 * @param deltaTime The amount of time that has passed.
	 * @param deltaPosition The amount of movement since the last update.
	 */
	void perform(float deltaTime, Vector2 deltaPosition);
}
