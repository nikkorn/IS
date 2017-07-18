package com.itemshop.schedule;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Handles processing of each entity's schedule component.
 */
public class ScheduleSystem extends IteratingSystem {

	/** The time. */
	private Time time;
	
	/** The time at which this system was last updated. */
	private String lastUpdateTime = "";
	
	/** Whether the time has changed since the last update. */
	private boolean timeChanged = false;
	
	/** Component mappers to get components from entities. */
    private static ComponentMapper<ScheduleComponent> scheduleMapper = ComponentMapper.getFor(ScheduleComponent.class);

	/**
	 * Create a new instance of the schedule system.
	 * @param time
	 */
	public ScheduleSystem(Time time) {
		super(Family.all(ScheduleComponent.class).get());
		this.time = time;
	}
	
	/**
	 * Updates the system.
	 * @param deltaTime since last update.
	 */
    public void update(float deltaTime) {
    	
    	// Get the current formatted time.
    	String formattedTime = time.getFormattedTime();
    	
    	// Determine whether the  time has changed since this system last updated.
    	timeChanged = !lastUpdateTime.equals(formattedTime);
    	
    	lastUpdateTime = formattedTime;
    	
		// Call through to process each entity.
		super.update(deltaTime);
		
		// Reset the timeChanged flag.
		timeChanged = false;
    }

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// Get the entity schedule component.
		ScheduleComponent scheduleComponent = scheduleMapper.get(entity);
		
		// If the time has changed since the last update, some activities may be scheduled to start.
		if (timeChanged) {
			// TODO Check appointments to schedule activities.
		}
	
		// Process any pending activities.
		if (scheduleComponent.activities.size() > 0) {
			// Get the current activity.
			Activity currentActivity = scheduleComponent.activities.get(0);
			// If the current activity has finished...
			if (currentActivity.hasFinished()) {
				// ... remove it, otherwise ...
				scheduleComponent.activities.remove(currentActivity);
			} else {
				// ... process it.
				currentActivity.process();
			}
		}
	}
}
