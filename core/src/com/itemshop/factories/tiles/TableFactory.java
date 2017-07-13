package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.itemshop.game.assets.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.RenderOffsetComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.utilities.lotto.Lotto;
import com.itemshop.container.ContainerComponent;
import com.itemshop.factories.items.RandomItemFactory;

/**
 * Factory for creating a Table tile.
 */
public class TableFactory implements TileFactory {
	
	/** The maximum number of items a table can hold.*/
	private static int MAXIMUM_CAPACITY = 2;

	/** Generator of random items in the table. */
	private RandomItemFactory itemGenerator;
	
	/**
	 * Creates a TableFactory instance.
	 */
	public TableFactory() {
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

		// Determine the amount of books on display.
		int numberOfItems = random.nextInt(MAXIMUM_CAPACITY + 1);

		// Populate the container component.
		ContainerComponent containerComponent = new ContainerComponent(MAXIMUM_CAPACITY);
		for (int i = 0; i < numberOfItems; i++) {
			try {
				// Create a random item.
				Entity item = itemGenerator.create();
				// The item will have the same position as this table.
				item.add(new PositionComponent(x, y, 2));
				// The item will have a slight offset as to sit in the middle of the table.
				item.add(new RenderOffsetComponent(0f, 0.5f));
				// As we are randomly populating this table, we need to add these
				// entities to the engine manually for now.
				engine.addEntity(item);
				// Add this item to the table container.
				containerComponent.add(item);
			} catch (Exception exception) {
			}
		}
		entity.add(containerComponent);
		
		// Add the right texture.
		entity.add(new TextureComponent(
			new Lotto<TextureRegion>(random)
				.add(Assets.table_wood, 1)
				.add(Assets.table_stone, 1)
				.draw()
		));
		
		// TODO: Render container items above table.

		// Add the tile entity to the engine.
		engine.addEntity(entity);
	}
}
