package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.assets.Assets;
import com.itemshop.movement.WalkableTileComponent;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.utilities.lotto.Lotto;

/**
 * Factory for creating a Grass tile.
 */
public class GrassFactory implements TileFactory {

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
		
		// Grass is a little bit harder to walk on than other tiles.
		entity.add(new WalkableTileComponent(3));
		
		// Randomly generate a texture component for this tile.
		entity.add(new TextureComponent(
			new Lotto<TextureRegion>(random)
				.add(Assets.grass, 4)
				.add(Assets.grass_flower, 2)
				.add(Assets.grass_mole, 1)
				.add(Assets.grass_pebble, 4)
				.add(Assets.grass_medium, 8)
				.add(Assets.grass_long, 8)
				.draw()
		));

		// Add the tile entity to the engine.
		engine.addEntity(entity);
		
		return entity;
	}
}
