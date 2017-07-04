package com.itemshop.character.walking.astar;

import java.util.ArrayList;
import java.util.Stack;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.itemshop.movement.Direction;

/**
 * An implementation of the A* pathfinding algorithm for finding shortest paths over walkable tiles.
 */
public class AStarPathfinder {
	
	/** The collection of all nodes. */
	private ArrayList<AStarNode> nodes = new ArrayList<AStarNode>();
	
	/** The collection of all open nodes. */
	private ArrayList<AStarNode> openNodes = new ArrayList<AStarNode>();
	
	/** The collection of all closed nodes. */
	private ArrayList<AStarNode> closedNodes = new ArrayList<AStarNode>();
	
	/**
	 * Create an instance of the AStarPathfinder class.
	 */
	public AStarPathfinder(ImmutableArray<Entity> entites, AStarNodeCreator creator) {
		// Create the nodes list from the entities.
		for (Entity entity : entites) {
			nodes.add(creator.create(entity));
		}
	}

	/**
	 * Takes an origin position and a destination position and produces a stack
	 * of movement directions needed to reach the destination.
	 * @param originPosX
	 * @param originPosY
	 * @param destinationPosX
	 * @param destinationPosY
	 * @return The stack of directional movements to follow to reach the destination.
	 */
	public Stack<Direction> getPath(int originPosX, int originPosY, int destinationPosX, int destinationPosY) {
		
		// Set the heuristic (h value) of the nodes based on the destination position. 
		for (AStarNode node : nodes) {
			node.h = Math.abs(destinationPosX-node.x) + Math.abs(destinationPosY-node.y);
		}
		
		AStarNode origin = getNodeAtPosition(nodes, originPosX, originPosY);
		AStarNode goal   = getNodeAtPosition(nodes, destinationPosX, destinationPosY);

		// Firstly, add the origin node to the open node list.
		openNodes.add(origin);
		
		do {
			// Get the open node with the lowest score.
			AStarNode current = getOpenNodeWithLowestScore();
			
			// Add the current node to the closed list and remove it from the open one.
			closedNodes.add(current);
			openNodes.remove(current);
			
			// If we added the destination to the closed list, we've found a path
			if (closedNodes.contains(goal)) {
				break;
			}
			
			// Get all walkable nodes adjacent to the current one and iterate over them.
			ArrayList<AStarNode> adjacentNodes = getAdjacentNodes(current);
			for (AStarNode adjacentNode : adjacentNodes) {
				
				// Ignore the adjacent node if it is already in the closed list.
				if (closedNodes.contains(adjacentNode)) {
					continue;
				}
				
				// If the current node is not already in the open list...
				if (!openNodes.contains(adjacentNode)) {
					// ...Compute its score...
					adjacentNode.g = current.g + 1;
					// ...Set its parent...
					adjacentNode.parent = current;
					// ...And add it to the open list.
					openNodes.add(adjacentNode);
				} else {
					// ...Test if using the current G score make the aSquare F score lower, if yes update the parent because it means its a better path.
					if (current.g + adjacentNode.h < adjacentNode.f()) {
						adjacentNode.parent = current;
					}
				}
			}
		} while(!openNodes.isEmpty());
		
		// Work backwards from the goal node to work out the sequence of directional
		// movements required to reach the goal from the original position.
		Stack<Direction> movements = new Stack<Direction>();
		AStarNode current          = goal;
		while (current.parent != null) {
			if(current.x < current.parent.x) {
				movements.push(Direction.LEFT);
			} else if(current.x > current.parent.x) {
				movements.push(Direction.RIGHT);
			} else if(current.y < current.parent.y) {
				movements.push(Direction.DOWN);
			} else {
				movements.push(Direction.UP);
			}
			current = current.parent;
		}
		
		return movements;
	}
	
	/**
	 * Get a list of nodes adjacent to the specified one.
	 * @param current
	 * @return adjacent nodes.
	 */
	private ArrayList<AStarNode> getAdjacentNodes(AStarNode current) {
		ArrayList<AStarNode> adjacentNodes = new ArrayList<AStarNode>();
		
		AStarNode nodeAbove = getNodeAtPosition(nodes, current.x, current.y + 1);
		if (nodeAbove != null) {
			adjacentNodes.add(nodeAbove);
		}
		
		AStarNode nodeBelow = getNodeAtPosition(nodes, current.x, current.y - 1);
		if (nodeBelow != null) {
			adjacentNodes.add(nodeBelow);
		}
		
		AStarNode nodeLeft = getNodeAtPosition(nodes, current.x - 1, current.y );
		if (nodeLeft != null) {
			adjacentNodes.add(nodeLeft);
		}
		
		AStarNode nodeRight = getNodeAtPosition(nodes, current.x + 1, current.y);
		if (nodeRight != null) {
			adjacentNodes.add(nodeRight);
		}
		
		return adjacentNodes;
	}

	/**
	 * Gets the open node with the lowest score.
	 * @return node
	 */
	private AStarNode getOpenNodeWithLowestScore() {
		AStarNode lowest = null;
		// Iterate over the open nodes to find the lowest score.
		for (AStarNode node : openNodes) {
			if (lowest == null || node.f() < lowest.f()) {
				// We found a node with a lower score!
				lowest = node;
			}
		}
		return lowest;
	}
	
	/**
	 * Get the node at a specified position.
	 * Returns null if there is no node at the specified position.
	 * @param nodes
	 * @param x
	 * @param y
	 * @return node
	 */
	private static AStarNode getNodeAtPosition(ArrayList<AStarNode> nodes, int x, int y) {
		// Get the node from the nodes list which has the specified position.
		for (AStarNode node : nodes) {
			if (node.x == x && node.y == y) {
				return node;
			}
		}
		// Could not find a node at the specified position, return null.
		return null;
	}
}
