package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.area.TileType;
import com.itemshop.area.TileTypeComponent;
import com.itemshop.container.ContainerComponent;
import com.itemshop.game.assets.Assets;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.shop.ShopDisplay;
import com.itemshop.shop.ShopDisplayComponent;

/**
 * Factory for creating a Bookcase tile.
 */
public class BookcaseFactory implements TileFactory {
	
	/** The maximum number of books that a bookcase can contain. */
	private static int MAXIMUM_CAPACITY = 14;

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
		
		// Add a container component.
		ContainerComponent containerComponent = new ContainerComponent(MAXIMUM_CAPACITY);
		// Add actions to perform when entities are added/removed from this container.
		containerComponent.onEntityAdded = (item) -> {
			// TextureComponent should be switched when books are added.
			entity.add(new TextureComponent(getTexture(containerComponent.getSize())));
		};
		containerComponent.onEntityRemoved = (item) -> {
			// TextureComponent should be switched when books are removed.
			entity.add(new TextureComponent(getTexture(containerComponent.getSize())));
		};
		entity.add(containerComponent);
		
		// This is a shop display so it needs a shop display component.
		entity.add(new ShopDisplayComponent(ShopDisplay.BOOKCASE));
		
		// Add a component defining this as a tile.
		entity.add(new TileTypeComponent(TileType.BOOKCASE));
		
		// Add the right texture.
		entity.add(new TextureComponent(Assets.bookcase_0));

		// Add the tile entity to the engine.
		engine.addEntity(entity);
		
		return entity;
	}
	
	/**
	 * Gets the correct texture for the specified number of books.
	 * @param numberOfBooks The number of books in the bookcase.
	 * @return The texture.
	 */
	private TextureRegion getTexture(int numberOfBooks) {
		switch (numberOfBooks) {
			case 0:
				return Assets.bookcase_0;
			case 1:
				return Assets.bookcase_1;
			case 2:
				return Assets.bookcase_2;
			case 3:
				return Assets.bookcase_3;
			case 4:
				return Assets.bookcase_4;
			case 5:
				return Assets.bookcase_5;
			case 6:
				return Assets.bookcase_6;
			case 7:
				return Assets.bookcase_7;
			case 8:
				return Assets.bookcase_8;
			case 9:
				return Assets.bookcase_9;
			case 10:
				return Assets.bookcase_10;
			case 11:
				return Assets.bookcase_11;
			case 12:
				return Assets.bookcase_12;
			case 13:
				return Assets.bookcase_13;
			case 14:
			default:
				return Assets.bookcase_14;
		}
	}
}
