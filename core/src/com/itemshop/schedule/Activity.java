package com.itemshop.schedule;

import com.badlogic.ashley.core.Engine;

/**
 * Represents a scheduled activity.
 */
public abstract class Activity {
	
	/** Whether the activity has begun. */
	private boolean hasBegun = false;
	
	/** Whether the activity has finished. */
	private boolean hasFinished = false;
	
	/** The game engine for use within the activity. */
	private Engine engine = null;
	
	/**
	 * Process this activity.
	 * @param engine
	 */
	public void process(Engine engine) {
		// Set the engine for use in this activity if we have not already done so.
		if (this.engine == null) {
			this.engine = engine;
		}
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
	 * Get the game engine.
	 * @return engine.
	 */
	public Engine getEngine() {
		return this.engine;
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
