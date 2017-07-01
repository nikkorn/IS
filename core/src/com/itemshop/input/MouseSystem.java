package com.itemshop.input;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Filter;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.itemshop.game.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.ui.ScreenPositionComponent;

/**
 * Handles processing of each entity's input component.
 */
public class MouseSystem extends IteratingSystem {
	static final ComponentMapper<MouseComponent> mouseMapper = ComponentMapper.getFor(MouseComponent.class);
	static final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
	static final ComponentMapper<ScreenPositionComponent> screenPositionMapper = ComponentMapper.getFor(ScreenPositionComponent.class);
	
	/** The cameras to resolve positions with. */
	private Camera worldCamera;
	private Camera uiCamera;

	private Vector3 worldPosition;
	private Vector3 uiPosition;
	
	private Vector2 lastPosition;
	private Vector2 deltaPosition;
	
	/**
	 * Creates the MouseSystem instance.
	 * @param camera The camera.
	 */
	public MouseSystem(Camera worldCamera, Camera uiCamera) {
		super(Family.all(MouseComponent.class).one(PositionComponent.class, ScreenPositionComponent.class).get());
		this.worldCamera = worldCamera;
		this.uiCamera = uiCamera;
		this.lastPosition = getPosition();
		setUpCursor();
	}
	
	/**
	 * Updates the system.
	 * @param Time since last update.
	 */
    public void update(float deltaTime) {
    	Vector2 currentPosition = getPosition();
    	
    	// Don't use the same vector3 for both, I think unproject is manipulating it.
		worldPosition = worldCamera.unproject(new Vector3(currentPosition, 0));
		uiPosition = uiCamera.unproject(new Vector3(currentPosition, 0));
		
		// Work out how much the mouse has moved.
		deltaPosition = lastPosition.sub(currentPosition);
		
		super.update(deltaTime);
		
		lastPosition = currentPosition;
    }
	
	/**
	 * Processes a single entity's keyboard machine.
	 * @param entity The entity.
	 * @param deltaTime The time that has passed since the last update.
	 */
	public void processEntity(Entity entity, float deltaTime) {
		boolean isOver;

		// Get the positional components.
		if (positionMapper.has(entity)) {
			PositionComponent position = positionMapper.get(entity);
			isOver = isOver(worldPosition.x, worldPosition.y, position.x, position.y, 1, 1);
		} else {
			ScreenPositionComponent screenPosition = screenPositionMapper.get(entity);
			isOver = isOver(uiPosition.x, uiPosition.y, screenPosition.x, screenPosition.y, screenPosition.width, screenPosition.height);
		}

		MouseComponent mouse = mouseMapper.get(entity);
		
		processHover(mouse, isOver, deltaTime);
		
		boolean isClicking = isOver && Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		
		processClick(mouse, isClicking, deltaTime);
	}
	
	/**
	 * Determines whether the mouse is over the specified position.
	 * @param mouseX The mouse's X position.
	 * @param mouseY The mouse's Y position.
	 * @param x The target's X position.
	 * @param y The target's Y position.
	 * @param width The target's width.
	 * @param height The target's height.
	 * @return Whether the mouse is over the specified position.
	 */
	private static boolean isOver(float mouseX, float mouseY, float x, float y, float width, float height) {
		boolean isXOverlap = mouseX > x && mouseX < x + width;
		boolean isYOverlap = mouseY > y && mouseY < y + height;
		return isXOverlap && isYOverlap;
	}
	
	/**
	 * Triggers any relevant hover handlers.
	 * @param mouse The mouse component.
	 * @param isOver Whether the mouse is over the component.
	 * @param deltaTime The amount of time that has passed.
	 */
	private void processHover(MouseComponent mouse, boolean isOver, float deltaTime) {
		if (isOver && !mouse.isHovered) {
			performAction(mouse.onBeginHover, deltaTime);
		} else if (isOver) {
			performAction(mouse.onHovering, deltaTime);
		} else if (!isOver && mouse.isHovered) {
			performAction(mouse.onEndHover, deltaTime);
		}
		mouse.isHovered = isOver;
	}
	
	/**
	 * Triggers any relevant click handlers.
	 * @param mouse The mouse component.
	 * @param isOver Whether the mouse is over the component.
	 * @param deltaTime The amount of time that has passed.
	 */
	private void processClick(MouseComponent mouse, boolean isClicking, float deltaTime) {
		if (isClicking && !mouse.isClicked) {
			performAction(mouse.onBeginClick, deltaTime);
		} else if (isClicking && mouse.isClicked) {
			performAction(mouse.onClicking, deltaTime);
		} else if (!isClicking && mouse.isClicked) {
			performAction(mouse.onEndClick, deltaTime);
		}
		mouse.isClicked = isClicking;
	}
	
	/**
	 * Performs an action if possible.
	 * @param action The action to attempt.
	 * @param deltaTime The amount of time that has passed.
	 */
	private void performAction(MouseInputAction action, float deltaTime) {
		if (action == null) {
			return;
		}
		action.perform(deltaTime, deltaPosition);
	}
	
	/**
	 * Creates the game cursor.
	 */
	private static void setUpCursor() {
		// Retrieve and convert the texture. 
		Texture cursorTexture = Assets.cursor;
		if (!cursorTexture.getTextureData().isPrepared()) {
			cursorTexture.getTextureData().prepare();
		}
		Pixmap pixmap = cursorTexture.getTextureData().consumePixmap();
		
		// Scale up the cursor.
		int scale = 2;
		Pixmap pixmapScaled = new Pixmap(pixmap.getWidth() * scale, pixmap.getHeight() * scale, Format.RGBA8888);
		Pixmap.setFilter(Filter.NearestNeighbour);
		pixmapScaled.drawPixmap(pixmap, 0, 0, pixmap.getWidth(), pixmap.getHeight(), 0, 0, pixmapScaled.getWidth(), pixmapScaled.getHeight());
		
		// Create and assign the cursor.
		Cursor customCursor = Gdx.graphics.newCursor(pixmapScaled, 0, 0);
		Gdx.graphics.setCursor(customCursor);
	}
	
	/**
	 * Gets the current mouse position.
	 * @return The current mouse position.
	 */
	private static Vector2 getPosition() {
		return new Vector2(Gdx.input.getX(), Gdx.input.getY());
	}
}
