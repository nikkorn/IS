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
 * Creates the delivery guy entity.
 */
public class DeliveryGuyFactory {
	
	/**
	 * Creates the delivery guy entity.
	 * @returns The delivery guy entity.
	 */
	public static Entity create() {

		Entity character = new Entity();

		// Handle walking changes.
		WalkComponent walkingComponent = new WalkComponent();
		walkingComponent.onStart = (direction) -> {
			// Remove the characters idle texture.
			character.remove(TextureComponent.class);
			// Set the characters walking animation.
			character.add(Assets.getCharacterResources(ISCharacter.DELIVER_GUY).getAnimationComponent(direction));
		};
		walkingComponent.onDirectionChange = (direction) -> {
			// The character is just changing direction, so they need the correct walking animation to reflect this.
			character.add(Assets.getCharacterResources(ISCharacter.DELIVER_GUY).getAnimationComponent(direction));
		};
		walkingComponent.onStop = (direction) -> {
			// Remove the characters walking animation.
			character.remove(AnimationComponent.class);
			// Set the characters's idle texture.
			character.add(Assets.getCharacterResources(ISCharacter.DELIVER_GUY).getTextureComponent(direction));
		};
		character.add(walkingComponent);
		
		// All characters will have a render offset so they are drawn in the centre of a tile.
		character.add(new RenderOffsetComponent(0f, 0.25f));
		
		// All characters will have a facing direction.
		character.add(new FacingDirectionComponent(Direction.DOWN));
		
		// Add the visual component for the player character.
		character.add(Assets.getCharacterResources(ISCharacter.DELIVER_GUY).getTextureComponent(Direction.DOWN));
		
		return character;
	}
}
