package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.assets.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.container.ContainerComponent;

/**
 * Factory for creating a chest tile.
 */
public class ChestFactory implements TileFactory {
	
	/** The maximum number of items a chest can hold.*/
	private static int MAXIMUM_CAPACITY = 15;

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

		// Create the container component.
		ContainerComponent containerComponent = new ContainerComponent(MAXIMUM_CAPACITY);
		
		// Add the container component to the table entity.
		entity.add(containerComponent);
		
		// Add the right texture.
		entity.add(new TextureComponent(Assets.chest));

		// Add the tile entity to the engine.
		engine.addEntity(entity);
		
		return entity;
	}
}
