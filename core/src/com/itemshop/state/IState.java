package com.itemshop.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents a game state.
 */
public interface IState {
    
    /**
     * Render the state.
     */
    void render();
    
    /**
     * Draw the state.
     * @param batch
     */
    void draw(SpriteBatch batch);

    /**
     * Get the name of this state.
     * @return name
     */
    String getStateName();
}
