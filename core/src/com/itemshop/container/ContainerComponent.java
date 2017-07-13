package com.itemshop.container;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * A component that allows an entity to contain other entities.
 */
public class ContainerComponent implements Component {
	
	/** The maximum amount the container can hold. */
	private int capacity;
	
	/** The list of items being contained. */
	private ArrayList<Entity> contents = new ArrayList<Entity>();
	
	/**
	 * Creates the container instance.
	 * @param capacity The maximum capacity for the container.
	 */
	public ContainerComponent(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Returns true if the container has space for additions.
	 * @return Whether the container has space for additions.
	 */
	public boolean hasSpace() {
		return contents.size() < capacity;
	}
	
	/**
	 * Adds the entity to the container.
	 * @param entity The entity to add.
	 * @throws Exception Container is at capacity.
	 */
	public void add(Entity entity) throws Exception {
		if (!this.hasSpace()){
			throw new Exception("Container is at capacity.");
		}
		contents.add(entity);
		this.onEntityAdded.perform(entity);
	}
	
	/**
	 * Removes the specified entity from the container.
	 * @param entity
	 */
	public void remove(Entity entity) {
		boolean removed = contents.remove(entity);
		if (removed) {
			this.onEntityRemoved.perform(entity);
		}
	}
	
	/**
	 * Gets the current list of entities.
	 * @return The current list of entities.
	 */
	public List<Entity> getContents() {
		return (List<Entity>) contents.clone();
	}
	
	/**
	 * Gets the capacity.
	 * @return The capacity.
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Gets the size.
	 * @return The size.
	 */
	public int getSize() {
		return contents.size();
	}
	
	/** Triggered when an entity is added to this container. */
	public ContainerAction onEntityAdded = (entity) -> {};

	/** Triggered when an entity is removed from this container. */
	public ContainerAction onEntityRemoved = (entity) -> {};
}
