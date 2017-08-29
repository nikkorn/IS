package com.itemshop.speech;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.RenderOffsetComponent;
import com.itemshop.render.TextureComponent;

/**
 * Handles processing of each entity's speech component.
 */
public class SpeechSystem extends IteratingSystem {
	
	/** Component mappers to get components from entities. */
    private static ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private static ComponentMapper<SpeechComponent> speechMapper     = ComponentMapper.getFor(SpeechComponent.class);

	/**
	 * Create a new instance of the SpeechSystem class.
	 */
	public SpeechSystem() {
		super(Family.all(SpeechComponent.class, PositionComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		
		// Get the speech component for this entity.
		SpeechComponent speechComponent = speechMapper.get(entity);
		
		// If there is no speech box entity for this component ... 
		if (speechComponent.speechBox == null) {
			
			// ... Create one and add it to the engine.
			speechComponent.speechBox = new Entity();
						
			// Make a note of when we started to display this speech box.
			speechComponent.displayStartTime = System.currentTimeMillis();
			
			// Create speech box texture.
			speechComponent.speechBox.add(createSpeechBoxTextureComponent(speechComponent.speechText));
			
			// A speech box will share the same position as the talker.
			speechComponent.speechBox.add(positionMapper.get(entity));
			
			// Add a render offset to the speech box entity so that it is drawn above the talker. 
			speechComponent.speechBox.add(new RenderOffsetComponent(1f, 1f));
			
			this.getEngine().addEntity(speechComponent.speechBox);
			
		} else {
			
			// ... Check whether the duration for this speech box is up. If so, delete
			// the speech box entity and remove the speech component from the talker.
			if ((System.currentTimeMillis() - speechComponent.displayStartTime) >= speechComponent.duration) {
				this.getEngine().removeEntity(speechComponent.speechBox);
				entity.remove(SpeechComponent.class);
			}
		}
	}
	
	/**
	 * Create a texture component for a speech box entity.
	 * @param text
	 * @return texture component.
	 */
	private TextureComponent createSpeechBoxTextureComponent(String text) {
		
		Pixmap speechBox = new Pixmap(30, 30, Pixmap.Format.RGB888);
		
		// TODO Actually draw the speech box to the pixmap.
		speechBox.fillRectangle(0, 0, 30, 30);
		
		return new TextureComponent(new TextureRegion(new Texture(speechBox)));
	}
}
