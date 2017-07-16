package com.itemshop.factories.items;

import com.badlogic.ashley.core.Entity;
import com.itemshop.game.assets.Assets;
import com.itemshop.item.ItemType;
import com.itemshop.item.ItemTypeComponent;
import com.itemshop.item.QualityComponent;
import com.itemshop.item.ValueComponent;
import com.itemshop.render.RenderSizeComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for creating a Book item.
 */
public class BookFactory implements ItemFactory {

	/**
	 * Creates the entity.
	 * @returns The entity
	 */
	public Entity create() {

		// Create the entity.
		Entity item = new Entity();
		
		// Add the entities components.
		item.add(new ItemTypeComponent(ItemType.BOOK, "Book"));
		// TODO: Add book texture.
		item.add(new TextureComponent(Assets.unknown));
		item.add(new QualityComponent());
		item.add(new ValueComponent(3));	
		item.add(new RenderSizeComponent(0.625f, 0.625f));
		
		return item;
	}
}
