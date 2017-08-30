package com.itemshop.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * The games font pack.
 */
public class FontPack {
	
	/** Singleton instance of FontPack. */
	private static FontPack instance;
	
	/** The games main font generator. */
	private static FreeTypeFontGenerator mainFontGenerator;
	
	/**
	 * The games font types.
	 */
	public enum FontType {
		MAIN_FONT
	}
	
	/**
	 * Create a new instance of the FontPack class.
	 */
	private FontPack() {
		mainFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/C&C Red Alert [INET].ttf"));
	}
	
	/**
	 * Get our FontPack singleton instance.
	 * @return FontPack
	 */
	public static FontPack getFontPack() {
		if(instance == null) {
			instance = new FontPack();
		}
		return instance;
	}
	
	/**
	 * Get a game font.
	 * @param type
	 * @param fontParameter
	 * @return font
	 */
	public BitmapFont getFont(FontType type, FreeTypeFontParameter fontParameter) {
		switch(type) {
			case MAIN_FONT:
				return mainFontGenerator.generateFont(fontParameter);
		}
		return null;
	}
}