package com.itemshop.factories.characters;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.area.Area;
import com.itemshop.area.AreaSystem;
import com.itemshop.area.TileType;
import com.itemshop.character.Character;
import com.itemshop.game.assets.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.schedule.ActivityPlanner;
import com.itemshop.schedule.Appointment;
import com.itemshop.schedule.ScheduleComponent;
import com.itemshop.schedule.activities.ClimbIntoContainerActivity;
import com.itemshop.schedule.activities.ClimbOutOfContainerActivity;
import com.itemshop.schedule.activities.TalkActivity;

/**
 * Creates the Walter entity.
 */
public class WalterFactory {
	
	/**
	 * Creates a shopkeeper entity.
	 * @param engine The game engine.
	 * @returns The shopkeeper entity.
	 */
	public static Entity create(Engine engine) {

		Entity character = new Entity();

		Utilities.setUpWalkingCharacter(engine, character, Assets.getCharacterResources(Character.Builder));
		
		// Give the delivery guy an initial position.
		character.add(new PositionComponent(1, 48, 1));
		
		// Create a schedule for walter.
		ScheduleComponent schedule = new ScheduleComponent();
		
		ActivityPlanner sleepPlan = (doer, current) -> {
			// All other activities should be disposed of.
			current.clear();
			// Get Walter's bed entity.
			Entity bed = engine.getSystem(AreaSystem.class).getTilesOfType(TileType.BED, Area.BUILDING_1).get(0);
			// Go climb into bed.
			current.add(new ClimbIntoContainerActivity(doer, bed));
		};
		
		ActivityPlanner wakeUpPlan = (doer, current) -> {
			// All other activities should be disposed of.
			current.clear();
			// Get Walter's bed entity.
			Entity bed = engine.getSystem(AreaSystem.class).getTilesOfType(TileType.BED, Area.BUILDING_1).get(0);
			// Get out of bed.
			current.add(new ClimbOutOfContainerActivity(doer, bed));
			// Comment on a great night sleep.
			current.add(new TalkActivity(doer, "What a great nap!"));
		};
		
		// Schedule appointments for Walter.
		schedule.appointments.add(new Appointment(9, 00, true, wakeUpPlan));
		schedule.appointments.add(new Appointment(20, 00, true, sleepPlan));
		
		// Give the delivery guy the schedule.
		character.add(schedule);
		
		engine.addEntity(character);
		
		return character;
	}
}
