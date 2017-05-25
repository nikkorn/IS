package com.itemshop.render;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;

/**
 * The camera system.
 */
public class CameraSystem extends EntitySystem {
	
	/** The entity to follow (if defined). */
	private Entity target;
	
	/** The camera */
	private OrthographicCamera camera;
	
	/**
	 * Invoked when the system is added to the engine.
	 * @param engine The engine the system is being added to.
	 */
	public void addedToEngine(Engine engine) {
		
		// Create the camera to match the screen dimensions.
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		this.camera = new OrthographicCamera(screenWidth, screenHeight);
		
		// Ensure everything is tidy ready to begin.
		this.reset();
	}
	
	/**
	 * Sets a target for the camera to follow.
	 * @param entity The entity to follow.
	 */
	public void setTarget(Entity entity) {
		
		// Update the target.
		this.target = entity;
	}
	
	/**
	 * Clears the target that the camera is following.
	 */
	public void clearTarget() {
		
		// Clear the target.
		this.target = null;
	}
	
	/**
	 * Sets the camera to an arbitrary position.
	 * @param x The x Position.
	 * @param y The y position.
	 * @param zoom The zoom level.
	 */
	public void setPosition(int x, int y, int zoom) {
		
		// Stop following the target if it exists.
		this.clearTarget();
		
		// Update the position.
		this.camera.position.x = x;
		this.camera.position.y = y;
		this.camera.zoom = zoom;
	}
	
	/**
	 * Resets the camera.
	 */
	public void reset() {
		
		// No target.
		this.clearTarget();
		
		// No position.
		this.camera.position.x = 0;
		this.camera.position.y = 0;
		this.camera.zoom = 1;
	}
	
	/**
	 * Gets the camera's projection matrix.
	 * @return The camera's projection matrix.
	 */
	public Matrix4 getProjectionMatrix() { return camera.combined; }
	
	/**
	 * Updates the system.
	 * @param deltaTime The time that has passed (in milliseconds).
	 */
	public void update(float deltaTime) {
		
		// If there is an entity then follow it.
		if (this.target != null) {
			
			// Update camera X and Y position.
		}
		
		this.camera.update();
	}
}
