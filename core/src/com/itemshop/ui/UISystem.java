package com.itemshop.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.itemshop.game.assets.Assets;
import com.itemshop.game.assets.FontPack;
import com.itemshop.game.assets.FontSize;
import com.itemshop.game.assets.FontPack.FontType;
import com.itemshop.money.PaymentSystem;
import com.itemshop.money.WalletComponent;
import com.itemshop.money.WalletOwner;
import com.itemshop.render.TextureComponent;
import com.itemshop.schedule.Time;
import com.itemshop.schedule.TimeSystem;
import com.itemshop.state.State;
import com.itemshop.state.StateSystem;

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
		textureMapper        = ComponentMapper.getFor(TextureComponent.class);
		
		this.spriteBatch = spriteBatch;
		this.camera      = camera;

		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    	parameter.size                  = FontSize.X_LARGE;
    	parameter.minFilter             = TextureFilter.Nearest;
    	parameter.magFilter             = TextureFilter.MipMapLinearNearest;
    	font                            = FontPack.getFontPack().getFont(FontType.MAIN_FONT, parameter);
    	font.setColor(Color.WHITE);
	}

	/**
	 * Updates the system.
	 * @param deltaTime since last update.
	 */
    public void update(float deltaTime) {
    	
		// Make sure we are observing the camera position.
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);

		// Draw the entities with screen positions and textures.
		spriteBatch.begin();
		super.update(deltaTime);
		spriteBatch.end();
		
		// Draw UI elements specific to the game state.
		State currentState = this.getEngine().getSystem(StateSystem.class).getCurrentState();
		switch (currentState) {
			case Game:
				// Draw the game HUD.
				this.drawHUD();
				break;
			case Title:
				break;
			default:
				// Do nothing here.
				break;
		}
	}
    
    @Override
    public void processEntity(Entity entity, float deltaTime) {
    	ScreenPositionComponent screenPosition = screenPositionMapper.get(entity);
    	TextureComponent texture               = textureMapper.get(entity);
  
    	spriteBatch.draw(texture.region, screenPosition.x, screenPosition.y, screenPosition.width, screenPosition.height);
    }
    
    /**
     * Draw the in-game HUD
     */
    private void drawHUD() {
    	
    	SpriteBatch batch = new SpriteBatch();
    	batch.begin();
    	
    	// Draw the bar at the bottom of the screen.
    	batch.draw(Assets.hud_bottom_bar, 0, 0, Gdx.graphics.getWidth(), 32);
    	
    	// Draw the shops money amount. First we need to get the wallet component of the shop from the game engine.
    	WalletComponent wallet = this.getEngine().getSystem(PaymentSystem.class).getWallet(WalletOwner.SHOP);
    	if (wallet != null) {
    		font.draw(batch, wallet.money + "G", 20, 22);
    	}
    	
    	// Draw the time.
    	Time time = this.getEngine().getSystem(TimeSystem.class).getTime();
    	if (time != null) {
    		font.draw(batch, time.getFormattedTime(), Gdx.graphics.getWidth() - 65, 22);
    	}
 
    	batch.end();
    }
}
