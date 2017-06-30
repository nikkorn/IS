package com.itemshop.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	private static final int SPRITE_SIZE = 16;

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
	
	public static Texture cursor;
	
	/**
	 * Load assets from disk.
	 */
	public static void load () {
		Texture spritesheet = getTexture("images/sprites/1.png");

		wood_horizontal = getTextureAt(spritesheet, 0, 0);
		wood_vertical = getTextureAt(spritesheet, 1, 0);
		wood_thick = getTextureAt(spritesheet, 2, 0);
		wood_square = getTextureAt(spritesheet, 3, 0);

		stone = getTextureAt(spritesheet, 0, 1);
		stone_top = getTextureAt(spritesheet, 1, 1);
		stone_slope_nw = getTextureAt(spritesheet, 2, 1);
		stone_slope_se = getTextureAt(spritesheet, 3, 1);
		stone_border_corner = getTextureAt(spritesheet, 4, 1);
		stone_border_side = getTextureAt(spritesheet, 5, 1);
		stone_stairs = getTextureAt(spritesheet, 6, 1);

		candle_wall = getTextureAt(spritesheet, 0, 2);
		candle_table = getTextureAt(spritesheet, 1, 2);
		plate = getTextureAt(spritesheet, 2, 2);
		apple = getTextureAt(spritesheet, 3, 2);
		dollar = getTextureAt(spritesheet, 4, 2);

		water = getTextureAt(spritesheet, 0, 3);
		water_top = getTextureAt(spritesheet, 1, 3);
		water_falling = getTextureAt(spritesheet, 2, 3);

		door = getTextureAt(spritesheet, 0, 4);
		door_wood = getTextureAt(spritesheet, 1, 4);
		door_stone = getTextureAt(spritesheet, 2, 4);
		rock = getTextureAt(spritesheet, 3, 4);
		window = getTextureAt(spritesheet, 4, 4);

		slab = getTextureAt(spritesheet, 0, 5);
		sand = getTextureAt(spritesheet, 1, 5);

		roof_top = getTextureAt(spritesheet, 0, 6);
		roof = getTextureAt(spritesheet, 1, 6);
		roof_bottom = getTextureAt(spritesheet, 2, 6);

		grass = getTextureAt(spritesheet, 0, 7);
		grass_flower = getTextureAt(spritesheet, 1, 7);
		grass_mole = getTextureAt(spritesheet, 2, 7);
		grass_pebble = getTextureAt(spritesheet, 3, 7);
		grass_medium = getTextureAt(spritesheet, 4, 7);
		grass_long = getTextureAt(spritesheet, 5, 7);
		grass_edge_left = getTextureAt(spritesheet, 6, 7);
		grass_edge_right = getTextureAt(spritesheet, 7, 7);
		
		// Use one of the empty question marks as a placeholder if we need it.
		unknown = getTextureAt(spritesheet, 0, 7);
		
		cursor = getTexture("images/sprites/cursor.png");
	}
	
	private static Texture getTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	private static TextureRegion getTextureAt(Texture spritesheet, int x, int y) {
		return new TextureRegion(
			spritesheet,
			x * SPRITE_SIZE,
			y * SPRITE_SIZE,
			SPRITE_SIZE,
			SPRITE_SIZE
		);
	}
}
