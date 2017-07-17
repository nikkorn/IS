package com.itemshop.schedule;

import java.util.ArrayList;
import com.badlogic.ashley.core.Component;

public class ScheduleComponent implements Component {
	
	/** 
	 * The list of activities to undertake.
	 * The first in the list is the currently active one. 
	 */
	public ArrayList<Activity> activities;
	
	/**
	 * Create a new instance of the ScheduleComponent class.
	 */
	public ScheduleComponent() {
		activities = new ArrayList<Activity>();
	}
}
