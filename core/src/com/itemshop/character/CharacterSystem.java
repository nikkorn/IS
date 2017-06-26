package com.itemshop.character;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Handles processing of each Character entity.
 */
public class CharacterSystem extends IteratingSystem {
	
	/** Component mappers to get components from entities. */
    private static ComponentMapper<CharacterComponent> characterMapper;
    
    /**
	 * Constructs the character system instance.
	 */
	public CharacterSystem() {
		super(Family.all(CharacterComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		// Get the character component.
		CharacterComponent character = characterMapper.get(entity);

		System.out.println("Processing character: " + character.name);
	}
}
