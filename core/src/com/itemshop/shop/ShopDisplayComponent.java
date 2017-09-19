package com.itemshop.shop;

import com.badlogic.ashley.core.Component;

/**
 * A component defining a shop display.
 */
public class ShopDisplayComponent implements Component {

	/** The display type. */
	public ShopDisplay type;
	
	/**
	 * Create a new instance of ShopDisplayComponent.
	 * @param type
	 */
	public ShopDisplayComponent(ShopDisplay type) {
		this.type = type;
	}
}
