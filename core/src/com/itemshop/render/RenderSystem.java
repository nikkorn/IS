package com.itemshop.render;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Handles processing of each entity's render component.
 */
public class RenderSystem extends IteratingSystem {
	
	/** The SpriteBatch to use throughout the application. */
	private SpriteBatch spriteBatch;

	/** Component mappers to get components from entities. */
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<TextureComponent> textureMapper;
    
    /** The game camera. */
    private OrthographicCamera camera;
	
	/**
	 * Constructs the render system instance.
	 */
	public RenderSystem(OrthographicCamera worldCamera, SpriteBatch spriteBatch) {
		super(Family.all(PositionComponent.class, TextureComponent.class).get());
		
		// Create the componentMappers.
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		textureMapper  = ComponentMapper.getFor(TextureComponent.class);
		
		this.spriteBatch = spriteBatch; 
		camera = worldCamera;
	}

	/**
	 * Updates the system.
	 * @param Time since last update.
	 */
    public void update(float deltaTime) {
    	
		// Make sure we are observing the camera position.
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
    	
		// Draw the game.
		super.update(deltaTime);
    }
    
    @Override
    public void processEntity(Entity entity, float deltaTime) {
    	PositionComponent position = positionMapper.get(entity);
    	TextureComponent texture   = textureMapper.get(entity);
    	
    	spriteBatch.draw(texture.texture, position.x, position.y, 1, 1);
    }
}
