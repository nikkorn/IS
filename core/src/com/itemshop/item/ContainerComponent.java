package com.itemshop.item;

import java.util.ArrayList;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * A component that defines a container for other entities.
 */
public class ContainerComponent implements Component {
	
	/** The list of entities within this container. */
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	/** The capacity of this container. */
	private int capacity;
	
	/**
	 * Create a new instance of ContainerComponent.
	 * @param capacity
	 */
	public ContainerComponent(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Get the capacity of this container.
	 * @return capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
	/**
	 * Get the list of entities within this container.
	 * @return list of entities
	 */
	public ArrayList<Entity> getContents() {
		return this.entities;
	}
}
