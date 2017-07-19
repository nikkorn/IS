package com.itemshop.state;

import java.util.ArrayList;

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
import com.itemshop.schedule.Activity;
import com.itemshop.schedule.Appointment;
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
		
		// Create a schedule for the delivery guy.
		ScheduleComponent schedule = new ScheduleComponent();
		
		// Create the list of activities required to carry out a delivery.
		ArrayList<Activity> deliveryActivities = new ArrayList<Activity>() {{
		
			// Add an activity to walk to the shop.
			add(new Activity() {
				
				// The path to follow for this activity.
				PathComponent path = new PathComponent(25, 28);
				
				@Override
				public void onBegin() {
					deliveryGuy.add(path);
				}
				@Override
				public void perform() {
					if (path.isPathComputed && path.movements.size() == 0) {
						this.finish();
					}
				}
				@Override
				public void onEnd() {
					path = new PathComponent(25, 28);
				} 
				@Override
				public void onPreempt() {}
			});
		
			// Add an activity to walk out of town.
			add(new Activity() {
				
				// The path to follow for this activity.
				PathComponent path = new PathComponent(0, 27);
				
				@Override
				public void onBegin() {
					deliveryGuy.add(path);
				}
				@Override
				public void perform() {
					if (path.isPathComputed && path.movements.size() == 0) {
						this.finish();
					}
				}
				@Override
				public void onEnd() {
					path = new PathComponent(0, 27);
				} 
				@Override
				public void onPreempt() {}
			});
		}};
		
		// Schedule an appointment for the delivery man to walk to the shop and back every now and then.
		schedule.appointments.add(new Appointment(6, 00, true, deliveryActivities));
		schedule.appointments.add(new Appointment(8, 00, true, deliveryActivities));
		schedule.appointments.add(new Appointment(10, 00, true, deliveryActivities));
		schedule.appointments.add(new Appointment(12, 30, true, deliveryActivities));
		schedule.appointments.add(new Appointment(14, 00, true, deliveryActivities));
		schedule.appointments.add(new Appointment(16, 00, true, deliveryActivities));
		
		// Give the delivery guy the schedule
		deliveryGuy.add(schedule);
		
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
