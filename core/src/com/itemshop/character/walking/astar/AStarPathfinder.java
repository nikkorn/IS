package com.itemshop.character.walking.astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.itemshop.movement.Direction;

/**
 * An implementation of the A* pathfinding algorithm for finding shortest paths over walkable tiles.
 */
public class AStarPathfinder {
	
	/** The map of all nodes. */
	private HashMap<String, AStarNode> nodes = new HashMap<String, AStarNode>();
	
	/** The map of all open nodes. */
	private HashMap<String, AStarNode> openNodes = new HashMap<String, AStarNode>();
	
	/** The map of all closed nodes. */
	private HashMap<String, AStarNode> closedNodes = new HashMap<String, AStarNode>();
	
	/**
	 * Create an instance of the AStarPathfinder class.
	 */
	public AStarPathfinder(ImmutableArray<Entity> entites, AStarNodeCreator creator) {
		// Create the nodes list from the entities.
		for (Entity entity : entites) {
			// Create the node.
			AStarNode node = creator.create(entity);
			// Put the node in the map of all nodes.
			nodes.put(node.x + ":" + node.y, creator.create(entity));
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
		for (AStarNode node : nodes.values()) {
			node.h = Math.abs(destinationPosX-node.x) + Math.abs(destinationPosY-node.y);
		}
		
		// Get the origin and goal node.
		AStarNode origin = nodes.get(originPosX + ":" + originPosY);
		AStarNode goal   = nodes.get(destinationPosX + ":" + destinationPosY);

		// Firstly, add the origin node to the open node map.
		openNodes.put(originPosX + ":" + originPosY, origin);
		
		do {
			// Get the open node with the lowest score.
			AStarNode current = getOpenNodeWithLowestScore();
			
			// Add the current node to the closed map and remove it from the open one.
			closedNodes.put(current.x + ":" + current.y, current);
			openNodes.remove(current.x + ":" + current.y);
			
			// If we added the destination to the closed list, we've found a path
			if (closedNodes.containsKey(goal.x + ":" +goal.y)) {
				break;
			}
			
			// Get all walkable nodes adjacent to the current one and iterate over them.
			ArrayList<AStarNode> adjacentNodes = getAdjacentNodes(current);
			for (AStarNode adjacentNode : adjacentNodes) {
				
				// Deduce the adjacent nodes key.
				String adjacentNodeKey = adjacentNode.x + ":" + adjacentNode.y;
				
				// Ignore the adjacent node if it is already in the closed map.
				if (closedNodes.containsKey(adjacentNodeKey)) {
					continue;
				}
				
				// If the current node is not already in the open map...
				if (!openNodes.containsKey(adjacentNodeKey)) {
					// ...Compute its score...
					adjacentNode.g = current.g + 1;
					// ...Set its parent...
					adjacentNode.parent = current;
					// ...And add it to the open map.
					openNodes.put(adjacentNodeKey, adjacentNode);
				} else {
					// ...Test if using the current G score make the adjacentNode F score lower, 
					// if yes update the parent because it means it iss a better path.
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
		
		AStarNode nodeAbove = nodes.get(current.x + ":" + (current.y + 1));
		if (nodeAbove != null) {
			adjacentNodes.add(nodeAbove);
		}
		
		AStarNode nodeBelow = nodes.get(current.x + ":" + (current.y - 1));
		if (nodeBelow != null) {
			adjacentNodes.add(nodeBelow);
		}
		
		AStarNode nodeLeft = nodes.get((current.x - 1) + ":" + current.y );
		if (nodeLeft != null) {
			adjacentNodes.add(nodeLeft);
		}
		
		AStarNode nodeRight = nodes.get((current.x + 1) + ":" + current.y);
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
		for (AStarNode node : openNodes.values()) {
			if (lowest == null || node.f() < lowest.f()) {
				// We found a node with a lower score!
				lowest = node;
			}
		}
		return lowest;
	}
}
