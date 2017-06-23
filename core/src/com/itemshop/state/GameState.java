package com.itemshop.state;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.factories.TestFactory;
import com.itemshop.factories.TownEntityFactory;
import com.itemshop.factories.items.AppleFactory;
import com.itemshop.input.MouseComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.SizeComponent;

/**
 * The game state in which the player is in town.
 */
public class GameState implements IState {
	
	public State getState() { return State.Game; }
	
	/**
	 * Performed when the game state is switched to.
	 * @param engine The game engine.
	 */
	public void beginState(Engine engine) {
		
		// Create the game screen entities.
		System.out.println("Beginning Game state");
		
		// We only have a test entity so use that for now.
		TestFactory.create(engine);
		
		// Create the entities we need for this state.
		TownEntityFactory.createTown(engine);
		
		// Create a test item.
		Entity apple = new AppleFactory().create();
		apple.add(new PositionComponent(25,25,1));
		apple.add(new SizeComponent(1,1));
		
		// Do something when the item is clicked on.
		MouseComponent mouseComponent = new MouseComponent();
		mouseComponent.onBeginClick = (hoveredEntity) -> {
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
		
		System.out.println("Ending Game state");
		
		// Clear up the town screen entities.
		for (Entity entity: engine.getEntities()) {
			engine.removeEntity(entity);
		}
	}
}
