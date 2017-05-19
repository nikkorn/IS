package com.itemshop.input;

import java.util.Map;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;

public class InputSystem extends EntitySystem {
	static final Family family = Family.one(InputComponent.class).get();
	static final ComponentMapper<InputComponent> mapper = ComponentMapper.getFor(InputComponent.class);
    public void update(float deltaTime) {
    	for (Entity entity : this.getEngine().getEntitiesFor(family)) {
    		InputComponent input = (InputComponent) mapper.get(entity);
    		for (Map.Entry<Integer, InputAction> entry : input.actions.entrySet()) {
    			int keyCode = entry.getKey();
    			if(Gdx.input.isKeyPressed(keyCode)) {
    				entry.getValue().perform(entity);
    			}
    		}
    	}
    }
}
