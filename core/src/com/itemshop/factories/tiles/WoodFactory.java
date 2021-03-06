package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.assets.Assets;
import com.itemshop.movement.WalkableTileComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for creating a wood tile.
 */
public class WoodFactory implements TileFactory {

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 * @param random The random number generator to use.
	 * @param x The x position.
	 * @param y The y position.
	 * @param sameAbove Whether the tile above is of the same type.
	 */
	public Entity create(Engine engine, Random random, int x, int y, boolean sameAbove) {
		// Create the tile entity.
		Entity entity = new Entity();

		// Add the entities components.
		entity.add(new PositionComponent(x, y));
		entity.add(new TextureComponent(Assets.wood_horizontal));
		entity.add(new WalkableTileComponent());

		// Add the tile entity to the engine.
		engine.addEntity(entity);
		
		return entity;
	}
}
