package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.assets.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.area.TileType;
import com.itemshop.area.TileTypeComponent;
import com.itemshop.container.ContainerComponent;

/**
 * Factory for creating a Bed tile.
 */
public class BedFactory implements TileFactory {
	
	/** The maximum number of entities a bed can hold.*/
	private static int MAXIMUM_CAPACITY = 1;
	
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
		
		// Add actions to perform when entities are added to this container.
		containerComponent.onEntityAdded = (occupant) -> {
			// TODO Hide occupant!
			entity.add(new TextureComponent(Assets.bed_full));
		};
		containerComponent.onEntityRemoved = (occupant) -> {
			// TODO Show occupant!
			entity.add(new TextureComponent(Assets.bed_empty));
		};
		
		// Add the container component to the bin entity.
		entity.add(containerComponent);
		
		// Add the default empty bed texture.
		entity.add(new TextureComponent(Assets.bed_empty));
		
		entity.add(new TileTypeComponent(TileType.BED));

		// Add the tile entity to the engine.
		engine.addEntity(entity);
		
		return entity;
	}
}
