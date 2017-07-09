package com.itemshop.factories.items;

import java.util.HashMap;
import java.util.Map;
import com.badlogic.ashley.core.Entity;
import com.itemshop.item.ItemType;

public class ItemFactoryMap {
	
	@SuppressWarnings("serial")
	static Map<ItemType, ItemFactory> factoryMap = new HashMap<ItemType, ItemFactory>() {{
		put(ItemType.APPLE, new AppleFactory());
		put(ItemType.BANANA, new BananaFactory());
		put(ItemType.BERRY, new BerryFactory());
		put(ItemType.CHEESE, new CheeseFactory());
		put(ItemType.MUFFIN, new MuffinFactory());
	}};
	
	/**
	 * Create an item entity based on the specified item type.
	 * @param type
	 * @returns The created entity
	 */
	public static Entity create(ItemType type) {
		return factoryMap.get(type).create();
	}
}
