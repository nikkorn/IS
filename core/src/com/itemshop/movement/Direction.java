package com.itemshop.movement;

/**
 * Face-able directions.
 */
public enum Direction {
	UP   (0),
	DOWN (1),
	LEFT (2),
	RIGHT(3);
	
	/** The direction code stored to allow for opposite extension method. */
	private final int directionCode;

	Direction(int directionCode) { this.directionCode = directionCode; }
    
	/**
	 * Get the opposite direction of this direction.
	 * @return opposite direction.
	 */
    public Direction opposite() {
        switch(directionCode) {
        	case 0:  return Direction.DOWN;
        	case 1:  return Direction.UP;
        	case 2:  return Direction.RIGHT;
        	default: return Direction.LEFT;
        }
    }
}
