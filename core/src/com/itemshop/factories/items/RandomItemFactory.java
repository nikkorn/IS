package com.itemshop.factories.items;

import java.util.Random;
import com.badlogic.ashley.core.Entity;
import com.itemshop.utilities.lotto.Lotto;

/**
 * Factory for creating random items.
 */
public class RandomItemFactory implements ItemFactory {
	
	/** Picker for selecting the factory type. */
	private Lotto<ItemFactory> picker;
	
	/**
	 * Creates a RandomItemFactory instance.
	 */
	public RandomItemFactory() {
		Random random = new Random();
		picker = new Lotto<ItemFactory>(random)
			.add(new AppleFactory(), 2)   
			.add(new BananaFactory(), 2)
			.add(new BookFactory(), 1)
			.add(new BerryFactory(), 2)
			.add(new CheeseFactory(), 1)
			.add(new MuffinFactory(), 2);
	}
	
	/**
	 * Creates the entity.
	 * @returns The entity
	 */
	public Entity create() {
		return picker.draw().create();
	}
}
