package com.itemshop.character.walking;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.itemshop.character.CharacterComponent;
import com.itemshop.character.FacingDirectionComponent;
import com.itemshop.render.PositionComponent;

/**
 * Handles processing of entities which can walk.
 */
public class WalkingSystem extends IteratingSystem {
	
	/** Component mappers to get components from entities. */
    private ComponentMapper<CharacterComponent> characterMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<TargetPositionComponent> targetPositionMapper;
    private ComponentMapper<FacingDirectionComponent> facingDirectionMapper;
    
    /** The walking system family. */
    private static Family family = Family.all(CharacterComponent.class, PositionComponent.class, TargetPositionComponent.class).get();
    
    /**
	 * Constructs the walking system instance.
	 */
    public WalkingSystem() {
		super(family);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
	}
}
