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

		// Determine the amount of items on display.
		int numberOfItems = random.nextInt(MAXIMUM_CAPACITY + 1);

		// Create the container component.
		ContainerComponent containerComponent = new ContainerComponent(MAXIMUM_CAPACITY);
		
		// Add actions to perform when entities are added/removed from this container.
		containerComponent.onEntityAdded = (item) -> {
			// The item will have the same position as this table.
			item.add(new PositionComponent(x, y, 2));
			// This item is to be displayed on the table. It needs an offset based on its position in the container.
			switch (containerComponent.getSize()) {
				case 1:
					// The item will be displayed in the top-left of the table.
					item.add(new RenderOffsetComponent(0f, 0.75f));
					break;
				case 2:
					// The item will be displayed in the bottom-right of the table.
					item.add(new RenderOffsetComponent(0.375f, 0.3125f));
					break;
			}
		};
		containerComponent.onEntityRemoved = (item) -> {
			// The item will no longer have the same position as this table.
			item.remove(PositionComponent.class);
			// The item will no need a render offset as it is no longer being displayed here.
			item.remove(RenderOffsetComponent.class);
		};
		
		for (int i = 0; i < numberOfItems; i++) {
			try {
				// Create a random item.
				Entity item = itemGenerator.create();
				// As we are randomly populating this table, we need to add these entities to the engine manually for now.
				engine.addEntity(item);
				// Add this item to the table container.
				containerComponent.add(item);
			} catch (Exception exception) {
			}
		}
		
		// Add the container component to the table entity.
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
