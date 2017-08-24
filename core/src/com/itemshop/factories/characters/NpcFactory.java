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
			.add(Assets.getCharacterResources(Character.Mole), 1)
			.add(Assets.getCharacterResources(Character.Greaser), 1)
			.add(Assets.getCharacterResources(Character.Scientist), 1)
			.add(Assets.getCharacterResources(Character.Lumberjack), 1)
			.add(Assets.getCharacterResources(Character.Lady), 1)
			.add(Assets.getCharacterResources(Character.Ghost), 1)
			.add(Assets.getCharacterResources(Character.Skeleton), 1)
			.add(Assets.getCharacterResources(Character.Knight1), 1)
			.add(Assets.getCharacterResources(Character.Knight2), 1)
			.add(Assets.getCharacterResources(Character.Knight3), 1)
			.add(Assets.getCharacterResources(Character.Knight4), 1)
			.add(Assets.getCharacterResources(Character.Eyeball), 1)
			.add(Assets.getCharacterResources(Character.Robot), 1)
			.add(Assets.getCharacterResources(Character.Chef), 1)
			.add(Assets.getCharacterResources(Character.SpaceMarine), 1)
			.add(Assets.getCharacterResources(Character.Builder), 1)
			.add(Assets.getCharacterResources(Character.Priest), 1)
			.add(Assets.getCharacterResources(Character.Baby), 1)
			.add(Assets.getCharacterResources(Character.Nerd), 1)
			.add(Assets.getCharacterResources(Character.Wizard), 1);

		Utilities.setUpWalkingCharacter(engine, character, picker.draw());
		
		Utilities.setUpRandomWandering(engine, character);
		
		return character;
	}
}
