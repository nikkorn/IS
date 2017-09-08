package com.itemshop.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itemshop.money.PaymentSystem;
import com.itemshop.money.WalletComponent;
import com.itemshop.money.WalletOwner;
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

	/** The font used in the UI. */
	BitmapFont font;

	public UISystem(OrthographicCamera camera, SpriteBatch spriteBatch) {
		super(Family.all(ScreenPositionComponent.class, TextureComponent.class).get());
		
		// Create the componentMappers.
		screenPositionMapper = ComponentMapper.getFor(ScreenPositionComponent.class);
		textureMapper  = ComponentMapper.getFor(TextureComponent.class);
		
		this.spriteBatch = spriteBatch;
		this.camera = camera;

		font = new BitmapFont();
		font.setColor(1.0f, 0f, 0f, 1.0f);
	}

	/**
	 * Updates the system.
	 * @param deltaTime since last update.
	 */
    public void update(float deltaTime) {
    	
		// Make sure we are observing the camera position.
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);

		spriteBatch.begin();
		
		// Draw the entities with screen positions and textures.
		super.update(deltaTime);
		
		// Draw the game HUD.
		this.drawHUD();
		
		spriteBatch.end();
	}
    
    @Override
    public void processEntity(Entity entity, float deltaTime) {
    	ScreenPositionComponent screenPosition = screenPositionMapper.get(entity);
    	TextureComponent texture               = textureMapper.get(entity);
  
    	spriteBatch.draw(texture.region, screenPosition.x, screenPosition.y, screenPosition.width, screenPosition.height);
    }
    
    /**
     * Draw the game HUD
     */
    private void drawHUD() {
    	
    	// Draw the shops money amount. First we need to get the wallet component of the shop from the game engine.
    	WalletComponent wallet = this.getEngine().getSystem(PaymentSystem.class).getWallet(WalletOwner.SHOP);
    	if (wallet != null) {
    		font.draw(spriteBatch, wallet.money + " gold", 20, 40);
    	}
    }
}
