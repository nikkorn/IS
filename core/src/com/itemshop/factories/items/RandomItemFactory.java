package com.itemshop.factories.items;

import java.util.Random;

import com.badlogic.ashley.core.Entity;
import com.itemshop.utilities.lotto.Lotto;

/**
 * Factory for creating a Muffin item.
 */
public class RandomItemFactory implements ItemFactory {
	
	private Lotto<ItemFactory> picker;
	
	public RandomItemFactory() {
		Random random = new Random();
		picker = new Lotto<ItemFactory>(random)
			.add(new AppleFactory(), 8)   
			.add(new BananaFactory(), 6)
			.add(new BookFactory(), 5)
			.add(new BerryFactory(), 4)
			.add(new CheeseFactory(), 2)
			.add(new MuffinFactory(), 1);
	}
	
	/**
	 * Creates the entity.
	 * @returns The entity
	 */
	public Entity create() {
		return picker.draw().create();
	}
}
