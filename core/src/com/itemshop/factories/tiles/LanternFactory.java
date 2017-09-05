package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.area.TileType;
import com.itemshop.area.TileTypeComponent;
import com.itemshop.game.assets.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for creating a lantern.
 */
public class LanternFactory implements TileFactory {

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
		
		// Add the unlit lantern texture.
		entity.add(new TextureComponent(Assets.lantern_lit));
		
		entity.add(new TileTypeComponent(TileType.LANTERN));

		// Add the tile entity to the engine.
		engine.addEntity(entity);
		
		return entity;
	}
}