package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.itemshop.container.ContainerComponent;
import com.itemshop.render.PositionComponent;

/**
 * An activity which involves walking to a container an placing the specified item into it.
 */
public class AddItemToContainerActivity extends WalkActivity {
	
	/** The required component mappers. */
    private static ComponentMapper<PositionComponent> positionMapper   = ComponentMapper.getFor(PositionComponent.class);
    private static ComponentMapper<ContainerComponent> containerMapper = ComponentMapper.getFor(ContainerComponent.class);
    
    /** The character entity doing the transfer. */
    private Entity character;
    
    /** The target container. */
    private Entity container;
    
    /** The item to transfer to the container. */
    private Entity item;

    /**
     * Create a new instance of the AddItemToContainerActivity class.
     * @param character
     * @param container
     * @param item
     */
	public AddItemToContainerActivity(Entity character, Entity container, Entity item) {
		// Call through to the walk activity to set our target location to match that of the target container.
		super(character, (int) positionMapper.get(container).x, (int) positionMapper.get(container).y);
		this.character = character;
		this.container = container;
		this.item      = item;
	}
	
	/**
	 * Add the item to the container.
	 */
	public void addItemToContainer() {
		System.out.println("Adding Item!");
	}

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
				// We have reached our target, place the item ... 
				addItemToContainer();
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
