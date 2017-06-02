package com.itemshop.item;

import com.badlogic.ashley.core.Component;

/**
 * A component that defines an entities value.
 */
public class ValueComponent implements Component {

	/** The value. */
	private int value;
	
	/**
	 * Create a new instance of ValueComponent.
	 * @param value
	 */
	public ValueComponent(int value) {
		this.setValue(value);
	}

	/**
	 * Get the value.
	 * @returns value
	 */
	public int getValue() { return value; }
	
	/**
	 * Set the value.
	 * @param value
	 */
	public void setValue(int value) {
		
		// The value cannot be less than 0.
		if (value < 0) {
			this.value = 0;
		} else {
			this.value = value;
		}
	}
}
