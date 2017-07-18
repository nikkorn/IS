package com.itemshop.state;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.itemshop.character.walking.PathComponent;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.itemshop.factories.CameraFactory;
import com.itemshop.factories.TownEntityFactory;
import com.itemshop.factories.characters.DeliveryGuyFactory;
import com.itemshop.factories.characters.PlayerFactory;
import com.itemshop.input.MouseComponent;
import com.itemshop.movement.WalkableTileComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.schedule.ScheduleComponent;

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
		createPlayer(engine);

		// Create the delivery guy.
		createDeliveryGuy(engine);
	}

	/**
	 * Create a player.
	 * @param engine
	 */
	private void createPlayer(Engine engine) {
		// Add the player to the town.
		Entity player = PlayerFactory.create();
		player.add(new PositionComponent(25, 28, 1));
		engine.addEntity(player);

		// Temporarily hook up walkable tile presses to player movement.
		ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
		engine.addEntityListener(Family.all(WalkableTileComponent.class).get(), new EntityListener() {

			@Override
			public void entityAdded(Entity entity) {
				// Get the position of this tile.
				PositionComponent position = positionMapper.get(entity);
				// Hook up a press of this tile to moving our player.
				MouseComponent mouseComponent = new MouseComponent();
				mouseComponent.onBeginClick = (deltaTime, deltaPosition) -> {
					player.add(new PathComponent(position.x, position.y));
				};
				entity.add(mouseComponent);
			}

			@Override
			public void entityRemoved(Entity entity) {
			}
		});
	}

	/**
	 * Create a delivery guy character.
	 * @param engine
	 */
	private void createDeliveryGuy(Engine engine) {
		// Add the the delivery guy to the town.
		Entity deliveryGuy = DeliveryGuyFactory.create();
		
		// Give the delivery guy an initial position.
		deliveryGuy.add(new PositionComponent(0, 27, 1));
		
		// Give the delivery guy a schedule.
		deliveryGuy.add(new ScheduleComponent());
		
		engine.addEntity(deliveryGuy);
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
