package com.itemshop.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;

public class TestFactory {
	public static void create(Engine engine) {
		Entity entity = new Entity();
		
		InputComponent inputComponent = new InputComponent();

		inputComponent.actions.put(Input.Keys.UP, (Entity target) -> { System.out.println("Up in yo grill!"); });
		inputComponent.actions.put(Input.Keys.DOWN, (Entity target) -> { System.out.println("Down to partay!"); });
		inputComponent.actions.put(Input.Keys.LEFT, (Entity target) -> { System.out.println("Left alone!"); });
		inputComponent.actions.put(Input.Keys.RIGHT, (Entity target) -> { System.out.println("Right on it!"); });
		
		entity.add(inputComponent);
		
		engine.addEntity(entity);
	}
}
