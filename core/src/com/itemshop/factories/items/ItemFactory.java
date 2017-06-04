package com.itemshop.factories.items;

import com.badlogic.ashley.core.Engine;

/**
 * Factory for creating a item.
 */
@FunctionalInterface
public interface ItemFactory {

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 */
	void create(Engine engine);
}
