package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.itemshop.game.assets.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.utilities.lotto.Lotto;
import com.itemshop.container.ContainerComponent;
import com.itemshop.factories.items.RandomItemFactory;

public class DisplayCaseFactory implements TileFactory {
	
	private RandomItemFactory itemGenerator;
	
	public DisplayCaseFactory() {
		itemGenerator = new RandomItemFactory();
	}

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 * @param random The random number generator to use.
	 * @param x The x position.
	 * @param y The y position.
	 * @param sameAbove Whether the tile above is of the same type.
	 */
	public void create(Engine engine, Random random, int x, int y, boolean sameAbove) {
		// Create the tile entity.
		Entity entity = new Entity();

		// Add the entities components.
		entity.add(new PositionComponent(x, y));
		
		// Populate the container component.
		ContainerComponent containerComponent = new ContainerComponent(1);
		if (random.nextBoolean()) {
			try {
				containerComponent.add(itemGenerator.create());
			} catch (Exception exception) {
			}			
		}
		entity.add(containerComponent);
		
		// Add the right texture.
		entity.add(new TextureComponent(
			new Lotto<TextureRegion>(random)
				.add(Assets.display_wood, 1)
				.add(Assets.display_stone, 1)
				.add(Assets.cabinet_wood, 1)
				.add(Assets.cabinet_stone, 1)
				.draw()
		));

		// Add the tile entity to the engine.
		engine.addEntity(entity);
	}
}
