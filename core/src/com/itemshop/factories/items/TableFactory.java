package com.itemshop.factories.items;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.Assets;
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
	 * @param engine The engine to add the entity to.
	 */
	public void create(Engine engine) {

		// Create the entity.
		Entity item = new Entity();
		
		// Add the entities components.
		item.add(new ItemTypeComponent(ItemType.TABLE, "Table"));
		item.add(new TextureComponent(Assets.table_texture));
		item.add(new ValueComponent(100));
		item.add(new ContainerComponent(1));
		
		// Put the entity into the engine. 
		engine.addEntity(item);
	}
}
