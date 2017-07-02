package com.itemshop.factories.tiles;

import java.util.Random;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for creating a Path tile.
 */
public class PathFactory implements TileFactory {

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 * @param random The random number generator to use.
	 * @param x The x position.
	 * @param y The y position.
	 */
	public void create(Engine engine, Random random, int x, int y) {
		// Create the tile entity.
		Entity entity = new Entity();

		// Add the entities components.
		entity.add(new PositionComponent(x, y));
		entity.add(new TextureComponent(Assets.slab));

		// Add the tile entity to the engine.
		engine.addEntity(entity);
	}
}
