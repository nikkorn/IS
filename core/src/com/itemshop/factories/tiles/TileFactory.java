package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;

/**
 * Factory to create a tile.
 */
@FunctionalInterface
public interface TileFactory {

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 * @param random The rng to use.
	 * @param x The x position.
	 * @param y The y position.
	 */
	void create(Engine engine, Random random, int x, int y);
}