package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.itemshop.render.PositionComponent;

/**
 * An activity which involves walking to and interacting with a tile.
 */
public abstract class TileInteractionActivity extends WalkActivity {
	
	/** The required component mappers. */
    private static ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);

    /**
     * Create a new instance of the TileInteractionActivity class.
     * @param character
     * @param tile
     */
	public TileInteractionActivity(Entity character, Entity tile) {
		// Call through to the walk activity to set our target location to match that of the target tile.
		super(character, (int) positionMapper.get(tile).x, (int) positionMapper.get(tile).y);
	}
	
	/**
	 * Called when the tile to interact with has been reached.
	 */
	public abstract void interact();

	@Override
	public void onBegin() {
		// Begin the walking activity.
		super.onBegin();
	}

	@Override
	public void perform() {
		// We need to wait for a path to the target.
		if (this.getPath().isPathComputed) {
			// If the path is blocked then we cannot reach our container and ...
			if (this.getPath().isPathBlocked) {
				// ... we cannot do anything.
				this.finish();
			} else if (this.getPath().isComplete) {
				// We have reached our target, interact with it ... 
				interact();
				// ... and then we are done.
				this.finish();
			}
		}
	}

	@Override
	public void onInterrupt() {}

	@Override
	public void onEnd() {}
}
