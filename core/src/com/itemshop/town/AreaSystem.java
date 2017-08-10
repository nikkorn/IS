package com.itemshop.town;

import java.util.ArrayList;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.itemshop.render.PositionComponent;

/**
 * Handles processing of areas an the tiles in those areas.
 */
public class AreaSystem extends EntitySystem {
	
	/** The tile entities. */
	private static ImmutableArray<Entity> tiles;

	/** The component mappers. */
	private static final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
	private static final ComponentMapper<AreaComponent> areaMapper         = ComponentMapper.getFor(AreaComponent.class);

	@Override
	public void addedToEngine(Engine engine) {
		tiles = engine.getEntitiesFor(Family.all(PositionComponent.class, AreaComponent.class).get());
	}
	
	/**
	 * Get the tiles which make up the town.
	 * @return tiles
	 */
	public static ImmutableArray<Entity> getTiles() {
		return tiles;
	}
	
	/**
	 * Get the tiles which make up an area in the town.
	 * @return tiles
	 */
	public static ArrayList<Entity> getAreaTiles(Area area) {
		// Create a new list to store the tiles.
		ArrayList<Entity> areaTiles = new ArrayList<Entity>();
		// Go over every tile in the town ...
		for (Entity tile : tiles) {
			// ... And check whether is resides in the specified area.
			if (areaMapper.get(tile).area == area) {
				areaTiles.add(tile);
			}
		}
		// Return the tiles which belong to this area.
		return areaTiles;
	}
	
	/**
	 * Get the tile at the specified position.
	 * @param x
	 * @param y
	 * @return tile
	 */
	public static Entity getTileAt(int x, int y) {
		// Go over every tile in the town ...
		for (Entity tile : tiles) {
			// Get the tile position component ...
			PositionComponent position = positionMapper.get(tile);
			// ... And check whether is at the specified position.
			if (position.x == ((float) x) && position.y == ((float) y)) {
				return tile;
			}
		}
		// There was no tile at the specified position.
		return null;
	}
	
	@Override
	public void update(float deltaTime) {}
}