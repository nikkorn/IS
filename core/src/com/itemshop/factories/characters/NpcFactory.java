package com.itemshop.factories.characters;

import java.util.Random;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.character.Character;
import com.itemshop.game.assets.Assets;
import com.itemshop.game.assets.CharacterSprites;
import com.itemshop.utilities.lotto.Lotto;

/**
 * Creates the blob entity.
 */
public class NpcFactory {
	
	/**
	 * Creates a blob entity.
	 * @param engine The game engine.
	 * @returns The shopkeeper entity.
	 */
	public static Entity create(Engine engine) {

		Entity character = new Entity();

		Random random = new Random();
		Lotto<CharacterSprites> picker = new Lotto<CharacterSprites>(random)
			.add(Assets.getCharacterResources(Character.GreenBlob), 1)
			.add(Assets.getCharacterResources(Character.OrangeBlob), 1)
			.add(Assets.getCharacterResources(Character.MoleKing), 1)
			.add(Assets.getCharacterResources(Character.Mole), 1);

		Utilities.setUpWalkingCharacter(engine, character, picker.draw());
		
		Utilities.setUpRandomWandering(engine, character);
		
		return character;
	}
}
