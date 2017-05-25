package com.itemshop.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	public static Texture grass_tile;
	public static Texture path_tile;
	public static Texture wall_tile;
	
	/**
	 * Load assets from disk.
	 */
	public static void load () {
		grass_tile = new Texture(Gdx.files.internal("images/tiles/grass_tile.png"));
		path_tile  = new Texture(Gdx.files.internal("images/tiles/path_tile.png"));
		wall_tile  = new Texture(Gdx.files.internal("images/tiles/wall_tile.png"));
	}
}
