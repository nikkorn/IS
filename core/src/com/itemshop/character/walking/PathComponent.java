package com.itemshop.character.walking;

import java.util.Stack;
import com.badlogic.ashley.core.Component;
import com.itemshop.movement.Direction;

/**
 * Defines a path to walk.
 */
public class PathComponent implements Component {

	/** Flag defining whether the path has been calculated for this component. */
	public boolean isPathComputed = false;
	
	/** Flag defining whether the path can be followed. */
	public boolean isPathBlocked = false;
	
	/** Flag indicating whether the target position is actually walkable. */
	public boolean isTargetWalkable = true;
	
	/** Flag indicating whether the path has been followed to the target. */
	public boolean isComplete = false;

	/** The target x position. */
	public float targetx;

	/** The target y position. */
	public float targety;

	/** Stack of directions which define a path to follow. */
	public Stack<Direction> movements = new Stack<Direction>();
	
	/**
	 * Create a new instance of PathComponent.
	 * @param targetx
	 * @param targety
	 */
	public PathComponent(float targetx, float targety) {
		this.targetx = targetx;
		this.targety = targety;
	}
}
