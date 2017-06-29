package com.itemshop.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.itemshop.game.Assets;

/**
 * Stand alone render system for drawing the in-game UI.
 */
public class UserInterface {
	
	/** Textures. */
	TextureRegion dollarTexture;
	TextureRegion cursorTexture;
	
	/** Sprite batch shared with the rest of the game. */
	SpriteBatch spriteBatch;
	
	/** Camera for UI scaling. */
	OrthographicCamera camera;

	public UserInterface(SpriteBatch spriteBatch) {
		this.dollarTexture = Assets.dollar;
		this.cursorTexture = Assets.cursor;
		this.spriteBatch = spriteBatch;
		
		// Create the camera with the same width and height as device.
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		this.camera = new OrthographicCamera(screenWidth, screenHeight);
		
		// Position it so that 0,0 is the bottom-left of the screen.
		camera.translate(screenWidth / 2, screenHeight / 2);
		camera.update();
	}

	public void render() {
		// Sprite batch is shared, apply our camera.
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.draw(dollarTexture, 0, 0);
		spriteBatch.draw(cursorTexture, Gdx.input.getX(), -Gdx.input.getY() + Gdx.graphics.getHeight(), 16, 16);
	}
}
