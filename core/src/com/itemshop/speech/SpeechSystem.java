package com.itemshop.speech;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.itemshop.game.assets.FontPack;
import com.itemshop.game.assets.FontPack.FontType;
import com.itemshop.movement.MovementTileTransitionComponent;
import com.itemshop.game.assets.FontSize;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.RenderOffsetComponent;
import com.itemshop.render.RenderSizeComponent;
import com.itemshop.render.TextureComponent;

/**
 * Handles processing of each entity's speech component.
 */
public class SpeechSystem extends IteratingSystem {
	
	/** Component mappers to get components from entities. */
    private static ComponentMapper<PositionComponent> positionMapper                   = ComponentMapper.getFor(PositionComponent.class);
    private static ComponentMapper<SpeechComponent> speechMapper                       = ComponentMapper.getFor(SpeechComponent.class);
    private static ComponentMapper<MovementTileTransitionComponent> movementTileOffset = ComponentMapper.getFor(MovementTileTransitionComponent.class);
    private static ComponentMapper<RenderOffsetComponent> renderOffsetMapper           = ComponentMapper.getFor(RenderOffsetComponent.class);
    
    /** The font with which to draw our speech box text. */
    private BitmapFont speechBoxFont;
    
	/**
	 * Create a new instance of the SpeechSystem class.
	 */
	public SpeechSystem() {
		super(Family.all(SpeechComponent.class, PositionComponent.class).get());
		
		// Create the font with which to write speech box text.
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    	parameter.size                  = FontSize.MEDIUM * 4;
    	parameter.minFilter             = TextureFilter.Nearest;
    	parameter.magFilter             = TextureFilter.MipMapLinearNearest;
    	speechBoxFont                   = FontPack.getFontPack().getFont(FontType.MAIN_FONT, parameter);
    	speechBoxFont.setColor(Color.WHITE);
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
			createSpeechBoxTexture(speechComponent);
			
			// A speech box will share the same position as the talker.
			speechComponent.speechBox.add(positionMapper.get(entity));
			
			this.getEngine().addEntity(speechComponent.speechBox);
			
		} else {
			
			// ... Check whether the duration for this speech box is up. If so, delete
			// the speech box entity and remove the speech component from the talker.
			if ((System.currentTimeMillis() - speechComponent.displayStartTime) >= speechComponent.duration) {
				this.getEngine().removeEntity(speechComponent.speechBox);
				entity.remove(SpeechComponent.class);
				return;
			}
			
			// Get the render offset component of the speech box.
			RenderOffsetComponent speechBoxRenderOffset = renderOffsetMapper.get(speechComponent.speechBox);
			
			// Modify the render offset of this speech box entity to reflect the potential tile offset of the talker.
			if (movementTileOffset.has(entity)) {
				
				// The offsets and size to use when drawing this entity.
		    	float offsetX = speechComponent.boxOffsetX, offsetY = speechComponent.boxOffsetY;
		    	
		    	// Apply the offset in the specified direction.
	    		switch(movementTileOffset.get(entity).direction) {
					case DOWN:
						offsetY -= movementTileOffset.get(entity).offset;
						break;
					case LEFT:
						offsetX -= movementTileOffset.get(entity).offset;
						break;
					case RIGHT:
						offsetX += movementTileOffset.get(entity).offset;
						break;
					case UP:
						offsetY += movementTileOffset.get(entity).offset;
						break;
	    		}
				
				speechBoxRenderOffset.offsetX = offsetX;
				speechBoxRenderOffset.offsetY = offsetY;
			} else {
				speechBoxRenderOffset.offsetX = speechComponent.boxOffsetX;
				speechBoxRenderOffset.offsetY = speechComponent.boxOffsetY;
			}
		}
	}
	
	/**
	 * Create a texture component for a speech box entity.
	 * @param speechBox
	 * @param text
	 */
	private void createSpeechBoxTexture(SpeechComponent speechComponent) {
		
		GlyphLayout glyphLayout = new GlyphLayout();
		glyphLayout.setText(speechBoxFont, speechComponent.speechText);
		
		// Determine the speech box padding.
		int padding = (int)(glyphLayout.height / 3f);
		
		FrameBuffer fbo   = new FrameBuffer(Pixmap.Format.RGB888, (int) glyphLayout.width + (padding*2), (int) glyphLayout.height + (padding*2), false);
		SpriteBatch batch = new SpriteBatch();
		
        // Set up an ortho projection matrix
        Matrix4 projMat = new Matrix4();
        projMat.setToOrtho2D(0, 0, fbo.getWidth(), fbo.getHeight());
        batch.setProjectionMatrix(projMat);

        // Render the text onto an FBO
        fbo.begin();
        batch.begin();
        speechBoxFont.draw(batch, speechComponent.speechText, padding, glyphLayout.height + padding);
        batch.end();
        fbo.end();

        // Flip the texture, and return it
        TextureRegion tex = new TextureRegion(fbo.getColorBufferTexture());
       	tex.flip(false, true);
  
		speechComponent.boxOffsetX = 1f;
		speechComponent.boxOffsetY = 1f;

		speechComponent.speechBox.add(new TextureComponent(tex));
		
		// Add a render offset to the speech box entity so that it is drawn above the talker. 
		speechComponent.speechBox.add(new RenderOffsetComponent(speechComponent.boxOffsetX, speechComponent.boxOffsetY));
		
		// Add a render size to match the speech box size.
		speechComponent.speechBox.add(new RenderSizeComponent(fbo.getWidth() / (4f*16f), fbo.getHeight() / (4f*16f)));
	}
}
