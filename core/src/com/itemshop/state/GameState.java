package com.itemshop.state;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.itemshop.factories.CameraFactory;
import com.itemshop.factories.TownEntityFactory;

/**
 * The game state in which the player is in town.
 */
public class GameState implements IState {
	
	private OrthographicCamera worldCamera;
	
	public GameState(OrthographicCamera worldCamera) {
		this.worldCamera = worldCamera;
	}
	
	public State getState() { return State.Game; }
	
	/**
	 * Performed when the game state is switched to.
	 * @param engine The game engine.
	 */
	public void beginState(Engine engine) {
		
		CameraFactory.create(engine, worldCamera);
		
		// Create the entities we need for this state.
		TownEntityFactory.createTown(engine);
	}

	/**
	 * Performed when the game state is switched away from.
	 * @param engine The game engine.
	 */
	public void endState(Engine engine) {
		
		// Clear up the town screen entities.
		for (Entity entity: engine.getEntities()) {
			engine.removeEntity(entity);
		}
	}
}
