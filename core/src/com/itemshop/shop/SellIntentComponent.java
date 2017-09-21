package com.itemshop.shop;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * A component which represents an intent to sell a specific item.
 */
public class SellIntentComponent implements Component {
	
	/** The item to be sold. */
	public Entity item;
	
	/** The asking price, will usually differ from the actual item value. */
	public int askingPrice;
	
	/** The lowest price the item will be sold at. */ 
	public int lowestPrice;
	
	/**
	 * Creates a new instance of the SellIntentComponent class.
	 * @param item
	 * @param askingPrice
	 * @param lowestPrice
	 */
	public SellIntentComponent(Entity item, int askingPrice, int lowestPrice) {
		this.item        = item;
		this.askingPrice = askingPrice;
		this.lowestPrice = lowestPrice;
	}	
}
