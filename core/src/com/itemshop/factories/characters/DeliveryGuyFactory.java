package com.itemshop.factories.characters;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.character.ISCharacter;
import com.itemshop.game.assets.Assets;

/**
 * Creates the shopkeeper entity.
 */
public class DeliveryGuyFactory {
	
	/**
	 * Creates a shopkeeper entity.
	 * @param engine The game engine.
	 * @returns The shopkeeper entity.
	 */
	public static Entity create(Engine engine) {

		Entity character = new Entity();

		Utilities.setUpWalkingCharacter(engine, character, Assets.getCharacterResources(ISCharacter.DeliveryMan));
		
		return character;
	}
}
