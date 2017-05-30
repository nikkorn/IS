package com.itemshop.item;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * A component that defines the the owning entity of an entity.
 */
public class OwnerComponent implements Component {
	
	/** The owning entity of this entity. */
	public Entity owner;
	
	/**
	 * Create a new instance of OwnerComponent.
	 * @param owner
	 */
	public OwnerComponent(Entity owner) {
		this.owner = owner;
	}
}
