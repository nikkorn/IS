package com.itemshop.render;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itemshop.ui.UserInterface;

/**
 * Handles processing of each entity's render component.
 */
public class RenderSystem extends IteratingSystem {
	
	/** The size of the map. */
	public static final int HALF_MAP_SIZE = 25;
	
	/** The scaling factor to apply to convert map coordinates to screen pixels. */
	public static final float ZOOM_SCALE = 1f / 16f;

	/** The SpriteBatch to use throughout the application. */
	private SpriteBatch batch;

	/** Component mappers to get components from entities. */
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<SizeComponent> sizeMapper;
    
    /** Stand alone user interface rendering logic. */
    private UserInterface userInterface;
    
    /** The game camera. */
    private OrthographicCamera camera;
    
    /** Keep track of the time so that we can do some demo camera animation. */
    private float time;
	
	/**
	 * Constructs the render system instance.
	 */
	public RenderSystem() { 
		super(Family.all(PositionComponent.class, TextureComponent.class, SizeComponent.class).get());
		
		// Create the sprite batch.
		batch = new SpriteBatch(); 
		
		// Create the componentMappers.
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		textureMapper  = ComponentMapper.getFor(TextureComponent.class);
		sizeMapper     = ComponentMapper.getFor(SizeComponent.class);
		
		// Create the camera.
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		this.camera = new OrthographicCamera(screenWidth, screenHeight);
		
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
    	TextureComponent texture   = textureMapper.get(entity);
    	SizeComponent size         = sizeMapper.get(entity);
    	
    	batch.draw(texture.texture, position.x, position.y, size.width, size.height);
    }
}
