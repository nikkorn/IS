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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.itemshop.game.Assets;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.SizeComponent;

/**
 * Handles processing of each entity's input component.
 */
public class MouseSystem extends IteratingSystem {
	
	/** The mechanism used to retrieve each entity's input component. */
	static final ComponentMapper<MouseComponent> mouseMapper = ComponentMapper.getFor(MouseComponent.class);
	
	/** The mechanism used to retrieve each entity's input component. */
	static final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
	
	/** The mechanism used to retrieve each entity's input component. */
	static final ComponentMapper<SizeComponent> sizeMapper = ComponentMapper.getFor(SizeComponent.class);
	
	/** The camera to resolve positions with. */
	private Camera camera;

	/** The current mouse X position in world co-ordinates. */
	private float currentX;

	/** The current mouse Y position in world co-ordinates. */
	private float currentY;
	
	/**
	 * Creates the MouseSystem instance.
	 * @param camera The camera.
	 */
	public MouseSystem(Camera camera) {
		super(Family.all(MouseComponent.class, PositionComponent.class, SizeComponent.class).get());
		this.camera = camera;
		setUpCursor();
	}
	
	/**
	 * Updates the system.
	 * @param Time since last update.
	 */
    public void update(float deltaTime) {

		Vector3 mousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		Vector3 worldPosition = camera.unproject(mousePosition);

		this.currentX = worldPosition.x;
		this.currentY = worldPosition.y;
		
		super.update(deltaTime);
    }
	
	/**
	 * Processes a single entity's keyboard machine.
	 * @param entity The entity.
	 * @param deltaTime The time that has passed since the last update.
	 */
	public void processEntity(Entity entity, float deltaTime) {

		// Get the positional components.
		PositionComponent position = positionMapper.get(entity);
		SizeComponent size = sizeMapper.get(entity);

		boolean isXOverlap = (this.currentX > position.x) && (this.currentX < position.x + size.width);
		boolean isYOverlap = (this.currentY > position.y) && (this.currentY < position.y + size.height);
		boolean isOver = isXOverlap && isYOverlap;

		MouseComponent mouse = mouseMapper.get(entity);
		
		processHover(mouse, entity, isOver);
		
		boolean isClicking = isOver && Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		
		processClick(mouse, entity, isClicking);
	}
	
	private void processHover(MouseComponent mouse, Entity entity, boolean isOver) {
		if (isOver && !mouse.isHovered) {
			beginHover(mouse, entity);
		} else if (!isOver && mouse.isHovered) {
			endHover(mouse, entity);
		}
		mouse.isHovered = isOver;
	}
	
	private void processClick(MouseComponent mouse, Entity entity, boolean isClicking) {
		if (isClicking && !mouse.isClicked) {
			beginClick(mouse, entity);
		} else if (!isClicking && mouse.isClicked) {
			endClick(mouse, entity);
		}
		mouse.isClicked = isClicking;
	}
	
	private void beginHover(MouseComponent mouse, Entity entity) {
		if (mouse.onBeginHover == null) {
			return;
		}
		
		mouse.onBeginHover.perform(entity);
	}
	
	private void endHover(MouseComponent mouse, Entity entity) {
		if (mouse.onEndHover == null) {
			return;
		}
		
		mouse.onEndHover.perform(entity);
	}
	
	private void beginClick(MouseComponent mouse, Entity entity) {
		if (mouse.onBeginClick == null) {
			return;
		}
		mouse.onBeginClick.perform(entity);
	}
	
	private void endClick(MouseComponent mouse, Entity entity) {
		if (mouse.onEndClick == null) {
			return;
		}
		mouse.onEndClick.perform(entity);
	}
	
	private static void setUpCursor() {
		Texture cursorTexture = Assets.cursor;
		if (!cursorTexture.getTextureData().isPrepared())
		{
			cursorTexture.getTextureData().prepare();
		}
		
		Pixmap pixmap = cursorTexture.getTextureData().consumePixmap();
		
		int scale = 2;
		Pixmap pixmapScaled = new Pixmap(pixmap.getWidth() * scale, pixmap.getHeight() * scale, Format.RGBA8888);
		Pixmap.setFilter(Filter.NearestNeighbour);
		pixmapScaled.drawPixmap(pixmap, 0, 0, pixmap.getWidth(), pixmap.getHeight(), 0, 0, pixmapScaled.getWidth(), pixmapScaled.getHeight());
		
		Cursor customCursor = Gdx.graphics.newCursor(pixmapScaled, 0, 0);
		
		Gdx.graphics.setCursor(customCursor);
	}
}
