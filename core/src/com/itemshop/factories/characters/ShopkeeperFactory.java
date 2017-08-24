package com.itemshop.factories.characters;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.character.Character;
import com.itemshop.container.ContainerComponent;
import com.itemshop.game.assets.Assets;
import com.itemshop.game.assets.CharacterSprites;
import com.itemshop.job.Job;
import com.itemshop.job.JobComponent;
import com.itemshop.schedule.ScheduleComponent;
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
		
		// Give the character a schedule.
		character.add(new ScheduleComponent());

		Random random = new Random();
		Lotto<CharacterSprites> picker = new Lotto<CharacterSprites>(random)
			.add(Assets.getCharacterResources(Character.Shopkeeper1), 1)
			.add(Assets.getCharacterResources(Character.Shopkeeper2), 1)
			.add(Assets.getCharacterResources(Character.Shopkeeper3), 1)
			.add(Assets.getCharacterResources(Character.Shopkeeper4), 1)
			.add(Assets.getCharacterResources(Character.Shopkeeper5), 1)
			.add(Assets.getCharacterResources(Character.Shopkeeper6), 1)
			.add(Assets.getCharacterResources(Character.Shopkeeper7), 1)
			.add(Assets.getCharacterResources(Character.Shopkeeper8), 1);
		
		// Give the character an initial position.
		character.add(Utilities.getRandomPosition());
		
		Utilities.setUpWalkingCharacter(engine, character, picker.draw());
		
		// All shopkeepers must be assigned their job role or they won't do any work.
		character.add(new JobComponent(Job.SHOPWORKER));
		
		// All shopkeepers must have a container component to represent their inventory.
		character.add(new ContainerComponent(5));
		
		return character;
	}
}
