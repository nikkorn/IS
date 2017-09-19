package com.itemshop.factories.characters;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.character.Character;
import com.itemshop.container.ContainerComponent;
import com.itemshop.game.assets.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.schedule.ScheduleComponent;

/**
 * Creates the Gilbert entity.
 */
public class GilbertFactory {
	
	/**
	 * Creates the Gilbert entity.
	 * @param engine The game engine.
	 * @returns gilbert.
	 */
	public static Entity create(Engine engine) {

		Entity character = new Entity();

		Utilities.setUpWalkingCharacter(engine, character, Assets.getCharacterResources(Character.Shopkeeper1));
		
		// Give Gilbert an initial position.
		character.add(new PositionComponent(1, 48, 1));
		
		// Give Gilbert an inventory.
		character.add(new ContainerComponent(10));
		
		// Create a schedule for Gilbert.
		character.add(new ScheduleComponent());
		
		engine.addEntity(character);
		
		return character;
	}
}
