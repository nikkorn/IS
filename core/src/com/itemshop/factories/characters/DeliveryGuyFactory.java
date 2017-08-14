package com.itemshop.factories.characters;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.ashley.core.ComponentMapper;
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
import com.itemshop.schedule.activities.WaitActivity;
import com.itemshop.schedule.activities.WalkActivity;

/**
 * Creates the delivery guy entity.
 */
public class DeliveryGuyFactory {
	
	/**
	 * Creates a shopkeeper entity.
	 * @param engine The game engine.
	 * @returns The shopkeeper entity.
	 */
	public static Entity create(Engine engine) {

		Entity character = new Entity();

		Utilities.setUpWalkingCharacter(engine, character, Assets.getCharacterResources(Character.DeliveryMan));
		
		// Give the delivery guy an initial position.
		character.add(new PositionComponent(0, 27, 1));
		
		// Create a schedule for the delivery guy.
		ScheduleComponent schedule = new ScheduleComponent();
		
		// Create the delivery plan.
		ActivityPlanner deliveryPlan = (current) -> {
			// All other activities should be disposed of.
			current.clear();
			// Get the a chest in the shop store room.
			ArrayList<Entity> chests = engine.getSystem(AreaSystem.class).getTilesOfType(TileType.CHEST, Area.STOREROOM);
			// We can't make a delivery if there are no chests in the store room.
			if (!chests.isEmpty()) {
				// Just pick any of the chests for now.
				Entity chest = chests.get(new Random().nextInt(chests.size()));
				// Get the position of this chest.
				PositionComponent chestPosition = ComponentMapper.getFor(PositionComponent.class).get(chest);
				// Add an activity to walk to the shop.
				current.add(new WalkActivity(character, (int) chestPosition.x, (int) chestPosition.y));
				// Add an activity to wait there for a couple of seconds.
				current.add(new WaitActivity(2000));
				// Add an activity to walk out of town.
				current.add(new WalkActivity(character, 0, 27));
			}
		};
		
		// Schedule an appointment for the delivery man to walk to the shop and back every now and then.
		schedule.appointments.add(new Appointment(9, 35, true, deliveryPlan));
		schedule.appointments.add(new Appointment(8, 00, true, deliveryPlan));
		schedule.appointments.add(new Appointment(10, 00, true, deliveryPlan));
		schedule.appointments.add(new Appointment(12, 30, true, deliveryPlan));
		schedule.appointments.add(new Appointment(14, 00, true, deliveryPlan));
		schedule.appointments.add(new Appointment(16, 00, true, deliveryPlan));
		
		// Give the delivery guy the schedule
		character.add(schedule);
		
		engine.addEntity(character);
		
		return character;
	}
}
