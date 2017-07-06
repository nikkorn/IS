package com.itemshop.character.walking.astar;

/**
 * Represents a node in an A* pathfinding algorithm.
 */
public class AStarNode {

	/** The parent of this node. */
	public AStarNode parent = null;

	/** The x position of this node. */
	public int x;

	/** The y position of this node. */
	public int y;

	/** The estimated movement cost from the current square to the destination point. */
	public int h;

	/** The amount to increment the movement cost by. Default is 1. */
	public int movementCost = 1;

	/**
	 * The movement cost from the start node to this node. This will normally just
	 * be incremented by 1 per node, but this can be made higher in order to
	 * decrease the appeal of following a path with this node.
	 */
	public int g = 0;

	/**
	 * Create a new instance of the AStarNode class.
	 * @param x
	 * @param y
	 */
	public AStarNode(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the score for this node.
	 * @return score.
	 */
	public int f() { return g + h; }
}
