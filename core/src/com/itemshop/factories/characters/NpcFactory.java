package com.itemshop.factories.characters;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.character.Character;
import com.itemshop.game.assets.Assets;
import com.itemshop.game.assets.CharacterSprites;
import com.itemshop.schedule.ScheduleComponent;
import com.itemshop.utilities.lotto.Lotto;

/**
 * Creates the blob entity.
 */
public class NpcFactory {
	
	/**
	 * Creates an NPC entity.
	 * @param engine The game engine.
	 * @returns The NPC entity.
	 */
	public static Entity create(Engine engine) {

		Entity character = new Entity();
		
		// Give the character a schedule.
		character.add(new ScheduleComponent());

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
			.add(Assets.getCharacterResources(Character.Robot), 1);
		
		// Give the character an initial position.
		character.add(Utilities.getRandomPosition());

		Utilities.setUpWalkingCharacter(engine, character, picker.draw());
		
		Utilities.setUpRandomWandering(engine, character);
		
		return character;
	}
}
