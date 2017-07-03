package com.itemshop.character.walking;

import com.itemshop.movement.Direction;

/**
 * An action that can be performed in response to a change of entity walking state.
 */
public interface WalkingAction {

    /**
     * Performs the action.
     * @param direction the direction.
     */
    void perform(Direction direction);
}
