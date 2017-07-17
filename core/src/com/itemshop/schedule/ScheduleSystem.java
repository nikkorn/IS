package com.itemshop.schedule;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Handles processing of each entity's schedule component.
 */
public class ScheduleSystem extends IteratingSystem {

	/** The time. */
	private Time time;

	/**
	 * Create a new instance of the schedule system.
	 * @param time
	 */
	public ScheduleSystem(Time time) {
		super(Family.all(ScheduleComponent.class).get());
		this.time = time;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
	}
}
