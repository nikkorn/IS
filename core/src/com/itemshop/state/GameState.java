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
import com.itemshop.factories.characters.PlayerFactory;
import com.itemshop.factories.items.AppleFactory;
import com.itemshop.input.MouseComponent;
import com.itemshop.movement.WalkableTileComponent;
import com.itemshop.render.PositionComponent;

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
		
		// Add the player to the town.
		Entity player = PlayerFactory.create();
		player.add(new PositionComponent(25,28,1));
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
			public void entityRemoved(Entity entity) {}
		});
		
		// Create a test item.
		Entity apple = new AppleFactory().create();
		apple.add(new PositionComponent(25,37,1));
		
		// Do something when the item is clicked on.
		MouseComponent mouseComponent = new MouseComponent();
		mouseComponent.onBeginClick = (deltaTime, deltaPosition) -> {
			System.out.println("Clicked on apple!");
		};
		apple.add(mouseComponent);
		
		engine.addEntity(apple);
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
