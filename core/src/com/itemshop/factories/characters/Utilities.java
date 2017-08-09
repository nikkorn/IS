package com.itemshop.factories.characters;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.itemshop.character.FacingDirectionComponent;
import com.itemshop.character.walking.WalkComponent;
import com.itemshop.game.assets.CharacterSprites;
import com.itemshop.movement.Direction;
import com.itemshop.render.AnimationComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.RenderOffsetComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.schedule.ActivityPlanner;
import com.itemshop.schedule.Appointment;
import com.itemshop.schedule.ScheduleComponent;
import com.itemshop.schedule.activities.WaitActivity;
import com.itemshop.schedule.activities.WalkActivity;
import com.itemshop.utilities.lotto.Lotto;

public class Utilities {
	/**
	 * Random number generator.
	 */
	private static Random random = new Random();

	/**
	 * Big list of safe walkable places that characters can walk to.
	 */
	private static Lotto<Vector2> safeLocations = new Lotto<Vector2>(random)
			.add(new Vector2(27, 2))
			.add(new Vector2(38, 5))
			.add(new Vector2(36, 7))
			.add(new Vector2(27, 5))
			.add(new Vector2(27, 14))
			.add(new Vector2(15, 9))
			.add(new Vector2(20, 7))
			.add(new Vector2(19, 10))
			.add(new Vector2(16, 15))
			.add(new Vector2(20, 15))
			.add(new Vector2(18, 21))
			.add(new Vector2(21, 19))
			.add(new Vector2(25, 19))
			.add(new Vector2(30, 20))
			.add(new Vector2(33, 25))
			.add(new Vector2(41, 23))
			.add(new Vector2(41, 18))
			.add(new Vector2(42, 14))
			.add(new Vector2(34, 15))
			.add(new Vector2(36, 12))
			.add(new Vector2(48, 25))
			.add(new Vector2(38, 38))
			.add(new Vector2(47, 30))
			.add(new Vector2(44, 36))
			.add(new Vector2(38, 36))
			.add(new Vector2(40, 33))
			.add(new Vector2(38, 41))
			.add(new Vector2(39, 45))
			.add(new Vector2(34, 48))
			.add(new Vector2(24, 40))
			.add(new Vector2(9, 43))
			.add(new Vector2(6, 37))
			.add(new Vector2(9, 35))
			.add(new Vector2(13, 39))
			.add(new Vector2(14, 35))
			.add(new Vector2(1, 27))
			.add(new Vector2(17, 33))
			.add(new Vector2(5, 19))
			.add(new Vector2(7, 21))
			.add(new Vector2(6, 14))
			.add(new Vector2(22, 25))
			.add(new Vector2(25, 25))
			.add(new Vector2(24, 27))
			.add(new Vector2(22, 31))
			.add(new Vector2(25, 31))
			.add(new Vector2(21, 30))
			.add(new Vector2(25, 35))
			.add(new Vector2(28, 30))
			.add(new Vector2(29, 34))
			.add(new Vector2(29, 27))
			.add(new Vector2(30, 25));

	/**
	 * Assigns the textures and walk logic necessary to allow a character entity to walk around.
	 * @param engine The game engine.
	 * @param character The player in the game.
	 * @param sprites The set of sprites for the walking character to use.
	 */
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
			// The player is just changing direction, so they need the correct walking
			// animation to reflect this.
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

		// All characters will have a render offset so they are drawn in the centre of a
		// tile.
		character.add(new RenderOffsetComponent(0f, 0.25f));
	}

	/**
	 * Sets up a character with a random wandering schedule.
	 * @param engine The game engine.
	 * @param character The character to wander randomly.
	 */
	public static void setUpRandomWandering(Engine engine, Entity character) {
		// Give the delivery guy an initial position.
		character.add(getRandomPosition());

		// Create a schedule for the delivery guy.
		ScheduleComponent schedule = new ScheduleComponent();

		// Create the list of activities required to carry out a delivery.
		ActivityPlanner wanderingPlan = (current) -> {
			// Clear the current activities.
			current.clear();
			// Walk somewhere.
			current.add(getRandomWalk(character));
			// Wait for a while.
			current.add(new WaitActivity(2000));
			// Walk somewhere else.
			current.add(getRandomWalk(character));
		};

		int offset = random.nextInt(60);

		for (int hour = 0; hour < 24; hour++) {
			schedule.appointments.add(new Appointment(hour, offset, true, wanderingPlan));
		}

		// Give the delivery guy the schedule
		character.add(schedule);
	}

	/**
	 * Gets a random (walkable) position.
	 * @return The random position.
	 */
	private static PositionComponent getRandomPosition() {
		Vector2 location = safeLocations.draw();
		return new PositionComponent(location.x, location.y, 1);
	}

	/**
	 * Creates a walk activity to a random (walkable) location.
	 * @param character The character that is walking.
	 * @return The walk activity.
	 */
	private static WalkActivity getRandomWalk(Entity character) {
		Vector2 location = safeLocations.draw();
		return new WalkActivity(character, (int)location.x, (int)location.y);
	}
}
