package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	}
}
