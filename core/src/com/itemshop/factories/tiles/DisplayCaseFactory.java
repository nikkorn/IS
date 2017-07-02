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
public class DisplayCaseFactory implements TileFactory {

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
			.add(Assets.display_wood, 1)
			.add(Assets.display_stone, 1)
			.add(Assets.cabinet_wood, 1)
			.add(Assets.cabinet_stone, 1)
			.draw()
		));
		entity.add(new NonWalkableTileComponent());

		// Add the tile entity to the engine.
		engine.addEntity(entity);
	}
}
