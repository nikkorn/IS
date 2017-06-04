package com.itemshop.factories.items;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.Assets;
import com.itemshop.item.ItemType;
import com.itemshop.item.ItemTypeComponent;
import com.itemshop.item.PerishableComponent;
import com.itemshop.item.QualityComponent;
import com.itemshop.item.ValueComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for creating a Berry item.
 */
public class BerryFactory {

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 */
	public static void create(Engine engine) {

		// Create the entity.
		Entity item = new Entity();
		
		// Add the entities components.
		item.add(new ItemTypeComponent(ItemType.BERRY, "Berry"));
		item.add(new TextureComponent(Assets.berry_texture));
		item.add(new QualityComponent());
		item.add(new ValueComponent(3));
		item.add(new PerishableComponent(5));		
		
		// Put the entity into the engine. 
		engine.addEntity(item);
	}
}