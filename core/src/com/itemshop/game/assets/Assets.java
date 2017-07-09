package com.itemshop.game.assets;

import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.itemshop.character.ISCharacter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Various game assets.
 */
public class Assets {
	// Character resource map.
	private static HashMap<ISCharacter, CharacterResources> characterResources = new HashMap<ISCharacter, CharacterResources>();

	private static final int SPRITE_SIZE = 16;

	private static final int BUTTON_WIDTH = 64;
	private static final int BUTTON_HEIGHT = 16;

	public static TextureRegion wood_horizontal;
	public static TextureRegion wood_vertical;
	public static TextureRegion wood_thick;
	public static TextureRegion wood_square;
	
	public static TextureRegion stone;
	public static TextureRegion stone_top;
	public static TextureRegion stone_slope_nw;
	public static TextureRegion stone_slope_se;
	public static TextureRegion stone_border_corner;
	public static TextureRegion stone_border_side;
	public static TextureRegion stone_stairs;
	
	public static TextureRegion candle_wall;
	public static TextureRegion candle_table;
	public static TextureRegion plate;
	public static TextureRegion apple;
	public static TextureRegion dollar;

	public static TextureRegion water;
	public static TextureRegion water_top;
	public static TextureRegion water_falling;

	public static TextureRegion door;
	public static TextureRegion door_wood;
	public static TextureRegion door_stone;
	public static TextureRegion rock;
	public static TextureRegion window;
	
	public static TextureRegion slab;
	public static TextureRegion sand;

	public static TextureRegion roof_top;
	public static TextureRegion roof;
	public static TextureRegion roof_bottom;

	public static TextureRegion grass;
	public static TextureRegion grass_flower;
	public static TextureRegion grass_mole;
	public static TextureRegion grass_pebble;
	public static TextureRegion grass_medium;
	public static TextureRegion grass_long;
	public static TextureRegion grass_edge_left;
	public static TextureRegion grass_edge_right;
	
	public static TextureRegion unknown;
	
	public static TextureRegion play;
	public static TextureRegion play_hover;
	public static TextureRegion play_click;
	
	public static Texture cursor;
	
	/**
	 * Load assets from disk.
	 */
	public static void load () {

		// Character resources.
		Texture characterSpritesheet = new Texture(Gdx.files.internal("images/sprites/2.png"));
		for (ISCharacter character : ISCharacter.values()) {
			characterResources.put(character, new CharacterResources(character, characterSpritesheet));
		}

		Texture mainSheet = getTexture("images/sprites/1.png");

		wood_horizontal = getSpriteAt(mainSheet, 0, 0);
		wood_vertical = getSpriteAt(mainSheet, 1, 0);
		wood_thick = getSpriteAt(mainSheet, 2, 0);
		wood_square = getSpriteAt(mainSheet, 3, 0);

		stone = getSpriteAt(mainSheet, 0, 1);
		stone_top = getSpriteAt(mainSheet, 1, 1);
		stone_slope_nw = getSpriteAt(mainSheet, 2, 1);
		stone_slope_se = getSpriteAt(mainSheet, 3, 1);
		stone_border_corner = getSpriteAt(mainSheet, 4, 1);
		stone_border_side = getSpriteAt(mainSheet, 5, 1);
		stone_stairs = getSpriteAt(mainSheet, 6, 1);

		candle_wall = getSpriteAt(mainSheet, 0, 2);
		candle_table = getSpriteAt(mainSheet, 1, 2);
		plate = getSpriteAt(mainSheet, 2, 2);
		apple = getSpriteAt(mainSheet, 3, 2);
		dollar = getSpriteAt(mainSheet, 4, 2);

		water = getSpriteAt(mainSheet, 0, 3);
		water_top = getSpriteAt(mainSheet, 1, 3);
		water_falling = getSpriteAt(mainSheet, 2, 3);

		door = getSpriteAt(mainSheet, 0, 4);
		door_wood = getSpriteAt(mainSheet, 1, 4);
		door_stone = getSpriteAt(mainSheet, 2, 4);
		rock = getSpriteAt(mainSheet, 3, 4);
		window = getSpriteAt(mainSheet, 4, 4);

		slab = getSpriteAt(mainSheet, 0, 5);
		sand = getSpriteAt(mainSheet, 1, 5);

		roof_top = getSpriteAt(mainSheet, 0, 6);
		roof = getSpriteAt(mainSheet, 1, 6);
		roof_bottom = getSpriteAt(mainSheet, 2, 6);

		grass = getSpriteAt(mainSheet, 0, 7);
		grass_flower = getSpriteAt(mainSheet, 1, 7);
		grass_mole = getSpriteAt(mainSheet, 2, 7);
		grass_pebble = getSpriteAt(mainSheet, 3, 7);
		grass_medium = getSpriteAt(mainSheet, 4, 7);
		grass_long = getSpriteAt(mainSheet, 5, 7);
		grass_edge_left = getSpriteAt(mainSheet, 6, 7);
		grass_edge_right = getSpriteAt(mainSheet, 7, 7);
		
		// Use one of the empty question marks as a placeholder if we need it.
		unknown = getSpriteAt(mainSheet, 0, 7);
		
		cursor = getTexture("images/sprites/cursor.png");

		Texture menuSheet = getTexture("images/sprites/menu.png");

		play = getButtonAt(menuSheet, 0, 0);
		play_hover = getButtonAt(menuSheet, 0, 1);
		play_click = getButtonAt(menuSheet, 0, 2);
	}

	/**
	 * Loads a texture from disk.
	 * @param file The texture location on disk.
	 * @return The loaded texture.
	 */
	private static Texture getTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	/**
	 * Extracts a sprite texture from a sprite sheet.
	 * @param spritesheet The sheet containing the sprite.
	 * @param x The X position of the sprite within the sheet.
	 * @param y The Y position of the sprite within the sheet.
	 * @return The sprite texture.
	 */
	public static TextureRegion getSpriteAt(Texture spritesheet, int x, int y) {
		return new TextureRegion(
			spritesheet,
			x * SPRITE_SIZE,
			y * SPRITE_SIZE,
			SPRITE_SIZE,
			SPRITE_SIZE
		);
	}

	/**
	 * Get the character resources for a specific character.
	 * @param character
	 * @return character resources
	 */
	public static CharacterResources getCharacterResources(ISCharacter character) {
		return characterResources.get(character);
	}

	/**
	 * Extracts a button texture from a button sprite sheet.
	 * @param spritesheet The sheet containing the button sprite.
	 * @param x The X position of the button sprite within the sheet.
	 * @param y The Y position of the button sprite within the sheet.
	 * @return The button sprite texture.
	 */
	private static TextureRegion getButtonAt(Texture spritesheet, int x, int y) {
		return new TextureRegion(
			spritesheet,
			x * BUTTON_WIDTH,
			y * BUTTON_HEIGHT,
			BUTTON_WIDTH,
			BUTTON_HEIGHT
		);
	}
}
