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
	public static TextureRegion stone_cobble_wall;
	public static TextureRegion stone_cobble;
	
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
	
	public static TextureRegion unknown;
	
	/**
	 * Load assets from disk.
	 */
	public static void load () {
		Texture spritesheet = new Texture(Gdx.files.internal("images/sprites/1.png"));

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
		stone_cobble_wall = getTextureAt(spritesheet, 7, 1);
		stone_cobble = getTextureAt(spritesheet, 8, 1);

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
		slab_round = getTextureAt(spritesheet, 1, 5);
		slab_quad = getTextureAt(spritesheet, 2, 5);
		sand = getTextureAt(spritesheet, 3, 5);

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
		
		bookcase_0 = getTextureAt(spritesheet, 0, 8);
		bookcase_1 = getTextureAt(spritesheet, 1, 8);
		bookcase_2 = getTextureAt(spritesheet, 2, 8);
		bookcase_3 = getTextureAt(spritesheet, 3, 8);
		bookcase_4 = getTextureAt(spritesheet, 4, 8);
		bookcase_5 = getTextureAt(spritesheet, 5, 8);
		bookcase_6 = getTextureAt(spritesheet, 6, 8);
		bookcase_7 = getTextureAt(spritesheet, 7, 8);
		bookcase_8 = getTextureAt(spritesheet, 8, 8);
		bookcase_9 = getTextureAt(spritesheet, 9, 8);
		bookcase_10 = getTextureAt(spritesheet, 10, 8);
		bookcase_11 = getTextureAt(spritesheet, 11, 8);
		bookcase_12 = getTextureAt(spritesheet, 12, 8);
		bookcase_13 = getTextureAt(spritesheet, 13, 8);
		bookcase_14 = getTextureAt(spritesheet, 14, 8);

		cabinet_wood = getTextureAt(spritesheet, 0, 9);
		table_wood = getTextureAt(spritesheet, 1, 9);
		display_wood = getTextureAt(spritesheet, 2, 9);
		cabinet_stone = getTextureAt(spritesheet, 3, 9);
		table_stone = getTextureAt(spritesheet, 4, 9);
		display_stone = getTextureAt(spritesheet, 5, 9);
		till = getTextureAt(spritesheet, 6, 9);

		
		// Use one of the empty question marks as a placeholder if we need it.
		unknown = getTextureAt(spritesheet, 15, 0);
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
