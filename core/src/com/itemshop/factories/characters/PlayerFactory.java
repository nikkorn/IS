package com.itemshop.factories.characters;

import com.badlogic.ashley.core.Entity;
import com.itemshop.character.CharacterComponent;
import com.itemshop.character.FacingDirectionComponent;
import com.itemshop.character.ISCharacter;
import com.itemshop.game.assets.Assets;
import com.itemshop.movement.Direction;
import com.itemshop.render.SizeComponent;

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
		player.add(new CharacterComponent(ISCharacter.PLAYER));
		
		// The player character should be the size of a tile.
		player.add(new SizeComponent(1, 1));
		
		// All characters will have a facing direction.
		player.add(new FacingDirectionComponent(Direction.DOWN));
		
		// Add the visual component for the player character.
		player.add(Assets.getCharacterResources(ISCharacter.PLAYER).getTexture(Direction.DOWN));
		
		return player;
	}
}
