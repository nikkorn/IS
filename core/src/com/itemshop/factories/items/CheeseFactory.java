package com.itemshop.factories.items;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.Assets;
import com.itemshop.item.ItemType;
import com.itemshop.item.ItemTypeComponent;
import com.itemshop.item.QualityComponent;
import com.itemshop.item.ValueComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for creating a Cheese item.
 */
public class CheeseFactory implements ItemFactory {

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 */
	public void create(Engine engine) {

		// Create the entity.
		Entity item = new Entity();
		
		// Add the entities components.
		item.add(new ItemTypeComponent(ItemType.CHEESE, "Cheese"));
		item.add(new TextureComponent(Assets.cheese_texture));
		item.add(new QualityComponent());
		item.add(new ValueComponent(5));	
		
		// Put the entity into the engine. 
		engine.addEntity(item);
	}
}
