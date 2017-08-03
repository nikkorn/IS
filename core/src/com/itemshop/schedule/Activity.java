package com.itemshop.schedule;

/**
 * Represents a scheduled activity.
 */
public abstract class Activity {
	
	/** Whether the activity has begun. */
	private boolean hasBegun = false;
	
	/** Whether the activity has finished. */
	private boolean hasFinished = false;
	
	/**
	 * Process this activity.
	 */
	public void process() {
		// Has this activity begun yet?
		if (!hasBegun) {
			onBegin();
			this.hasBegun = true;
		} else if (!hasFinished) {
			// Perform this activity.
			this.perform();
		}
	}
	
	/**
	 * Reset this activity.
	 */
	public void reset() {
		this.hasBegun    = false;
		this.hasFinished = false;
	}
	
	/**
	 * Finish this activity.
	 */
	public void finish() {
		this.hasFinished = true;
		onEnd();
	}
	
	/**
	 * Return whether this activity has finished.
	 * @return finished
	 */
	public boolean hasFinished() {
		return this.hasFinished;
	}
	
	/**
	 * Return whether this activity has begun.
	 * @return begun
	 */
	public boolean hasBegun() {
		return this.hasBegun;
	}
	
	/**
	 * Called when the activity is begun.
	 */
	public abstract void onBegin();
	
	/**
	 * Called until this activity is finished or preempted.
	 */
	public abstract void perform();
	
	/**
	 * Called when the activity is interrupted.
	 * This will be followed by a call to finish().
	 */
	public abstract void onInterrupt();
	
	/**
	 * Called when the activity is ended.
	 */
	public abstract void onEnd();
}
