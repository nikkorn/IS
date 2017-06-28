package com.itemshop.factories.items;

import com.badlogic.ashley.core.Entity;
import com.itemshop.game.assets.Assets;
import com.itemshop.item.ContainerComponent;
import com.itemshop.item.ItemType;
import com.itemshop.item.ItemTypeComponent;
import com.itemshop.item.ValueComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for creating a Table item.
 */
public class TableFactory implements ItemFactory {

	/**
	 * Creates the entity.
	 * @returns The entity
	 */
	public Entity create() {

		// Create the entity.
		Entity item = new Entity();
		
		// Add the entities components.
		item.add(new ItemTypeComponent(ItemType.TABLE, "Table"));
		item.add(new TextureComponent(Assets.table_texture));
		item.add(new ValueComponent(100));
		item.add(new ContainerComponent(1));
		
		return item;
	}
}
