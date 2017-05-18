package com.itemshop.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The game state in which the player is in town.
 */
public class TownState implements IState {

	@Override
	public void render() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public String getStateName() { return "town"; }
}
