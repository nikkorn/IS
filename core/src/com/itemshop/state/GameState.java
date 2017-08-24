package com.itemshop.state;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.itemshop.factories.CameraFactory;
import com.itemshop.factories.ShopFactory;
import com.itemshop.factories.TownEntityFactory;
import com.itemshop.factories.characters.NpcFactory;
import com.itemshop.factories.characters.DeliveryGuyFactory;
import com.itemshop.factories.characters.ShopkeeperFactory;

/**
 * The game state in which the player is in town.
 */
public class GameState implements IState {

	private OrthographicCamera worldCamera;

	public GameState(OrthographicCamera worldCamera) {
		this.worldCamera = worldCamera;
	}
	
	public State getState() {
		return State.Game;
	}

	/**
	 * Performed when the game state is switched to.
	 * @param engine the game engine.
	 */
	public void beginState(Engine engine) {

		// Create the world camera.
		CameraFactory.create(engine, worldCamera);

		// Create the entities we need for this state.
		TownEntityFactory.createTown(engine);
		
		// Create the shop entity.
		ShopFactory.create(engine);

		// Create a test player.
		for (int i = 0; i < 25; i++) {
			engine.addEntity(ShopkeeperFactory.create(engine));
		}

		// Create a test player.
		for (int i = 0; i < 50; i++) {
			engine.addEntity(NpcFactory.create(engine));
		}

		// Create the delivery guy.
		engine.addEntity(DeliveryGuyFactory.create(engine));
	}

	/**
	 * Performed when the game state is switched away from.
	 * @param engine the game engine.
	 */
	public void endState(Engine engine) {
		// Clear up the town screen entities.
		for (Entity entity : engine.getEntities()) {
			engine.removeEntity(entity);
		}
	}
}
