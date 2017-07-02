package com.itemshop.render;

import java.util.Comparator;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.itemshop.movement.MovementTileTransitionComponent;
import com.itemshop.ui.UserInterface;

/**
 * Handles processing of each entity's render component.
 */
public class RenderSystem extends SortedIteratingSystem {
	
	/** The SpriteBatch to use throughout the application. */
	private SpriteBatch spriteBatch;

	/** Component mappers to get components from entities. */
    private static ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<AnimationComponent> animationMapper;
    private ComponentMapper<MovementTileTransitionComponent> tileTransitionMapper;
    
    /** Stand alone user interface rendering logic. */
    private UserInterface userInterface;
    
    /** The game camera. */
    private OrthographicCamera camera;

	/** The game time (required for animations). */
	public float time = 0f;
	
	/**
	 * Constructs the render system instance.
	 */
	public RenderSystem(OrthographicCamera worldCamera, SpriteBatch spriteBatch) {
		super(Family.all(PositionComponent.class).one(TextureComponent.class, AnimationComponent.class).get(), new ZComparator());
		
		// Create the componentMappers.
		positionMapper       = ComponentMapper.getFor(PositionComponent.class);
		textureMapper        = ComponentMapper.getFor(TextureComponent.class);
		animationMapper      = ComponentMapper.getFor(AnimationComponent.class);
		tileTransitionMapper = ComponentMapper.getFor(MovementTileTransitionComponent.class);
		
		this.spriteBatch = spriteBatch; 
		camera = worldCamera;
	}

	/**
	 * Updates the system.
	 * @param deltaTime since last update.
	 */
    public void update(float deltaTime) {
    	
		// Make sure we are observing the camera position.
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);

		// Update the game tme.
		time += Gdx.graphics.getDeltaTime();
    	
		// Draw the game.
		super.update(deltaTime);
    }
    
    @Override
    public void processEntity(Entity entity, float deltaTime) {
    	PositionComponent position = positionMapper.get(entity);
    	
    	// Get the actual texture to draw, either from the texture or animation component.
    	TextureRegion texture = null;
    	if (textureMapper.has(entity)) {
    		texture = textureMapper.get(entity).region;
    	} else {
			texture = animationMapper.get(entity).animation.getKeyFrame(time, true);
    	}
    	
    	// Determine whether this entity should be drawn with an offset
    	float offsetX = 0f, offsetY = 0f;
    	if (tileTransitionMapper.has(entity)) {
    		// Apply the offset in the specified direction.
    		switch(tileTransitionMapper.get(entity).direction) {
				case DOWN:
					offsetY -= tileTransitionMapper.get(entity).offset;
					break;
				case LEFT:
					offsetX -= tileTransitionMapper.get(entity).offset;
					break;
				case RIGHT:
					offsetX += tileTransitionMapper.get(entity).offset;
					break;
				case UP:
					offsetY += tileTransitionMapper.get(entity).offset;
					break;
    		}
    	}
    	
    	// Draw the entity.
		spriteBatch.draw(texture, position.x + offsetX, position.y + offsetY, 1, 1);
    }
    
    /**
     * The comparator class for ordering positioned entities by Z index.
     */
    private static class ZComparator implements Comparator<Entity> {
        @Override
        public int compare(Entity e1, Entity e2) {
            return (int)Math.signum(positionMapper.get(e1).z - positionMapper.get(e2).z);
        }
    }
}
