package com.itemshop.shop;

import com.badlogic.ashley.core.Component;
import com.itemshop.item.ItemCategory;
import com.itemshop.item.ItemType;

/**
 * A component which represents an intent to purchase a specific/type of item.
 */
public class BuyIntentComponent implements Component {
	
	/** 
	 * The type of the desired item.
	 * If this is not defined, then any item with the defined category will do.
	 */
	public ItemType itemType;
	
	/** 
	 * The category of the desired item.
	 * If NONE, then any item will do.
	 */
	public ItemCategory itemCategory;
	
	/** The highest price the the buyer is willing to pay. */ 
	public int highestPrice;
	
	/**
	 * Creates a new instance of the SellIntentComponent class.
	 * @param item
	 * @param askingPrice
	 * @param lowestPrice
	 */
	public BuyIntentComponent(ItemType itemType, ItemCategory itemCategory, int highestPrice) {
		this.itemType     = itemType;
		this.itemCategory = itemCategory;
		this.highestPrice = highestPrice;
	}	
}
