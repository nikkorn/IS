package com.itemshop.schedule;

import java.util.ArrayList;

/**
 * Represents a scheduled appointment.
 */
public class Appointment {

	/** The hour this appointment is scheduled at. */
	private int hour;

	/** The minute this appointment is scheduled at. */
	private int minute;

	/** Whether this appointment is repeated. */
	private boolean isRepeated;

	/** The list of activities to undertake for this appointment */
	private ArrayList<Activity> activities;

	/**
	 * Create a new instance of the Appointment class with no activities.
	 * @param hour the hour of the appointment
	 * @param minute the minute of the appointment
	 * @param repeated whether this appointment is repeated or just done one.
	 */
	public Appointment(int hour, int minute, boolean repeated) {
		this(hour, minute, repeated, new ArrayList<Activity>());
	}

	/**
	 * Create a new instance of the Appointment class.
	 * @param hour the hour of the appointment
	 * @param minute the minute of the appointment
	 * @param repeated whether this appointment is repeated or just done one.
	 * @param activities the list of activities to undertake for this appointment.
	 */
	public Appointment(int hour, int minute, boolean repeated, ArrayList<Activity> activities) {
		this.hour       = hour;
		this.minute     = minute;
		this.isRepeated = repeated;
		this.activities = activities;
	}

	/**
	 * Get the hour this appointment is scheduled at.
	 * @returns The hour.
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Get the minute this appointment is scheduled at.
	 * @returns The minute.
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Get whether this appointment is repeated.
	 * @returns is repeated.
	 */
	public boolean isRepeated() {
		return isRepeated;
	}

	/**
	 * Gets list of activities to undertake for this appointment.
	 * @returns activities.
	 */
	public ArrayList<Activity> getActivities() {
		return activities;
	}

	/**
	 * Sets list of activities to undertake for this appointment.
	 * @param activities.
	 */
	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}
}
