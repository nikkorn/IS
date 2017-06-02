package com.itemshop.item;

import com.badlogic.ashley.core.Component;

/**
 * A component that defines a dropped item.
 */
public class DroppedComponent implements Component {

	/** The time the entity was dropped. */
	public long droppedTime = System.currentTimeMillis();
}
