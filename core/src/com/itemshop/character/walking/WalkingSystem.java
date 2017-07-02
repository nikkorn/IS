package com.itemshop.character.walking;

import java.util.Random;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
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
	private ComponentMapper<WalkComponent> walkMapper;
    
    /** The walking system family. */
    private static Family family = Family.all(PositionComponent.class, 
    		TargetPositionComponent.class, FacingDirectionComponent.class).get();

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
		walkMapper            = ComponentMapper.getFor(WalkComponent.class);
	}

	@Override
	protected void processEntity(Entity entity) {

		// Get the required components via their mappers.
		TargetPositionComponent target = targetPositionMapper.get(entity);
		PositionComponent position     = positionMapper.get(entity);
		WalkComponent walk             = walkMapper.get(entity);
		
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

				// We have stopped walking because we have reached our target position.
				walk.onStop.perform(facingDirectionMapper.get(entity).direction);
				walk.isWalking = false;
    		}

			// Get the facing direction of this entity.
			Direction facingDirection = facingDirectionMapper.get(entity).direction;
    		
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

			// If we have not actually starting walking yet we should call onstart on the walk component.
			if(!walk.isWalking) {
				// We have started walking.
				walk.onStart.perform(direction);
				walk.isWalking = true;
			} else {
				// We had already started walking, determine if the position we have moved to is
				// different direction to the one we were walking in when moving to the last position.
				if (facingDirection != direction) {
					walk.onDirectionChange.perform(direction);
				}
			}
    		
    		// Set the new facing direction of this entity.
    		facingDirectionMapper.get(entity).direction = direction;

    		// The entity has started to move between tiles.
    		entity.add(new MovementTileTransitionComponent(direction.opposite(), 1f - TRANSITION_STEP));
    	}
	}
}
