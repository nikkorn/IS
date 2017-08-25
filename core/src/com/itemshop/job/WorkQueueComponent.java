package com.itemshop.job;

import java.util.ArrayList;
import com.badlogic.ashley.core.Component;
import com.itemshop.schedule.ActivityPlanner;

/**
 * A component which defines a queue of things to do by entities which have a matching job type. 
 * The ScheduleSystem will be responsible for assigning work to be done to those who can do it.
 */
public class WorkQueueComponent implements Component {
	
	/** The job role in which this work is done. */
	private Job job; 
	
	/** The queue of activity planners which can be applied to the schedule component of an entity. */
	private ArrayList<ActivityPlanner> queue = new ArrayList<ActivityPlanner>();
	
	/**
	 * Creates a new instance of the WorkQueueComponent class.
	 * @param job
	 */
	public WorkQueueComponent(Job job) {
		this.job = job;
	}
	
	/**
	 * Get the job role.
	 */
	public Job getJobRole() {
		return this.job;
	}
	
	/**
	 * Clear the job queue.
	 */
	public void clear() {
		queue.clear();
	}
	
	/**
	 * Return whether there are any jobs to do.
	 * @return has jobs
	 */
	public boolean hasWorkQueued() {
		return !queue.isEmpty();
	}
	
	/**
	 * Add a work planner to the front of the queue.
	 * @param planner
	 */
	public void addWorkItem(ActivityPlanner planner) {
		this.queue.add(planner);
	}
	
	/**
	 * Add a work planner to the front of the queue.
	 * @param planner
	 */
	public ActivityPlanner takeWorkItem() {
		// We can't return a work item if we have none queued.
		if (hasWorkQueued()) {
			return this.queue.remove(this.queue.size() - 1);
		} else {
			// Just return a work item which does nothing.
			return (doer, current) -> {};
		}
	}
}
