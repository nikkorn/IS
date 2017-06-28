package com.itemshop.factories.items;

import com.badlogic.ashley.core.Entity;
import com.itemshop.game.assets.Assets;
import com.itemshop.item.ItemType;
import com.itemshop.item.ItemTypeComponent;
import com.itemshop.item.PerishableComponent;
import com.itemshop.item.QualityComponent;
import com.itemshop.item.ValueComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for creating a Muffin item.
 */
public class MuffinFactory implements ItemFactory {

	/**
	 * Creates the entity.
	 * @returns The entity
	 */
	public Entity create() {

		// Create the entity.
		Entity item = new Entity();
		
		// Add the entities components.
		item.add(new ItemTypeComponent(ItemType.MUFFIN, "Muffin"));
		item.add(new TextureComponent(Assets.muffin_texture));
		item.add(new QualityComponent());
		item.add(new ValueComponent(15));
		item.add(new PerishableComponent(2));
		
		return item;
	}
}
