package com.itemshop.character.walking;

import java.util.Stack;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.itemshop.character.FacingDirectionComponent;
import com.itemshop.character.walking.astar.AStarNode;
import com.itemshop.character.walking.astar.AStarPathfinder;
import com.itemshop.character.walking.astar.AStarResult;
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
		super(family, TRANSITION_STEP / 2);

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
	 * @param engine the engine		
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

		// If we are in transition between tiles we need to increase the position offset or remove the transition if it has been completed.
		if (isTransitioning) {

			// Modify the entities transition offset.
			modifyTransitionOffset(entity);
			
		} else {

			// We may not have computed a path for the target position defined in the path component.
			if (!path.isPathComputed) {

				// Compute a path for this path component
				this.computePath(path, position, walk);
				
				// If we were unable to compute a path and we are not atually next to our t
				if (path.movements.isEmpty()) {
					// We do not need the path component if we have no path to follow.
					entity.remove(PathComponent.class);
					// If we were walking before this path component was added to the current
					// entity then we need to let the walk component know we have stopped.
					if (walk.isWalking) {
						walk.onStop.perform(facingDirectionMapper.get(entity).direction);
						walk.isWalking = false;
					}
					// There is nothing to do now as we have no path to follow.
					return;
				}
			}

			// If we have no move movements to make in the path we have, we have reached our destination.
			if (path.movements.isEmpty()) {

				// TODO If the target position is not walkable then we need to change our facing direction now.
				
				// We have stopped walking because we have reached our target position.
				walk.onStop.perform(facingDirectionMapper.get(entity).direction);
				walk.isWalking = false;

				// We no longer need a path component if we have carried out all of the movements.
				entity.remove(PathComponent.class);
			} else {

				// Change position to the next tile in the path.
				moveToNextTileInPath(entity, path, position, walk);
			}
		}
	}
	
	/**
	 * For any entity which is transitioning between tiles we need to increase
	 * the position offset or remove the transition if it has been completed.
	 * @param entity
	 */
	private void modifyTransitionOffset(Entity entity) {
		
		// Get the transition.
		MovementTileTransitionComponent transition = tileTransitionMapper.get(entity);

		// If the transition is over then remove it, otherwise reduce the offset.
		if (transition.offset == TRANSITION_STEP) {
			entity.remove(MovementTileTransitionComponent.class);
		} else {
			// Decrease the transition offset.
			transition.offset -= TRANSITION_STEP;
		}
	}
	
	/**
	 * Move from one tile position to another.
	 * @param entity
	 * @param path
	 * @param position
	 * @param walk
	 */
	private void moveToNextTileInPath(Entity entity, PathComponent path, PositionComponent position, WalkComponent walk) {
		
		// Get the facing direction of this entity.
		Direction facingDirection = facingDirectionMapper.get(entity).direction;

		// We are exiting the current walkable tile.
		WalkableTileComponent currentWalkableTile = getWalkableTileComponentAtPosition(position.x, position.y);
		if (currentWalkableTile != null && currentWalkableTile.onExit != null) {
			currentWalkableTile.onExit.perform();
		}
		
		// Get the next movement we have to make in following the path.
		Direction nextDirectionOfMovement = path.movements.pop();

		// Alter the entities position based on this direction of movement.
		switch (nextDirectionOfMovement) {
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
		if (!walk.isWalking) {
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
		
		// We have officially entered the next walkable tile.
		currentWalkableTile = getWalkableTileComponentAtPosition(position.x, position.y);
		if (currentWalkableTile != null && currentWalkableTile.onEntry != null) {
			currentWalkableTile.onEntry.perform();
		}
		
		// The entity has started to move between tiles.
		entity.add(new MovementTileTransitionComponent(nextDirectionOfMovement.opposite(), 1f - TRANSITION_STEP));
	}

	/**
	 * Get the walkable tile component at the specified position.
	 * @param x
	 * @param y
	 * @return walkable tile component
	 */
	private WalkableTileComponent getWalkableTileComponentAtPosition(float x, float y) {
		for (Entity tile : positionedWalkableTileEntities) {
			// Get the tile entities position component.
			PositionComponent tilePosition = positionMapper.get(tile);
			if (tilePosition.x == x && tilePosition.y == y) {
				return walkableTileMapper.get(tile);
			}
		}
		// Could not find the tile at the position.
		return null;
	}
	
	/**
	 * Computes a path for a path component.
	 * @param path component
	 * @param position component
	 * @param walk component
	 */
	private void computePath(PathComponent path, PositionComponent position, WalkComponent walk) {
		
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
		
		// If the target position is not walkable the aim will be for the entity 
		// to move to the closest adjacent non-diagonal position. To calculate
		// a path for this we will have to include the target position as a walkable 
		// node when we calculate a path.
		if (!path.isTargetWalkable) {
			// Add a node to our pathfinder which represents our target position.
			pathfinder.addOrReplaceNode(new AStarNode((int) path.targetx, (int) path.targety));
			// Compute the path we need to take.
			AStarResult result = pathfinder.getPath((int) position.x, (int) position.y, (int) path.targetx, (int) path.targety);
			// Set the path movements on the path component.
			path.movements = result.movements;
			// We need to remove the last direction from our movements.
			removeLastMovementFromStack(path.movements);
			// Set a flag on the path component which defines whether the path was blocked. 
			path.isPathBlocked = result.isPathBlocked;
		} else {
			// Compute the path we need to take.
			AStarResult result = pathfinder.getPath((int) position.x, (int) position.y, (int) path.targetx, (int) path.targety);
			// Set the path movements on the path component.
			path.movements = result.movements;
			// Set a flag on the path component which defines whether the path was blocked. 
			path.isPathBlocked = result.isPathBlocked;
		}

		// We have computed the movements for this path.
		path.isPathComputed = true;
	}
	
	/**
	 * Remove the last directional movement from a movement stack.
	 * @param stack
	 */
	private void removeLastMovementFromStack(Stack<Direction> stack) {
		// Don't do anything if the stack is empty.
		if (!stack.isEmpty()) {
			Stack<Direction> temp = new Stack<Direction>();
			
			for (int i = 0; i < stack.size() - 1; i++) {
				temp.push(stack.pop());
			}
			
			stack.clear();
				
			while (!temp.isEmpty()) {
				stack.push(temp.pop());
			}
		}
	}
}
