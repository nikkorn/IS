package com.itemshop.factories.characters;

import com.badlogic.ashley.core.Entity;
import com.itemshop.character.FacingDirectionComponent;
import com.itemshop.character.ISCharacter;
import com.itemshop.character.walking.WalkComponent;
import com.itemshop.game.assets.Assets;
import com.itemshop.movement.Direction;
import com.itemshop.render.AnimationComponent;
import com.itemshop.render.RenderOffsetComponent;
import com.itemshop.render.TextureComponent;

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
			// Remove the players idle texture.
			player.remove(TextureComponent.class);
			// Set the players walking animation.
			player.add(Assets.getCharacterResources(ISCharacter.PLAYER).getAnimationComponent(direction));
		};
		walkingComponent.onDirectionChange = (direction) -> {
			// The player is just changing direction, so they need the correct walking animation to reflect this.
			player.add(Assets.getCharacterResources(ISCharacter.PLAYER).getAnimationComponent(direction));
		};
		walkingComponent.onStop = (direction) -> {
			// Remove the players walking animation.
			player.remove(AnimationComponent.class);
			// Set the player's idle texture.
			player.add(Assets.getCharacterResources(ISCharacter.PLAYER).getTextureComponent(direction));
		};
		player.add(walkingComponent);
		
		// All characters will have a render offset so they are drawn in the centre of a tile.
		player.add(new RenderOffsetComponent(0f, 0.25f));
		
		// All characters will have a facing direction.
		player.add(new FacingDirectionComponent(Direction.DOWN));
		
		// Add the visual component for the player character.
		player.add(Assets.getCharacterResources(ISCharacter.PLAYER).getTextureComponent(Direction.DOWN));
		
		return player;
	}
}
