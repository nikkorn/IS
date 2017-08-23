package com.itemshop.schedule;

import com.badlogic.ashley.core.Entity;

/**
 * A functional interface which allows the list of scheduled activities to be modified.
 */
public interface ActivityPlanner {
	
	/**
	 * Update the list of current activities.
	 * @param doer The doer of the activity.
	 * @param current THe list of pending activities for the doer.
	 */
	public void update(Entity doer, ActivityList current);
}
