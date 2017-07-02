package com.itemshop.factories.characters;

import com.badlogic.ashley.core.Entity;
import com.itemshop.character.FacingDirectionComponent;
import com.itemshop.character.ISCharacter;
import com.itemshop.character.walking.WalkComponent;
import com.itemshop.game.assets.Assets;
import com.itemshop.movement.Direction;

/**
 * Creates the player entity.
 */
public class PlayerFactory {
	
	/**
	 * Creates the player entity.
	 * @returns The player entity.
	 */
	public static Entity create() {

		Entity player = new Entity();

		// Handle walking changes.
		WalkComponent walkingComponent = new WalkComponent();
		walkingComponent.onStart = (direction) -> {
			System.out.println("Walking started!");
		};
		walkingComponent.onDirectionChange = (direction) -> {
			System.out.println("Direction changed!");
		};
		walkingComponent.onStop = (direction) -> {
			System.out.println("Walking stopped!");
		};
		player.add(walkingComponent);
		
		// All characters will have a facing direction.
		player.add(new FacingDirectionComponent(Direction.DOWN));
		
		// Add the visual component for the player character.
		player.add(Assets.getCharacterResources(ISCharacter.PLAYER).getTexture(Direction.DOWN));
		
		return player;
	}
}
