package com.itemshop.character.walking.astar;

import com.badlogic.ashley.core.Entity;

/**
 * Functional interface for creating A* nodes from entities.
 */
public interface AStarNodeCreator {

	/**
	 * Create an A* node from an entity.
	 * @param entity
	 * @return node
	 */
	public AStarNode create(Entity entity);
}
