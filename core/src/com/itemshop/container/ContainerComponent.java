package com.itemshop.container;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class ContainerComponent implements Component {
	private int capacity;
	private ArrayList<Entity> contents = new ArrayList<Entity>();
	public ContainerComponent(int capacity) {
		this.capacity = capacity;
	}
	public boolean hasSpace() {
		return contents.size() < capacity;
	}
	public void add(Entity entity) throws Exception {
		if (!this.hasSpace()){
			throw new Exception("Container is at capacity.");
		}
		contents.add(entity);
	}
	public void remove(Entity entity) {
		contents.remove(entity);
	}
	public List<Entity> get() {
		return (List<Entity>) contents.clone();
	}
}
