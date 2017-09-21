package com.itemshop.item;

import com.badlogic.ashley.core.Component;
import com.itemshop.shop.ShopDisplay;

/**
 * A component that defines an entities item type.
 */
public class ItemTypeComponent implements Component {

	/** The item type. */
	public ItemType type;
	
	/** The item category. */
	public ItemCategory category;
	
	/** The item name. */
	public String name;
	
	/** The shop display this item can be displayed on. */
	private ShopDisplay targetDisplay = null;
	
	/**
	 * Create a new instance of ItemTypeComponent.
	 * @param type
	 * @param name
	 */
	public ItemTypeComponent(ItemType type, String name) {
		this(type, ItemCategory.NONE, name);
	}
	
	/**
	 * Create a new instance of ItemTypeComponent.
	 * @param type
	 * @param category
	 * @param name
	 */
	public ItemTypeComponent(ItemType type, ItemCategory category, String name) {
		this.type     = type;
		this.category = category;
		this.name     = name;
	}

	/**
	 * Get the shop display type that this item can be displayed on.
	 * @return display
	 */
	public ShopDisplay getTargetDisplay() {
		return targetDisplay;
	}

	/**
	 * Set the shop display type that this item can be displayed on.
	 * @param targetDisplay
	 */
	public void setTargetDisplay(ShopDisplay targetDisplay) {
		this.targetDisplay = targetDisplay;
	}
}
