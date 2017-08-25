package com.itemshop.character.walking.astar;

import java.util.ArrayList;
import java.util.HashMap;
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
	 * @param entites the entities to be mapped to nodes
	 * @param creator the node creator
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
	 * Takes an origin position and a destination position and produces a stack of movement directions needed to reach the destination.
	 * @param originPosX
	 * @param originPosY
	 * @param destinationPosX
	 * @param destinationPosY
	 * @return The result.
	 */
	public AStarResult getPath(int originPosX, int originPosY, int destinationPosX, int destinationPosY) {
		
		// The result of the pathfinding algorithm.
		AStarResult result = new AStarResult(); 

		// Get the origin and goal node.
		AStarNode origin = nodes.get(originPosX + ":" + originPosY);
		AStarNode goal   = nodes.get(destinationPosX + ":" + destinationPosY);
		
		// If we did not get our goal node, then we will add one, but keep note that
		// if there is a valid path to it, that the target itself is not walkable.
		if (goal == null) {
			// The target is not walkable.
			result.isTargetWalkable = false;
			// Create a node for the target ...
			goal = new AStarNode(destinationPosX, destinationPosY);
			// ... and add it the node list.
			nodes.put(destinationPosX + ":" + destinationPosY, goal);
		}

		// Set the heuristic for the origin and goal nodes.
		setHeuristic(origin, destinationPosX, destinationPosY);
		setHeuristic(goal, destinationPosX, destinationPosY);

		// Firstly, add the origin node to the open node map.
		openNodes.put(originPosX + ":" + originPosY, origin);

		do {
			// Get the open node with the lowest score.
			AStarNode current = getOpenNodeWithLowestScore();

			// Add the current node to the closed map and remove it from the open one.
			closedNodes.put(current.x + ":" + current.y, current);
			openNodes.remove(current.x + ":" + current.y);

			// If we added the destination to the closed map, we've found a path!
			if (closedNodes.containsKey(goal.x + ":" + goal.y)) {
				// The path is not blocked.
				result.isPathBlocked = false;
				// Stop looking for a path.
				break;
			}

			// Get all walkable nodes adjacent to the current one and iterate over them.
			ArrayList<AStarNode> adjacentNodes = getAdjacentNodes(current);
			for (AStarNode adjacentNode : adjacentNodes) {

				// Deduce the adjacent node key.
				String adjacentNodeKey = adjacentNode.x + ":" + adjacentNode.y;

				// Ignore the adjacent node if it is already in the closed map.
				if (closedNodes.containsKey(adjacentNodeKey)) {
					continue;
				}

				// If the current node is not already in the open map...
				if (!openNodes.containsKey(adjacentNodeKey)) {
					// ...Compute its score based on the adjacent nodes movement cost ...
					adjacentNode.g = current.g + adjacentNode.movementCost;
					// ...And compute its heuristic...
					setHeuristic(adjacentNode, destinationPosX, destinationPosY);
					// ...Set its parent...
					adjacentNode.parent = current;
					// ...And add it to the open map.
					openNodes.put(adjacentNodeKey, adjacentNode);
				} else {
					// ...Test if using the current G score make the adjacentNode F score
					// lower, if yes update the parent because it means it is a better path.
					if (current.g + adjacentNode.h < adjacentNode.f()) {
						adjacentNode.parent = current;
					}
				}
			}
		} while (!openNodes.isEmpty());

		// Work backwards from the goal node to work out the sequence of directional
		// movements required to reach the goal from the original position.
		AStarNode current = goal;
		// If the target is not itself walkable, we need to omit the last
		// directional movement which would move the entity onto it.
		if (!result.isTargetWalkable) {
			current = current.parent;
		}
		// Collect the directional movements that the path is composed of.
		while (current.parent != null) {
			if (current.x < current.parent.x) {
				result.movements.push(Direction.LEFT);
			} else if (current.x > current.parent.x) {
				result.movements.push(Direction.RIGHT);
			} else if (current.y < current.parent.y) {
				result.movements.push(Direction.DOWN);
			} else {
				result.movements.push(Direction.UP);
			}
			current = current.parent;
		}
		
		// Return the result.
		return result;
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

		AStarNode nodeLeft = nodes.get((current.x - 1) + ":" + current.y);
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
	 * Set a nodes heuristic value.
	 * @param node
	 * @param destinationPosX
	 * @param destinationPosY
	 */
	private static void setHeuristic(AStarNode node, int destinationPosX, int destinationPosY) {
		node.h = Math.abs(destinationPosX - node.x) + Math.abs(destinationPosY - node.y);
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
