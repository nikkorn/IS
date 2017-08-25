package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

/**
 * Factory to create a tile.
 */
@FunctionalInterface
public interface TileFactory {

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 * @param random The random number generator to use.
	 * @param x The x position.
	 * @param y The y position.
	 * @param sameAbove Whether the tile above is of the same type.
	 * @returns the tile entity.
	 */
	Entity create(Engine engine, Random random, int x, int y, boolean sameAbove);
}
