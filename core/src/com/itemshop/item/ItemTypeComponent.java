package com.itemshop.item;

import com.badlogic.ashley.core.Component;

/**
 * A component that defines an entities item type.
 */
public class ItemTypeComponent implements Component {

	/** The item type. */
	public ItemType type;
	
	/**
	 * Create a new instance of ItemTypeComponent.
	 * @param type
	 */
	public ItemTypeComponent(ItemType type) {
		this.type = type;
	}
}
