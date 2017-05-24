package com.itemshop.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.itemshop.input.InputSystem;
import com.itemshop.render.CameraSystem;
import com.itemshop.render.RenderSystem;
import com.itemshop.state.IState;
import com.itemshop.state.StateSystem;

/**
 * Represents the game.
 */
public class Game extends ApplicationAdapter {
	/** The current game state. */
	IState state;
	/**  The game engine */
	Engine engine;
	
	@Override
	public void create () {
		
		// Create the overall engine.
		engine = new Engine();
		
		// Add the state system.
		StateSystem stateSystem = new StateSystem();
		engine.addSystem(stateSystem);
		stateSystem.initialize();
		
		// Add the other systems.
		engine.addSystem(new CameraSystem());
		engine.addSystem(new InputSystem());
		engine.addSystem(new RenderSystem());
		
		// Add a test entity to verify the input system.
		com.itemshop.input.TestFactory.create(engine);
	}

	@Override
	public void render () {
		// Update the engine.
		engine.update(Gdx.graphics.getDeltaTime());
	}
}
