package com.itemshop.character.walking;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.itemshop.character.FacingDirectionComponent;
import com.itemshop.character.walking.astar.AStarNode;
import com.itemshop.character.walking.astar.AStarPathfinder;
import com.itemshop.movement.Direction;
import com.itemshop.movement.MovementTileTransitionComponent;
import com.itemshop.movement.WalkableTileComponent;
import com.itemshop.render.PositionComponent;

/**
 * Handles processing of entities which can walk.
 */
public class PathingSystem extends IntervalIteratingSystem {
	
	/** Component mappers to get components from entities. */
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<PathComponent> pathMapper;
    private ComponentMapper<FacingDirectionComponent> facingDirectionMapper;
	private ComponentMapper<MovementTileTransitionComponent> tileTransitionMapper;
	private ComponentMapper<WalkComponent> walkMapper;
	private ComponentMapper<WalkableTileComponent> walkableTileMapper;
    
    /** The path finding system family. */
    private static Family family = Family.all(PositionComponent.class, FacingDirectionComponent.class)
			.one(PathComponent.class, MovementTileTransitionComponent.class).get();
    
    /** The transition step to decrement transition offsets by. */
    private static float TRANSITION_STEP = 1f / 8f;
    
    /** The positioned walkable tile entities. We need to know these in order to carry our path finding. */
    ImmutableArray<Entity> positionedWalkableTileEntities;
    
    /**
	 * Constructs the pathing system instance.
	 */
    public PathingSystem() {
		super(family, TRANSITION_STEP / 2); // 16 times a second = 0.0625f
		
		// Create the componentMappers.
		positionMapper        = ComponentMapper.getFor(PositionComponent.class);
		pathMapper            = ComponentMapper.getFor(PathComponent.class);
		facingDirectionMapper = ComponentMapper.getFor(FacingDirectionComponent.class);
		tileTransitionMapper  = ComponentMapper.getFor(MovementTileTransitionComponent.class);
		walkMapper            = ComponentMapper.getFor(WalkComponent.class);
		walkableTileMapper    = ComponentMapper.getFor(WalkableTileComponent.class);
	}
    
    /**
     * Called when this system is added to the engine.
     */
    public void addedToEngine(Engine engine) {
    	super.addedToEngine(engine);
    	positionedWalkableTileEntities = engine.getEntitiesFor(Family.all(PositionComponent.class, WalkableTileComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity) {

		// Get the required components via their mappers.
		PathComponent path         = pathMapper.get(entity);
		PositionComponent position = positionMapper.get(entity);
		WalkComponent walk         = walkMapper.get(entity);
		
		// If we have a tile transition component then we are walking between tiles.
		boolean isTransitioning = tileTransitionMapper.has(entity);
		
		// If we are in transition between tiles we need to increase the position
		// offset or remove the transition if it has been completed.
		if (isTransitioning) {
			
			// Get the transition.
			MovementTileTransitionComponent transition = tileTransitionMapper.get(entity);
			
			// If the transition is over then remove it, otherwise reduce the offset.
			if (transition.offset == TRANSITION_STEP) {
				entity.remove(MovementTileTransitionComponent.class);
			} else {
				// Increase the transition offset.
				transition.offset -= TRANSITION_STEP;
			}
    	} else {
    		
    		// We may not have computed a path for the target position defined in the path component.
    		if (!path.isPathComputed) {
    			
    			// Use A* to compute path to position defined by path.targtex and path.targety
    			AStarPathfinder pathfinder = new AStarPathfinder(positionedWalkableTileEntities, (tile) -> {
    				// Get the tile entities position component.
    				PositionComponent tilePosition = positionMapper.get(tile);
    				// Create an A* node based on this walkable tiles position.
    				AStarNode node = new AStarNode((int) tilePosition.x, (int) tilePosition.y);
    				// Set the movement cost for this node, this influences how favourable it is to walk on.
    				node.movementCost = walkableTileMapper.get(tile).movementCost;
    				// Return the node which represents the tile entity.
    				return node;
    			});
    			
    			long start = System.currentTimeMillis();
    			// Compute the path we need to take.
    			path.movements = pathfinder.getPath((int) position.x, (int) position.y, (int) path.targetx, (int) path.targety);
    			System.out.println("time:" + (System.currentTimeMillis() - start));
    			
    			// We have computed the movements for this path.
    			path.isPathComputed = true;
    		}
    		
    		// If we have no move movements to make in the path we have, we have reached our destination.
    		if (path.movements.isEmpty()) {

				// We have stopped walking because we have reached our target position.
				walk.onStop.perform(facingDirectionMapper.get(entity).direction);
				walk.isWalking = false;

				// We no longer need a path component if we have carried out all of the movements.
				entity.remove(PathComponent.class);
			} else {

				// Get the facing direction of this entity.
				Direction facingDirection = facingDirectionMapper.get(entity).direction;
				
				// Get the next movement we have to make in following the path.
				Direction nextDirectionOfMovement = path.movements.pop();

				// Alter the entities position based on this direction of movement.
				switch(nextDirectionOfMovement) {
					case DOWN:
						position.y -= 1;
						break;
					case LEFT:
						position.x -= 1;
						break;
					case RIGHT:
						position.x += 1;
						break;
					case UP:
						position.y += 1;
						break;
					default:
						break;
				}

				// If we have not actually starting walking yet we should call onstart() on the walk component.
				if(!walk.isWalking) {
					// We have started walking.
					walk.onStart.perform(nextDirectionOfMovement);
					walk.isWalking = true;
				} else {
					// We had already started walking, determine if the position we have moved to is
					// different direction to the one we were walking in when moving to the last position.
					if (facingDirection != nextDirectionOfMovement) {
						walk.onDirectionChange.perform(nextDirectionOfMovement);
					}
				}

				// Set the new facing direction of this entity.
				facingDirectionMapper.get(entity).direction = nextDirectionOfMovement;

				// The entity has started to move between tiles.
				entity.add(new MovementTileTransitionComponent(nextDirectionOfMovement.opposite(), 1f - TRANSITION_STEP));
			}
    	}
	}
}

