package com.itemshop.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.itemshop.character.CharacterSystem;
import com.itemshop.input.KeyboardSystem;
import com.itemshop.input.MouseSystem;
import com.itemshop.render.RenderSystem;
import com.itemshop.state.IState;
import com.itemshop.state.StateSystem;

/**
 * Represents the game.
 */
public class Game extends ApplicationAdapter {
	/** The current game state. */
	IState state;
	/** The game engine */
	Engine engine;
	
	@Override
	public void create() {
		// Create the overall engine.
		engine = new Engine();
		
		// Load the game assets.
		Assets.load();
		
		// Create the camera.
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		OrthographicCamera camera = new OrthographicCamera(screenWidth, screenHeight);
		
		// Add the systems.
		engine.addSystem(new StateSystem());
		engine.addSystem(new CharacterSystem());
		engine.addSystem(new KeyboardSystem());
		engine.addSystem(new MouseSystem(camera));
		engine.addSystem(new RenderSystem(camera));
	}

	@Override
	public void render() {
		// Update the engine.
		engine.update(Gdx.graphics.getDeltaTime());
	}
}
