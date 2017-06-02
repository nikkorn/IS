package com.itemshop.item;

import com.badlogic.ashley.core.Component;

/**
 * A component that defines an entities item type.
 */
public class ItemTypeComponent implements Component {

	/** The item type. */
	public ItemType type;
	
	/** The item name. */
	public String name;
	
	/**
	 * Create a new instance of ItemTypeComponent.
	 * @param type
	 * @param name
	 */
	public ItemTypeComponent(ItemType type, String name) {
		this.type = type;
		this.name = name;
	}
}
