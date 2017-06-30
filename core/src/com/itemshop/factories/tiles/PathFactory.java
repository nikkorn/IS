package com.itemshop.factories.tiles;

import java.util.Random;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.assets.Assets;
import com.itemshop.input.MouseComponent;
import com.itemshop.movement.WalkableTileComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.SizeComponent;
import com.itemshop.render.TextureComponent;

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
	public void create(Engine engine, Random random, int x, int y) {
		// Create the tile entity.
		Entity entity = new Entity();

		// Add the entities components.
		entity.add(new SizeComponent(1, 1));
		entity.add(new PositionComponent(x, y));

		entity.add(new WalkableTileComponent());
		entity.add(new TextureComponent(Assets.slab));
		
		MouseComponent mouseComponent = new MouseComponent();
		mouseComponent.onBeginHover = (hoveredEntity) -> {
			System.out.println("Hovering on path @ " + x + ", " + y);
		};
		mouseComponent.onBeginClick = (hoveredEntity) -> {
			System.out.println("Clicking on path @ " + x + ", " + y);
		};
		entity.add(mouseComponent);

		// Add the tile entity to the engine.
		engine.addEntity(entity);
	}
}
