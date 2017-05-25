package com.itemshop.render;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Handles processing of each entity's render component.
 */
public class RenderSystem extends IteratingSystem {

	/** The SpriteBatch to use throughout the application. */
	SpriteBatch batch;

	/** Component mappers to get components from entities. */
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<SizeComponent> sizeMapper;
	
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
	}

	/**
	 * Updates the system.
	 * @param Time since last update.
	 */
    public void update(float deltaTime) {
    	Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	
    	CameraSystem camera = this.getEngine().getSystem(CameraSystem.class);
    	
    	batch.setProjectionMatrix(camera.getProjectionMatrix());
    	
		batch.begin();
		super.update(deltaTime);
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
