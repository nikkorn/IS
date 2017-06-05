package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.itemshop.game.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.SizeComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.utilities.lotto.Lotto;

/**
 * Factory for creating a Grass tile.
 */
public class GrassFactory implements TileFactory {

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 * @param random The rng to use.
	 * @param x The x position.
	 * @param y The y position.
	 */
	public void create(Engine engine, Random random, int x, int y) {
		// Create the tile entity.
		Entity entity = new Entity();

		// Add the entities components.
		entity.add(new SizeComponent(1, 1));
		entity.add(new PositionComponent(x, y));
		entity.add(new TextureComponent(
				new Lotto<Texture>(random).add(Assets.grass_tile_plain, 4)
				.add(Assets.grass_tile, 1)
				.add(Assets.grass_tile_rough, 2).draw()
			));

		// Add the tile entity to the engine.
		engine.addEntity(entity);
	}
}
