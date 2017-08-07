package com.itemshop.game.assets;

import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.itemshop.character.Character;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Various game assets.
 */
public class Assets {
	// Character resource map.
	private static HashMap<Character, CharacterSprites> characterResources = new HashMap<Character, CharacterSprites>();

	private static final int TILE_SIZE = 16;
	private static final int ITEM_SIZE = 10;

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
	public static TextureRegion stone_cobble_wall;
	public static TextureRegion stone_cobble;

	public static TextureRegion water;
	public static TextureRegion water_top;
	public static TextureRegion water_falling;

	public static TextureRegion door;
	public static TextureRegion door_wood;
	public static TextureRegion door_stone;
	public static TextureRegion rock;
	public static TextureRegion window;

	public static TextureRegion slab;
	public static TextureRegion slab_round;
	public static TextureRegion slab_quad;
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

	public static TextureRegion bookcase_0;
	public static TextureRegion bookcase_1;
	public static TextureRegion bookcase_2;
	public static TextureRegion bookcase_3;
	public static TextureRegion bookcase_4;
	public static TextureRegion bookcase_5;
	public static TextureRegion bookcase_6;
	public static TextureRegion bookcase_7;
	public static TextureRegion bookcase_8;
	public static TextureRegion bookcase_9;
	public static TextureRegion bookcase_10;
	public static TextureRegion bookcase_11;
	public static TextureRegion bookcase_12;
	public static TextureRegion bookcase_13;
	public static TextureRegion bookcase_14;

	public static TextureRegion cabinet_wood;
	public static TextureRegion table_wood;
	public static TextureRegion display_wood;
	public static TextureRegion cabinet_stone;
	public static TextureRegion table_stone;
	public static TextureRegion display_stone;
	public static TextureRegion till;
	public static TextureRegion chest;
	public static TextureRegion bin;

	public static TextureRegion town_info_board;
	public static TextureRegion mail_box;
	public static Animation fountain;
	
	public static TextureRegion unknown;

	public static TextureRegion banana;
	public static TextureRegion candle;
	public static TextureRegion cherry;
	public static TextureRegion apple;
	public static TextureRegion muffin;
	public static TextureRegion flower;
	public static TextureRegion money_coins;
	public static TextureRegion money_notes;
	
	public static TextureRegion play;
	public static TextureRegion play_hover;
	public static TextureRegion play_click;
	
	public static Texture cursor;
	
