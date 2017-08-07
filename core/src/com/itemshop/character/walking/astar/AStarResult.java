package com.itemshop.character.walking.astar;

import java.util.Stack;
import com.itemshop.movement.Direction;

/**
 * The result of a pathfinding calculation.
 */
public class AStarResult {
	
	/** Whether the path is blocked */
	public boolean isPathBlocked = true;
	
	/** The movements which the path are composed of. */
	public Stack<Direction> movements = new Stack<Direction>();
}
