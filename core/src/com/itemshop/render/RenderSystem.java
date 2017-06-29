package com.itemshop.render;

import java.util.Comparator;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itemshop.movement.MovementTileTransitionComponent;
import com.itemshop.ui.UserInterface;

/**
 * Handles processing of each entity's render component.
 */
public class RenderSystem extends SortedIteratingSystem {
	
	/** The size of the map. */
	public static final int HALF_MAP_SIZE = 25;
	
	/** The scaling factor to apply to convert map coordinates to screen pixels. */
	public static final float ZOOM_SCALE = 1f / 16f;

	/** The SpriteBatch to use throughout the application. */
	private SpriteBatch batch;

	/** Component mappers to get components from entities. */
    private static ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<SizeComponent> sizeMapper;
    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<AnimationComponent> animationMapper;
    private ComponentMapper<MovementTileTransitionComponent> tileTransitionMapper;
    
    /** Stand alone user interface rendering logic. */
    private UserInterface userInterface;
    
    /** The game camera. */
    private OrthographicCamera camera;
    
    /** Keep track of the time so that we can do some demo camera animation. */
    private float time;
	
	/**
	 * Constructs the render system instance.
	 */
	public RenderSystem(OrthographicCamera worldCamera) { 
		super(Family.all(PositionComponent.class, SizeComponent.class)
				.one(TextureComponent.class, AnimationComponent.class).get(), new ZComparator());
		
		// Create the sprite batch.
		batch = new SpriteBatch(); 
		
		// Create the componentMappers.
		positionMapper       = ComponentMapper.getFor(PositionComponent.class);
		sizeMapper           = ComponentMapper.getFor(SizeComponent.class);
		textureMapper        = ComponentMapper.getFor(TextureComponent.class);
		animationMapper      = ComponentMapper.getFor(AnimationComponent.class);
		tileTransitionMapper = ComponentMapper.getFor(MovementTileTransitionComponent.class);
		
		this.camera = worldCamera;
		
		// Move to the middle of the map.
		this.camera.translate(HALF_MAP_SIZE, HALF_MAP_SIZE);
		
		// Zoom so that the each sprite pixel is 2 screen pixels.
		this.camera.zoom = ZOOM_SCALE / 2;
		
		// Prepare the user interface for rendering.
		userInterface = new UserInterface(batch);
		
		// Track the amount of time passed so that we can do some sweet animations.
		time = 0;
	}

	/**
	 * Updates the system.
	 * @param Time since last update.
	 */
    public void update(float deltaTime) {
    	
    	// Clear the screen.
    	Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Do some sweet animations to demonstrate the camera functionality.
		time += deltaTime;
		camera.position.x = (float) (Math.sin(time / 2) * 5) + HALF_MAP_SIZE;
		camera.position.y = (float) (Math.cos(time / 2) * 5) + HALF_MAP_SIZE;
		
		// Make sure we are observing the camera position.
		camera.update();
    	batch.setProjectionMatrix(camera.combined);
    	
		batch.begin();
    	
		// Draw the game.
		super.update(deltaTime);
		
		// Draw the user interface.
		userInterface.render();

		batch.end();
    }
    
    @Override
    public void processEntity(Entity entity, float deltaTime)
    {
    	PositionComponent position = positionMapper.get(entity);
    	SizeComponent size         = sizeMapper.get(entity);
    	
    	// TODO Get either animation or texture.
    	TextureComponent texture = textureMapper.get(entity);
    	
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
    	batch.draw(texture.texture, position.x + offsetX, position.y + offsetY, size.width, size.height);
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
