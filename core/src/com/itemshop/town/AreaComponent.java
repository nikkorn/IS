package com.itemshop.town;

import com.badlogic.ashley.core.Component;

/**
 * A component which defines the area in which an entity resides.
 */
public class AreaComponent implements Component {
	
	/** The area. */
	public Area area;
	
	/**
	 * Create a new instance of AreaComponent.
	 * @param area
	 */
	public AreaComponent(Area area) {
		this.area = area;
	}
}
