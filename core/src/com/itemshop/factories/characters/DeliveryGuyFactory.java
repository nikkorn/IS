package com.itemshop.factories.characters;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.area.Area;
import com.itemshop.area.AreaSystem;
import com.itemshop.area.TileType;
import com.itemshop.character.Character;
import com.itemshop.factories.items.RandomItemFactory;
import com.itemshop.game.assets.Assets;
import com.itemshop.money.Payment;
import com.itemshop.money.PaymentType;
import com.itemshop.money.WalletComponent;
import com.itemshop.money.WalletOwner;
import com.itemshop.render.PositionComponent;
import com.itemshop.schedule.ActivityPlanner;
import com.itemshop.schedule.Appointment;
import com.itemshop.schedule.ScheduleComponent;
import com.itemshop.schedule.activities.PaymentActivity;
import com.itemshop.schedule.activities.PlaceItemActivity;
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
		
		// Give the delivery guy a wallet.
		character.add(new WalletComponent());
		
		// Create a schedule for the delivery guy.
		ScheduleComponent schedule = new ScheduleComponent();
		
		// Create a plan to deliver an item to the shop store room.
		ActivityPlanner deliveryPlan = (doer, current) -> {
			// All other activities should be disposed of.
			current.clear();
			// Get the chests in the shop store room.
			ArrayList<Entity> chests = engine.getSystem(AreaSystem.class).getTilesOfType(TileType.CHEST, Area.STOREROOM);
			// We can't make a delivery if there are no chests in the store room.
			if (!chests.isEmpty()) {
				// Just pick any of the chests for now.
				Entity chest = chests.get(new Random().nextInt(chests.size()));
				// Create an item to deliver.
				Entity itemToDeliver = new RandomItemFactory().create();
				engine.addEntity(itemToDeliver);
				// Add an activity drop off an item to the shop.
				current.add(new PlaceItemActivity(doer, chest, itemToDeliver));
				// Charge the shop.
				current.add(new PaymentActivity(doer, new Payment(WalletOwner.SHOP, PaymentType.RECEIVE, 10)));
				// Add an activity to wait there for a second.
				current.add(new WaitActivity(1000));
				// Add an activity to walk out of town.
				current.add(new WalkActivity(doer, 0, 27));
			}
		};
		
		// Schedule an appointment for the delivery man to walk to the shop and back every now and then.
		schedule.appointments.add(new Appointment(9, 35, true, deliveryPlan));
		schedule.appointments.add(new Appointment(10, 00, true, deliveryPlan));
		schedule.appointments.add(new Appointment(12, 30, true, deliveryPlan));
		schedule.appointments.add(new Appointment(14, 00, true, deliveryPlan));
		schedule.appointments.add(new Appointment(16, 00, true, deliveryPlan));
		
		// Give the delivery guy the schedule.
		character.add(schedule);
		
		engine.addEntity(character);
		
		return character;
	}
}
