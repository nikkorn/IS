package com.itemshop.factories.tiles;

import com.badlogic.ashley.core.Engine;

/**
 * Factory to create a tile.
 */
@FunctionalInterface
public interface TileFactory {

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 * @param x The x position.
	 * @param y The y position.
	 */
	void create(Engine engine, int x, int y);
}
