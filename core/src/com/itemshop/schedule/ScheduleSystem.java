package com.itemshop.schedule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.itemshop.job.Job;
import com.itemshop.job.JobComponent;
import com.itemshop.job.WorkQueueComponent;
import com.itemshop.time.Time;

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
	
	/** The entities which have a work queue component. */
	private static ImmutableArray<Entity> workQueueEntities;

	/** Component mappers to get components from entities. */
	private static ComponentMapper<ScheduleComponent> scheduleMapper   = ComponentMapper.getFor(ScheduleComponent.class);
	private static ComponentMapper<JobComponent> jobMapper             = ComponentMapper.getFor(JobComponent.class);
	private static ComponentMapper<WorkQueueComponent> workQueueMapper = ComponentMapper.getFor(WorkQueueComponent.class);

	/**
	 * Create a new instance of the schedule system.
	 * @param time
	 */
	public ScheduleSystem(Time time) {
		super(Family.all(ScheduleComponent.class).get());
		this.time = time;
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		
		// We need to keep track of the entities which have work to dish out to entities.
		workQueueEntities = engine.getEntitiesFor(Family.all(WorkQueueComponent.class).get());
	}

	/**
	 * Updates the system.
	 * @param deltaTime since last update.
	 */
	public void update(float deltaTime) {
		
		// Get the current formatted time.
		String formattedTime = time.getFormattedTime();

		// Determine whether the time has changed since this system last updated.
		timeChanged = !lastUpdateTime.equals(formattedTime);
		
		if (timeChanged) {
			// Print the current time to the console.
			System.out.println("Time: " + time.getFormattedTime());
		}

		lastUpdateTime = formattedTime;

		// Call through to process each entity.
		super.update(deltaTime);
		
		// Schedule any work activities for entities with jobs.
		scheduleJobActivities();

		// Reset the timeChanged flag.
		timeChanged = false;
	}
	
	/**
	 * Schedule any work activities for entities with jobs.
	 */
	private void scheduleJobActivities() {
		
		// Iterate over all work queue components and check for an work that needs to be done.
		for (Entity workQueueEntity : workQueueEntities) {
			
			// Get the work queue component for this entity.
			WorkQueueComponent workQueueComponent = workQueueMapper.get(workQueueEntity);
			
			// We only care about work queues that have work to be done. 
			if (workQueueComponent.hasWorkQueued()) {
				
				// Get the worker entities that can do the job.
				ArrayList<Entity> workers = getEntitesWithJob(workQueueComponent.getJobRole());
				
				// Assign work to those who can do it until the work queue is empty.
				while (workQueueComponent.hasWorkQueued()) {
					
					// Pick a random job worker to assign the work to.
					Entity worker = workers.get(new Random().nextInt(workers.size() - 1));
					
					// Assign the work!
					workQueueComponent.takeWorkItem().update(worker, scheduleMapper.get(worker).activities);
				}
			}
		}
	}
	
	/**
	 * Get entities with the specified job.
	 * @param job
	 */
	private ArrayList<Entity> getEntitesWithJob(Job job) {

		ArrayList<Entity> results = new ArrayList<Entity>();

		// Check all entities with a schedule component for a job component and ...
		for (Entity entity : this.getEntities()) {
			
			// ... If the entity has a job which matches the specified one ...
			if (jobMapper.has(entity) && jobMapper.get(entity).job == job) {
				
				// ... Add it to the result list.
				results.add(entity);
			}
		}
		
		// Return the results.
		return results;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		// Get the entity schedule component.
		ScheduleComponent scheduleComponent = scheduleMapper.get(entity);

		// If the time has changed since the last update, some activities may be scheduled to start.
		if (timeChanged) {

			// Check for pending appointments.
			for (Iterator<Appointment> appointmentIterator = scheduleComponent.appointments.iterator(); appointmentIterator.hasNext();) {
				
				// Get the current appointment.
				Appointment appointment = appointmentIterator.next();

				// Check to see whether this appointment is scheduled for now and ...
				if (appointment.getHour() == time.getHour() && appointment.getMinute() == time.getMinute()) {
					
					// ... add the appointment activities to the schedule appointments.
					appointment.getPlanner().update(entity, scheduleComponent.activities);

					// If this appointment is to not be repeated, then remove it from the schedule.
					if (!appointment.isRepeated()) {
						appointmentIterator.remove();
					}
				}
			}
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
				// ... process it, passing the game engine for the activity to use.
				currentActivity.process(this.getEngine());
			}
		}
	}
}
