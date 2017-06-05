package com.itemshop.factories.items;

import com.badlogic.ashley.core.Entity;

/**
 * Factory for creating a item.
 */
@FunctionalInterface
public interface ItemFactory {

	/**
	 * Creates the entity.
	 * @return the entity
	 */
	Entity create();
}
