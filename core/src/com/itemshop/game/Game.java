package com.itemshop.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itemshop.character.walking.WalkingSystem;
import com.itemshop.game.assets.Assets;
import com.itemshop.input.KeyboardSystem;
import com.itemshop.input.MouseSystem;
import com.itemshop.render.RenderSystem;
import com.itemshop.state.StateSystem;
import com.itemshop.ui.UISystem;

/**
 * Represents the game.
 */
public class Game extends ApplicationAdapter {
	
	/** The size of the map. */
	public static final int MAP_SIZE = 50;
	
	/**
	 * The scaling factor to apply to convert map coordinates to screen pixels.
	 * Zoom so that the each sprite pixel is 2 screen pixels.
	 */
	public static final float WORLD_ZOOM = (1f / 16f) / 2;

	/** The game engine. */
	Engine engine;
	
	/** The sprite batch. */
	SpriteBatch spriteBatch;
	
	@Override
	public void create() {
		// Create the overall engine.
		engine = new Engine();
		
		// Load the game assets.
		Assets.load();

		OrthographicCamera worldCamera = setUpWorldCamera();
		OrthographicCamera uiCamera = setUpUICamera();

		// Create the sprite batch.
		spriteBatch = new SpriteBatch(); 
		
		// Add the systems.
		engine.addSystem(new StateSystem(worldCamera));
		engine.addSystem(new WalkingSystem());
		engine.addSystem(new KeyboardSystem());
		engine.addSystem(new MouseSystem(worldCamera, uiCamera));
		engine.addSystem(new RenderSystem(worldCamera, spriteBatch));
		engine.addSystem(new UISystem(uiCamera, spriteBatch));
	}

	@Override
	public void render() {
		// Clear the screen.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		
		// Update the engine.
		engine.update(Gdx.graphics.getDeltaTime());
		
		spriteBatch.end();
	}
	
	/**
	 * Sets up the world camera.
	 * @return The world camera.
	 */
	private OrthographicCamera setUpWorldCamera() {
		// Create the camera.
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		OrthographicCamera camera = new OrthographicCamera(screenWidth, screenHeight);
		
		// Move to the middle of the map.
		camera.translate(MAP_SIZE / 2, MAP_SIZE / 2);
		
		camera.zoom = WORLD_ZOOM;
		
		camera.update();
		
		return camera;
	}

	/**
	 * Sets up the UI camera.
	 * @return The UI camera.
	 */
	private OrthographicCamera setUpUICamera() {
		// Create the camera with the same width and height as device.
		OrthographicCamera camera = new OrthographicCamera(1, 1);
		
		// Position it so that 0,0 is the bottom-left of the screen.
		camera.translate(0.5f, 0.5f);
		
		camera.zoom = 1;
		
		camera.update();
		
		return camera;
	}
}
