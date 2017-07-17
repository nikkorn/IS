package com.itemshop.schedule;

import com.badlogic.ashley.systems.IntervalSystem;

/**
 * Handles processing of entities which can walk.
 */
public class TimeSystem extends IntervalSystem {

	/** The duration of one in-game minute in seconds. */
	private static final float minuteDuration = 2;

	/** The time. */
	private Time time;

	/**
	 * Constructs the TimeSystem system instance.
	 */
	public TimeSystem() {
		super(minuteDuration);
		time = new Time();
	}

	/**
	 * Get the time.
	 * 
	 * @return time
	 */
	public Time getTime() {
		return time;
	}

	@Override
	protected void updateInterval() {
		time.tick();
		System.out.println("time: " + time.getFormattedTime());
	}
}
