package com.itemshop.factories.items;

import com.badlogic.ashley.core.Entity;
import com.itemshop.game.Assets;
import com.itemshop.item.ItemType;
import com.itemshop.item.ItemTypeComponent;
import com.itemshop.item.PerishableComponent;
import com.itemshop.item.QualityComponent;
import com.itemshop.item.ValueComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for creating an Apple item.
 */
public class AppleFactory implements ItemFactory {

	/**
	 * Creates the entity.
	 * @returns The entity
	 */
	public Entity create() {

		// Create the entity.
		Entity item = new Entity();
		
		// Add the entities components.
		item.add(new ItemTypeComponent(ItemType.APPLE, "Apple"));
		item.add(new TextureComponent(Assets.apple));
		item.add(new QualityComponent());
		item.add(new ValueComponent(2));
		item.add(new PerishableComponent(4));		
		
		return item;
	}
}
