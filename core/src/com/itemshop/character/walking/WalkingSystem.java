package com.itemshop.character.walking;

import java.util.Random;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.itemshop.character.CharacterComponent;
import com.itemshop.character.FacingDirectionComponent;
import com.itemshop.movement.Direction;
import com.itemshop.movement.MovementTileTransitionComponent;
import com.itemshop.render.PositionComponent;

/**
 * Handles processing of entities which can walk.
 */
public class WalkingSystem extends IntervalIteratingSystem {
	
	/** Component mappers to get components from entities. */
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<TargetPositionComponent> targetPositionMapper;
    private ComponentMapper<FacingDirectionComponent> facingDirectionMapper;
    private ComponentMapper<MovementTileTransitionComponent> tileTransitionMapper;
    
    /** The walking system family. */
    private static Family family = Family.all(PositionComponent.class, 
    		TargetPositionComponent.class, FacingDirectionComponent.class, CharacterComponent.class).get();

    /** The transition step to increment transition offsets by. */
    private static float TRANSITION_STEP = 1f / 8f;
    
    /**
	 * Constructs the walking system instance.
	 */
    public WalkingSystem() {
		super(family, TRANSITION_STEP / 2); // 16 times a second = 0.0625f
		
		// Create the componentMappers.
		positionMapper        = ComponentMapper.getFor(PositionComponent.class);
		targetPositionMapper  = ComponentMapper.getFor(TargetPositionComponent.class);
		facingDirectionMapper = ComponentMapper.getFor(FacingDirectionComponent.class);
		tileTransitionMapper  = ComponentMapper.getFor(MovementTileTransitionComponent.class);
	}

	@Override
	protected void processEntity(Entity entity) {
		
		// Get the required components via their mappers.
		TargetPositionComponent target = targetPositionMapper.get(entity);
		PositionComponent position     = positionMapper.get(entity);
		
		// If we have a tile transition component then we are walking between tiles.
		boolean isTransitioning = tileTransitionMapper.has(entity);
		
		// If we are in transition between tiles we need to increase the position
		// offset or remove the transition if it has been completed.
		if (isTransitioning) {
			
			// Get the transition.
			MovementTileTransitionComponent transition = tileTransitionMapper.get(entity);
			
			// If the transition is over then remove it, otherwise increase the offset.
			if (transition.offset == TRANSITION_STEP) {
				entity.remove(MovementTileTransitionComponent.class);
			} else {
				// Increase the transition offset.
				transition.offset -= TRANSITION_STEP;
			}
    	} else {
    		
    		// If we have reached our target we no longer need it.
    		if (target.y == position.y && target.x == position.x) {
    			target.x = new Random().nextInt(30) + 10;
    			target.y = new Random().nextInt(30) + 10;
    		}
    		
    		// For now, let us try to move towards our target, ignoring non-walkable tiles.
    		Direction direction = Direction.DOWN;
    		if(target.x > position.x) {
    			position.x += 1;
    			direction = Direction.RIGHT;
    		}
    		else if(target.x < position.x) {
    			position.x -= 1;
    			direction = Direction.LEFT;
    		}
    		else if(target.y > position.y) {
    			position.y += 1;
    			direction = Direction.UP;
    		}
    		else if(target.y < position.y) {
    			position.y -= 1;
    			direction = Direction.DOWN;
    		}
    		
    		// Set the facing direction of this entity
    		facingDirectionMapper.get(entity).direction = direction;
    		
    		// TODO Update the entities visual component.
    		
    		// The entity has started to move between tiles.
    		entity.add(new MovementTileTransitionComponent(direction.opposite(), 1f - TRANSITION_STEP));
    	}
	}
}
