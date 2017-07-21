package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.Entity;
import com.itemshop.character.walking.PathComponent;
import com.itemshop.schedule.Activity;

/**
 * A simple walking activity.
 */
public class WalkActivity extends Activity {

	/** The path to follow for this activity. */
	private PathComponent path;
	
	/** The target x position for the path component. */
	private int targetPositionX;
	
	/** The target y position for the path component. */
	private int targetPositionY;
	
	/** The path to follow for this activity. */
	private Entity entity;
	
	/**
	 * Create a new instance of the WalkActivity class.
	 * @param entity
	 * @param targetPositionX
	 * @param targetPositionY
	 */
	public WalkActivity(Entity entity, int targetPositionX, int targetPositionY) {
		this.entity          = entity;
		this.targetPositionX = targetPositionX;
		this.targetPositionY = targetPositionY;
	}
	
	@Override
	public void onBegin() {
		// We will need a new path component for every walk action.
		path = new PathComponent(targetPositionX, targetPositionY);
		// Add the path component to the entity.
		entity.add(path);
	}
	
	@Override
	public void perform() {
		// This activity will finish when the path has been computed and there are no more movements to make.
		if (path.isPathComputed && path.movements.size() == 0) {
			this.finish();
		}
	}
	
	@Override
	public void onEnd() {} 
	
	@Override
	public void onPreempt() {}
}
