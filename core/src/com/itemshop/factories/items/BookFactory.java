package com.itemshop.factories.items;

import com.badlogic.ashley.core.Entity;
import com.itemshop.game.assets.Assets;
import com.itemshop.item.ItemType;
import com.itemshop.item.ItemTypeComponent;
import com.itemshop.item.QualityComponent;
import com.itemshop.item.ValueComponent;
import com.itemshop.render.RenderSizeComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.shop.ShopDisplay;

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
		
		ItemTypeComponent itemTypeComponent = new ItemTypeComponent(ItemType.BOOK, "Book");
		itemTypeComponent.setTargetDisplay(ShopDisplay.BOOKCASE);
		
		// Add the entities components.
		item.add(itemTypeComponent);
		item.add(new TextureComponent(Assets.book1));
		item.add(new QualityComponent());
		item.add(new ValueComponent(3));	
		item.add(new RenderSizeComponent(0.625f, 0.625f));
		
		return item;
	}
}
