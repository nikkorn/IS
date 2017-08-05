package com.itemshop.factories.characters;

import java.util.Random;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.character.ISCharacter;
import com.itemshop.game.assets.Assets;
import com.itemshop.game.assets.CharacterSprites;
import com.itemshop.utilities.lotto.Lotto;

/**
 * Creates the shopkeeper entity.
 */
public class ShopkeeperFactory {
	
	/**
	 * Creates a shopkeeper entity.
	 * @param engine The game engine.
	 * @returns The shopkeeper entity.
	 */
	public static Entity create(Engine engine) {

		Entity character = new Entity();

		Random random = new Random();
		Lotto<CharacterSprites> picker = new Lotto<CharacterSprites>(random)
			.add(Assets.getCharacterResources(ISCharacter.Shopkeeper1), 1)
			.add(Assets.getCharacterResources(ISCharacter.Shopkeeper2), 1)
			.add(Assets.getCharacterResources(ISCharacter.Shopkeeper3), 1)
			.add(Assets.getCharacterResources(ISCharacter.Shopkeeper4), 1)
			.add(Assets.getCharacterResources(ISCharacter.Shopkeeper5), 1)
			.add(Assets.getCharacterResources(ISCharacter.Shopkeeper6), 1)
			.add(Assets.getCharacterResources(ISCharacter.Shopkeeper7), 1);

		character.add(Utilities.getRandomPosition(engine));
		Utilities.setUpWalkingCharacter(engine, character, picker.draw());
		
		return character;
	}
}
