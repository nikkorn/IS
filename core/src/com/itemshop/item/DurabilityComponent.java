package com.itemshop.item;

import com.badlogic.ashley.core.Component;

/**
 * A component that defines the durability of an entity.
 */
public class DurabilityComponent implements Component {

	/** The max durability. */
	private static int MAX_DURABILITY = 100;

	/** The durability. */
	private int durability;
	
	/**
	 * Create a new instance of DurabilityComponent.
	 * @param durability
	 */
	public DurabilityComponent(int durability) {
		this.setDurability(durability);
	}
	
	/**
	 * Get the durability.
	 * @returns durability
	 */
	public int getDurability() { return durability; }
	
	/**
	 * Set the durability.
	 * @param durability
	 */
	public void setDurability(int durability) {
		
		// The durability must be a value between 0 and the max durability.
		if (durability < 0) {
			this.durability = 0;
		} else if (durability > MAX_DURABILITY) {
			this.durability = MAX_DURABILITY;
		} else {
			this.durability = durability;
		}
	}
}
