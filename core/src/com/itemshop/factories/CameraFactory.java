package com.itemshop.factories;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.itemshop.input.InputAction;
import com.itemshop.input.KeyboardComponent;
import com.itemshop.input.MouseComponent;
import com.itemshop.ui.ScreenPositionComponent;

/**
 * Factory for creating a camera.
 */
public class CameraFactory {
	/** Speed for sliding the screen. */
	private static final float MOVE_SPEED = 15f;
	
	/** Speed for zooming in or out */
	private static final float ZOOM_SPEED = 1;
	
	/** Speed for moving the map when dragging the screen */
	private static final float DRAG_SPEED = 0.035f;
	
	/** Size of the side areas. */
	private static final float REGION_SIZE = 0.05f;

	/**
	 * Creates the entity.
	 * @param engine The engine to add the entity to.
	 * @param camera The world camera to be moved around.
	 */
	public static void create(Engine engine, OrthographicCamera camera) {
		// Left, right, bottom, top.
		createSide(engine, camera, 0, 0, REGION_SIZE, 1, new int[]{Input.Keys.A, Input.Keys.LEFT}, -1, 0);
		createSide(engine, camera, 1 - REGION_SIZE, 0, REGION_SIZE, 1, new int[]{Input.Keys.D, Input.Keys.RIGHT}, 1, 0);
		createSide(engine, camera, 0, 0, 1, REGION_SIZE, new int[]{Input.Keys.S, Input.Keys.DOWN}, 0, -1);
		createSide(engine, camera, 0, 1 - REGION_SIZE, 1, REGION_SIZE, new int[]{Input.Keys.W, Input.Keys.UP}, 0, 1);
		
		// In, out.
		createZoom(engine, camera, new int[]{Input.Keys.EQUALS}, -1);
		createZoom(engine, camera, new int[]{Input.Keys.MINUS}, 1);
		
		// Drag.
		createDrag(engine, camera);
	}
	
	/**
	 * Creates an area at the edge of the screen which slides the map.
	 * @param engine The game engine.
	 * @param camera The world camera.
	 * @param x The area's X position.
	 * @param y The area's Y position.
	 * @param width The area's width.
	 * @param height The area's height.
	 * @param keys Any keyboard keys which should trigger the area.
	 * @param deltaX The amount that the world map should move along the X axis.
	 * @param deltaY The amount that the world map should move along the Y axis.
	 */
	private static void createSide(Engine engine, OrthographicCamera camera, float x, float y, float width, float height, int[] keys, int deltaX, int deltaY) {
		Entity entity = new Entity();

		// Create the screen region.
		entity.add(new ScreenPositionComponent(x, y, width, height));
		
		// Define the slide action.
		InputAction translate = (deltaTime) -> {
			camera.translate(deltaX * deltaTime * MOVE_SPEED, deltaY * deltaTime * MOVE_SPEED);
		};
		
		// Trigger the slide action on hover.
		MouseComponent mouseHandler = new MouseComponent();
		mouseHandler.onHovering = (deltaTime, deltaPosition) -> translate.perform(deltaTime);
		entity.add(mouseHandler);
		
		// Bind the keys to trigger the slide action.
		addKeys(entity, keys, translate);
		
		engine.addEntity(entity);
	}
	
	/**
	 * Creates a handler for zooming the map in or out.
	 * @param engine The game engine.
	 * @param camera The world camera.
	 * @param keys Any keyboard keys which should trigger the action.
	 * @param deltaZoom The amount that the zoom should change.
	 */
	private static void createZoom(Engine engine, OrthographicCamera camera, int[] keys, int deltaZoom) {
		Entity entity = new Entity();
		addKeys(entity, keys, (deltaTime) -> {
			camera.zoom += deltaZoom * deltaTime * ZOOM_SPEED;
		});
		engine.addEntity(entity);
	}
	
	/**
	 * Binds keyboard actions to the specified entity.
	 * @param entity The entity to bind to.
	 * @param keys The keys to trigger the action.
	 * @param action The action to trigger.
	 */
	private static void addKeys(Entity entity, int[] keys, InputAction action) {
		KeyboardComponent keyboardHandler = new KeyboardComponent();
		for (int keyIndex = 0; keyIndex < keys.length; keyIndex++) {
			keyboardHandler.actions.put(keys[keyIndex], action);
		}
		entity.add(keyboardHandler);
	}
	
	/**
	 * Creates a draggable area on the screen.
	 * @param engine The game engine.
	 * @param camera The world camera.
	 */
	private static void createDrag(Engine engine, OrthographicCamera camera) {
		Entity entity = new Entity();

		// Fill the screen with a drag area.
		entity.add(new ScreenPositionComponent(0, 0, 1, 2));
		
		// When dragging, move the camera as well.
		MouseComponent mouseHandler = new MouseComponent();
		mouseHandler.onClicking = (deltaTime, deltaPosition) -> {
			camera.translate(deltaPosition.x * DRAG_SPEED, -deltaPosition.y * DRAG_SPEED);
		};
		entity.add(mouseHandler);

		engine.addEntity(entity);
	}
}
