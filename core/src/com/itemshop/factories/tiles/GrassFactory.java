package com.itemshop.factories.tiles;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.SizeComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for creating a Grass tile.
 */
public class GrassFactory implements TileFactory {

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 * @param x The x position.
	 * @param y The y position.
	 */
	public void create(Engine engine, int x, int y) {
		// Create the tile entity.
		Entity entity = new Entity();

		// Add the entities components.
		entity.add(new SizeComponent(1, 1));
		entity.add(new PositionComponent(x, y));
		entity.add(new TextureComponent(Assets.grass_tile));

		// Add the tile entity to the engine.
		engine.addEntity(entity);
	}
}
