package com.itemshop.schedule.activities;

import com.itemshop.schedule.Activity;

/**
 * A simple activity which waits a specified amount of time before finishing.
 */
public class WaitActivity extends Activity {

	/** The time the activity began. */
	private long activityBeginTime;

	/** The duration to wait. */
	private long waitDuration;

	/**
	 * Create a new instance of the WaitActivity class.
	 * @param duration the duration to wait
	 */
	public WaitActivity(long duration) {
		this.waitDuration = duration;
	}

	@Override
	public void onBegin() {
		// Keep track of the time this activity began. TODO: Eventually take delta time into account.
		this.activityBeginTime = System.currentTimeMillis();
	}

	@Override
	public void perform() {
		// This activity will finish when we have waited long enough.
		if ((System.currentTimeMillis() - activityBeginTime) >= waitDuration) {
			this.finish();
		}
	}

	@Override
	public void onEnd() {}

	@Override
	public void onInterrupt() {}
}
