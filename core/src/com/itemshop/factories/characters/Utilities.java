package com.itemshop.factories.characters;

import java.util.Random;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.character.FacingDirectionComponent;
import com.itemshop.character.walking.PathComponent;
import com.itemshop.character.walking.PathingSystem;
import com.itemshop.character.walking.WalkComponent;
import com.itemshop.game.assets.CharacterSprites;
import com.itemshop.movement.Direction;
import com.itemshop.render.AnimationComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.RenderOffsetComponent;
import com.itemshop.render.TextureComponent;

public class Utilities {
	private static Random random = new Random();
	
	public static void setUpWalkingCharacter(Engine engine, Entity character, CharacterSprites sprites) {
		// Handle walking changes.
		WalkComponent walkingComponent = new WalkComponent();
		walkingComponent.onStart = (direction) -> {
			// Remove the players idle texture.
			character.remove(TextureComponent.class);
			// Set the players walking animation.
			character.add(sprites.getAnimationComponent(direction));
		};
		walkingComponent.onDirectionChange = (direction) -> {
			// The player is just changing direction, so they need the correct walking animation to reflect this.
			character.add(sprites.getAnimationComponent(direction));
		};
		walkingComponent.onStop = (direction) -> {
			// Remove the players walking animation.
			character.remove(AnimationComponent.class);
			// Set the player's idle texture.
			character.add(sprites.getTextureComponent(direction));
		};
		character.add(walkingComponent);
		
		// Add the visual component for the player character.
		character.add(sprites.getTextureComponent(Direction.DOWN));
		
		// All characters will have a facing direction.
		character.add(new FacingDirectionComponent(Direction.DOWN));

		// All characters will have a render offset so they are drawn in the centre of a tile.
		character.add(new RenderOffsetComponent(0f, 0.25f));
	}
	
	private static int getRandomMapCoordinate() {
		int value = random.nextInt(50);
		System.out.println(value);
		return value;
	}
	
	public static PositionComponent getRandomPosition(Engine engine) {
		PathingSystem pathing = engine.getSystem(PathingSystem.class);
		int x = -1;
		int y = -1;
		do {
			x = getRandomMapCoordinate();
			y = getRandomMapCoordinate();
		} while (!pathing.isWalkable(x, y));
		return new PositionComponent(x, y, 1);
	}
	
	public static PathComponent getRandomDestination(Engine engine) {
		PathingSystem pathing = engine.getSystem(PathingSystem.class);
		int x = -1;
		int y = -1;
		do {
			x = getRandomMapCoordinate();
			y = getRandomMapCoordinate();
		} while (!pathing.isWalkable(x, y));
		return new PathComponent(x, y);
	}
}