	/**
	 * Load assets from disk.
	 */
	public static void load () {

		// Character resources.
		Texture characterSpritesheet = new Texture(Gdx.files.internal("images/sprites/characters.png"));
		for (Character character : Character.values()) {
			characterResources.put(character, new CharacterSprites(character, characterSpritesheet));
		}

		Texture tileSheet = getTexture("images/sprites/tiles.png");

		wood_horizontal = getTileAt(tileSheet, 0, 0);
		wood_vertical = getTileAt(tileSheet, 1, 0);
		wood_thick = getTileAt(tileSheet, 2, 0);
		wood_square = getTileAt(tileSheet, 3, 0);

		stone = getTileAt(tileSheet, 0, 1);
		stone_top = getTileAt(tileSheet, 1, 1);
		stone_slope_nw = getTileAt(tileSheet, 2, 1);
		stone_slope_se = getTileAt(tileSheet, 3, 1);
		stone_border_corner = getTileAt(tileSheet, 4, 1);
		stone_border_side = getTileAt(tileSheet, 5, 1);
		stone_stairs = getTileAt(tileSheet, 6, 1);
		stone_cobble_wall = getTileAt(tileSheet, 7, 1);
		stone_cobble = getTileAt(tileSheet, 8, 1);

		water = getTileAt(tileSheet, 0, 2);
		water_top = getTileAt(tileSheet, 1, 2);
		water_falling = getTileAt(tileSheet, 2, 2);

		door = getTileAt(tileSheet, 0, 3);
		door_wood = getTileAt(tileSheet, 1, 3);
		door_stone = getTileAt(tileSheet, 2, 3);
		rock = getTileAt(tileSheet, 3, 3);
		window = getTileAt(tileSheet, 4, 3);

		slab = getTileAt(tileSheet, 0, 4);
		slab_round = getTileAt(tileSheet, 1, 4);
		slab_quad = getTileAt(tileSheet, 2, 4);
		sand = getTileAt(tileSheet, 3, 4);

		roof_top = getTileAt(tileSheet, 0, 5);
		roof = getTileAt(tileSheet, 1, 5);
		roof_bottom = getTileAt(tileSheet, 2, 5);

		grass = getTileAt(tileSheet, 0, 6);
		grass_flower = getTileAt(tileSheet, 1, 6);
		grass_mole = getTileAt(tileSheet, 2, 6);
		grass_pebble = getTileAt(tileSheet, 3, 6);
		grass_medium = getTileAt(tileSheet, 4, 6);
		grass_long = getTileAt(tileSheet, 5, 6);
		grass_edge_left = getTileAt(tileSheet, 6, 6);
		grass_edge_right = getTileAt(tileSheet, 7, 6);
		
		bookcase_0 = getTileAt(tileSheet, 0, 7);
		bookcase_1 = getTileAt(tileSheet, 1, 7);
		bookcase_2 = getTileAt(tileSheet, 2, 7);
		bookcase_3 = getTileAt(tileSheet, 3, 7);
		bookcase_4 = getTileAt(tileSheet, 4, 7);
		bookcase_5 = getTileAt(tileSheet, 5, 7);
		bookcase_6 = getTileAt(tileSheet, 6, 7);
		bookcase_7 = getTileAt(tileSheet, 7, 7);
		bookcase_8 = getTileAt(tileSheet, 8, 7);
		bookcase_9 = getTileAt(tileSheet, 9, 7);
		bookcase_10 = getTileAt(tileSheet, 10, 7);
		bookcase_11 = getTileAt(tileSheet, 11, 7);
		bookcase_12 = getTileAt(tileSheet, 12, 7);
		bookcase_13 = getTileAt(tileSheet, 13, 7);
		bookcase_14 = getTileAt(tileSheet, 14, 7);

		cabinet_wood = getTileAt(tileSheet, 0, 8);
		table_wood = getTileAt(tileSheet, 1, 8);
		display_wood = getTileAt(tileSheet, 2, 8);
		cabinet_stone = getTileAt(tileSheet, 3, 8);
		table_stone = getTileAt(tileSheet, 4, 8);
		display_stone = getTileAt(tileSheet, 5, 8);
		till = getTileAt(tileSheet, 6, 8);
		chest = getTileAt(tileSheet, 7, 8);
		bin = getTileAt(tileSheet, 8, 8);

		town_info_board = getTileAt(tileSheet, 0, 9);
		mail_box = getTileAt(tileSheet, 4, 9);
		fountain = new Animation(1f/6f, Assets.getTileAt(tileSheet, 1, 9),
				Assets.getTileAt(tileSheet, 2, 9), Assets.getTileAt(tileSheet, 3, 9));

		// Use one of the empty question marks as a placeholder if we need it.
		unknown = getTileAt(tileSheet, 0, 15);
		
		// TODO: Add better support for 10x10 items.
		Texture itemSheet = getTexture("images/sprites/items.png");
		
		banana = getItemAt(itemSheet, 0, 0);
		candle = getItemAt(itemSheet, 1, 0);
		cherry = getItemAt(itemSheet, 2, 0);
		apple = getItemAt(itemSheet, 3, 0);
		muffin = getItemAt(itemSheet, 4, 0);
		flower = getItemAt(itemSheet, 5, 0);
		money_coins = getItemAt(itemSheet, 6, 0);
		money_notes = getItemAt(itemSheet, 7, 0);

		wood_horizontal = getTileAt(tileSheet, 0, 0);
		
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
	public static TextureRegion getTileAt(Texture spritesheet, int x, int y) {
		return new TextureRegion(
			spritesheet,
			x * TILE_SIZE,
			y * TILE_SIZE,
			TILE_SIZE,
			TILE_SIZE
		);
	}

	/**
	 * Get the character resources for a specific character.
	 * @param character
	 * @return character resources
	 */
	public static CharacterSprites getCharacterResources(Character character) {
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

	/**
	 * Extracts a button texture from a button sprite sheet.
	 * @param spritesheet The sheet containing the button sprite.
	 * @param x The X position of the button sprite within the sheet.
	 * @param y The Y position of the button sprite within the sheet.
	 * @return The button sprite texture.
	 */
	private static TextureRegion getItemAt(Texture spritesheet, int x, int y) {
		return new TextureRegion(
			spritesheet,
			x * ITEM_SIZE,
			y * ITEM_SIZE,
			ITEM_SIZE,
			ITEM_SIZE
		);
	}
}
