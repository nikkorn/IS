package com.itemshop.schedule;

/**
 * A functional interface which allows the list of scheduled activities to be modified.
 */
public interface ActivityPlanner {
	
	/**
	 * Update the list of current activities.
	 * @param current
	 */
	public void update(ActivityList current);
}
