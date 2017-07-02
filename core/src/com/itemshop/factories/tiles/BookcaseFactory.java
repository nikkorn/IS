package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.itemshop.game.Assets;
import com.itemshop.movement.NonWalkableTileComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.utilities.lotto.Lotto;

/**
 * Factory for creating a Bookcase tile.
 */
public class BookcaseFactory implements TileFactory {

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
			.add(Assets.bookcase_0, 1)
			.add(Assets.bookcase_1, 1)
			.add(Assets.bookcase_2, 1)
			.add(Assets.bookcase_3, 1)
			.add(Assets.bookcase_4, 1)
			.add(Assets.bookcase_5, 1)
			.add(Assets.bookcase_6, 1)
			.add(Assets.bookcase_7, 1)
			.add(Assets.bookcase_8, 1)
			.add(Assets.bookcase_9, 1)
			.add(Assets.bookcase_10, 1)
			.add(Assets.bookcase_11, 1)
			.add(Assets.bookcase_12, 1)
			.add(Assets.bookcase_13, 1)
			.add(Assets.bookcase_14, 1)
			.draw()
		));
		entity.add(new NonWalkableTileComponent());

		// Add the tile entity to the engine.
		engine.addEntity(entity);
	}
}
