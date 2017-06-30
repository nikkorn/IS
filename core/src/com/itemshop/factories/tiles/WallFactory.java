package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.assets.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.SizeComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for creating a Wall tile.
 */
public class WallFactory implements TileFactory {

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
		entity.add(new SizeComponent(1, 1));
		entity.add(new PositionComponent(x, y));
		entity.add(new TextureComponent(Assets.stone));

		// Add the tile entity to the engine.
		engine.addEntity(entity);
	}
}
