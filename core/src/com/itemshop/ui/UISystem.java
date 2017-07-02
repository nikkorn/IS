package com.itemshop.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itemshop.render.TextureComponent;

/**
 * Stand alone render system for drawing the in-game UI.
 */
public class UISystem extends IteratingSystem {

	/** Component mappers to get components from entities. */
    private ComponentMapper<ScreenPositionComponent> screenPositionMapper;
    private ComponentMapper<TextureComponent> textureMapper;
	
	/** Sprite batch shared with the rest of the game. */
	SpriteBatch spriteBatch;
	
	/** Camera for UI scaling. */
	OrthographicCamera camera;

	public UISystem(OrthographicCamera camera, SpriteBatch spriteBatch) {
		super(Family.all(ScreenPositionComponent.class, TextureComponent.class).get());
		
		// Create the componentMappers.
		screenPositionMapper = ComponentMapper.getFor(ScreenPositionComponent.class);
		textureMapper  = ComponentMapper.getFor(TextureComponent.class);
		
		this.spriteBatch = spriteBatch;
		this.camera = camera;
	}

	/**
	 * Updates the system.
	 * @param deltaTime since last update.
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
    	ScreenPositionComponent screenPosition = screenPositionMapper.get(entity);
    	TextureComponent texture = textureMapper.get(entity);
    	
    	spriteBatch.draw(texture.region, screenPosition.x, screenPosition.y, screenPosition.width, screenPosition.height);
    }
}
