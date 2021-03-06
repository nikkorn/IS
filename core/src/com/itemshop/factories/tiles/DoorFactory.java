package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.assets.Assets;
import com.itemshop.movement.WalkableTileComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for creating a Door tile.
 */
public class DoorFactory implements TileFactory {

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
		entity.add(new TextureComponent(Assets.door_stone));
		
		// Doors is a little bit harder to walk on than other tiles.
		// Also, we should swap out our door texture as a character passes 
		// through it to make it look like the door is opening and closing.
		WalkableTileComponent walkableTileComponent = new WalkableTileComponent(2);
		walkableTileComponent.onEntry = () -> {
			entity.add(new TextureComponent(Assets.slab));
		};
		walkableTileComponent.onExit = () -> {
			entity.add(new TextureComponent(Assets.door_stone));
		};
		entity.add(walkableTileComponent);

		// Add the tile entity to the engine.
		engine.addEntity(entity);
		
		return entity;
	}
}
