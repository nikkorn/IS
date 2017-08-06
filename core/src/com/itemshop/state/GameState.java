package com.itemshop.state;

import java.util.ArrayList;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.itemshop.character.walking.PathComponent;
import com.itemshop.factories.CameraFactory;
import com.itemshop.factories.TownEntityFactory;
import com.itemshop.factories.characters.BlobFactory;
import com.itemshop.factories.characters.DeliveryGuyFactory;
import com.itemshop.factories.characters.ShopkeeperFactory;
import com.itemshop.input.MouseComponent;
import com.itemshop.movement.Direction;
import com.itemshop.movement.WalkableTileComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.schedule.Activity;
import com.itemshop.schedule.Appointment;
import com.itemshop.schedule.ScheduleComponent;
import com.itemshop.schedule.activities.FaceDirectionActivity;
import com.itemshop.schedule.activities.WaitActivity;
import com.itemshop.schedule.activities.WalkActivity;

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

		// Create a test player.
		for (int i = 0; i < 25; i++) {
			engine.addEntity(ShopkeeperFactory.create(engine));
		}

		// Create a test player.
		for (int i = 0; i < 25; i++) {
			engine.addEntity(BlobFactory.create(engine));
		}

		// Create the delivery guy.
		engine.addEntity(DeliveryGuyFactory.create(engine));
	}

	/**
	 * Create a player.
	 * @param engine
	 */
	private void createShopkeeper(Engine engine) {
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
