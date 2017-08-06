package com.itemshop.factories.characters;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.character.Character;
import com.itemshop.game.assets.Assets;

/**
 * Creates the blob entity.
 */
public class BlobFactory {
	
	/**
	 * Creates a blob entity.
	 * @param engine The game engine.
	 * @returns The shopkeeper entity.
	 */
	public static Entity create(Engine engine) {

		Entity character = new Entity();

		Utilities.setUpWalkingCharacter(engine, character, Assets.getCharacterResources(Character.Blob));
		
		Utilities.setUpRandomWandering(engine, character);
		
		return character;
	}
}
