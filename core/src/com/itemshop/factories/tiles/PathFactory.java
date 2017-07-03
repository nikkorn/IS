package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.itemshop.game.assets.Assets;
import com.itemshop.movement.WalkableTileComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.utilities.lotto.Lotto;

/**
 * Factory for creating a Path tile.
 */
public class PathFactory implements TileFactory {

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 * @param random The random number generator to use.
	 * @param x The x position.
	 * @param y The y position.
	 */
	public void create(Engine engine, Random random, int x, int y, boolean sameAbove) {
		// Create the tile entity.
		Entity entity = new Entity();

		// Add the entities components.
		entity.add(new PositionComponent(x, y));
		entity.add(new TextureComponent(
			new Lotto<TextureRegion>(random)
				.add(Assets.slab, 4)
				.add(Assets.slab_round, 2)
				.add(Assets.slab_quad, 1)
				.draw()
		));

		entity.add(new WalkableTileComponent());

		// Add the tile entity to the engine.
		engine.addEntity(entity);
	}
}
