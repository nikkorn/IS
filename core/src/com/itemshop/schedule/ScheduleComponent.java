package com.itemshop.schedule;

import java.util.ArrayList;
import com.badlogic.ashley.core.Component;

/**
 * A component that defines a character schedule.
 */
public class ScheduleComponent implements Component {
	
	/** 
	 * The list of activities to undertake.
	 * The first in the list is the currently active one. 
	 */
	public ActivityList activities;
	
	/** 
	 * The list of appointments to be met.
	 */
	public ArrayList<Appointment> appointments;
	
	/**
	 * Create a new instance of the ScheduleComponent class.
	 */
	public ScheduleComponent() {
		activities   = new ActivityList();
		appointments = new ArrayList<Appointment>();
	}
}
