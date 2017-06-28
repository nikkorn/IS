package com.itemshop.game.assets;

import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.itemshop.character.ISCharacter;

/**
 * Various game assets.
 */
public class Assets {
	// Character resource map.
	private static HashMap<ISCharacter, CharacterResources> characterResources = new HashMap<ISCharacter, CharacterResources>();
		
	// Tiles.
	public static Texture grass_tile;
	public static Texture grass_tile_plain;
	public static Texture grass_tile_rough;
	public static Texture path_tile;
	public static Texture wall_tile;
	public static Texture bucks_tile;
	
	// Items.
	public static Texture apple_texture;
	public static Texture banana_texture;
	public static Texture berry_texture;
	public static Texture cheese_texture;
	public static Texture muffin_texture;
	public static Texture table_texture;
	public static Texture chest_texture;
	
	/**
	 * Load assets from disk.
	 */
	public static void load () {
		// Character resources.
		for (ISCharacter character : ISCharacter.values()) {
			characterResources.put(character, new CharacterResources(character));
		}
				
		// Tiles.
		grass_tile       = new Texture(Gdx.files.internal("images/tiles/grass_tile.png"));
		grass_tile_plain = new Texture(Gdx.files.internal("images/tiles/grass_tile_plain.png"));
		grass_tile_rough = new Texture(Gdx.files.internal("images/tiles/grass_tile_rough.png"));
		path_tile        = new Texture(Gdx.files.internal("images/tiles/path_tile.png"));
		wall_tile        = new Texture(Gdx.files.internal("images/tiles/wall_tile.png"));
		bucks_tile       = new Texture(Gdx.files.internal("images/ui/bucks_tile.png"));
		
		// Items.
		apple_texture  = new Texture(Gdx.files.internal("images/items/apple.png"));
		//banana_texture = new Texture(Gdx.files.internal("images/items/apple.png"));
		//berry_texture  = new Texture(Gdx.files.internal("images/items/apple.png"));
		//cheese_texture = new Texture(Gdx.files.internal("images/items/apple.png"));
		//muffin_texture = new Texture(Gdx.files.internal("images/items/apple.png"));
		//table_texture  = new Texture(Gdx.files.internal("images/items/apple.png"));
		//chest_texture  = new Texture(Gdx.files.internal("images/items/apple.png"));
	}
	
	/**
	 * Get the character resources for a specific character.
	 * @param character
	 * @return character resources
	 */
	public static CharacterResources getCharacterResources(ISCharacter character) {
		return characterResources.get(character);
	}
}
