package com.itemshop.schedule;

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
	
	/** The planner with which we modify the activity list of a schedule. */
	private ActivityPlanner planner;

	/**
	 * Create a new instance of the Appointment class with no activities.
	 * @param hour the hour of the appointment
	 * @param minute the minute of the appointment
	 * @param repeated whether this appointment is repeated or just done one.
	 */
	public Appointment(int hour, int minute, boolean repeated) {
		this(hour, minute, repeated, (current) -> {});
	}

	/**
	 * Create a new instance of the Appointment class.
	 * @param hour the hour of the appointment
	 * @param minute the minute of the appointment
	 * @param repeated whether this appointment is repeated or just done one.
	 * @param planner the planner with which we modify the activity list of a schedule.
	 */
	public Appointment(int hour, int minute, boolean repeated, ActivityPlanner planner) {
		this.hour       = hour;
		this.minute     = minute;
		this.isRepeated = repeated;
		this.planner    = planner;
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
	 * Get the activity planner for this appointment.
	 * @returns planner.
	 */
	public ActivityPlanner getPlanner() {
		return planner;
	}
}
