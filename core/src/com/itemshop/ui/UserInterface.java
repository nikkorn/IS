package com.itemshop.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itemshop.game.assets.Assets;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.itemshop.schedule.Clock;

/**
 * Stand alone render system for drawing the in-game UI.
 */
public class UserInterface {
	/** Textures. */
	TextureRegion dollarTexture;
	
	/** Sprite batch shared with the rest of the game. */
	SpriteBatch spriteBatch;
	
	/** Camera for UI scaling. */
	OrthographicCamera camera;

	/** The font for writing to the screen. */
	BitmapFont font;

	public UserInterface(SpriteBatch spriteBatch) {
		this.dollarTexture = Assets.dollar;
		this.spriteBatch = spriteBatch;
		this.font = Assets.font;
		
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

		font.draw(spriteBatch, Clock.getClock().getFormattedClock(), Gdx.graphics.getWidth() - 50, Gdx.graphics.getHeight() - 10);
	}
}
