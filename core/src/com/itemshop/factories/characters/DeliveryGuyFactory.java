package com.itemshop.factories.characters;

import java.util.ArrayList;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.character.Character;
import com.itemshop.game.assets.Assets;
import com.itemshop.movement.Direction;
import com.itemshop.render.PositionComponent;
import com.itemshop.schedule.Activity;
import com.itemshop.schedule.Appointment;
import com.itemshop.schedule.ScheduleComponent;
import com.itemshop.schedule.activities.FaceDirectionActivity;
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
		
		// Create the list of activities required to carry out a delivery.
		ArrayList<Activity> deliveryActivities = new ArrayList<Activity>() {{
			// Add an activity to walk to the shop.
			add(new WalkActivity(character, 25, 31));
			// Turn to face a container.
			add(new FaceDirectionActivity(character, Direction.RIGHT));
			// Add an activity to wait there for a couple of seconds.
			add(new WaitActivity(2000));
			// Add an activity to walk out of town.
			add(new WalkActivity(character, 0, 27));
		}};
		
		// Schedule an appointment for the delivery man to walk to the shop and back every now and then.
		schedule.appointments.add(new Appointment(9, 35, true, deliveryActivities));
		schedule.appointments.add(new Appointment(8, 00, true, deliveryActivities));
		schedule.appointments.add(new Appointment(10, 00, true, deliveryActivities));
		schedule.appointments.add(new Appointment(12, 30, true, deliveryActivities));
		schedule.appointments.add(new Appointment(14, 00, true, deliveryActivities));
		schedule.appointments.add(new Appointment(16, 00, true, deliveryActivities));
		
		// Give the delivery guy the schedule
		character.add(schedule);
		
		engine.addEntity(character);
		
		return character;
	}
}
