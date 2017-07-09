package com.itemshop.game.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.itemshop.character.ISCharacter;
import com.itemshop.movement.Direction;
import com.itemshop.render.AnimationComponent;
import com.itemshop.render.TextureComponent;

/**
 * Assets for a specific character type.
 */
public class CharacterResources {
	
	private TextureComponent stationary_up;
	private TextureComponent stationary_down;
	private TextureComponent stationary_right;
	private TextureComponent stationary_left;
	
	private AnimationComponent walking_up;
	private AnimationComponent walking_right;
	private AnimationComponent walking_left;
	private AnimationComponent walking_down;
	
	/**
	 * Creates a new instance of the CharacterResources class.
	 * @param character
	 */
	public CharacterResources(ISCharacter character, Texture spritesheet) {
		
		// The oridinal of the character enum defines its vertical position in the character spritesheet.
		int regionYPositon = character.ordinal();

		// Load the relevant assets.
		stationary_up    = new TextureComponent(Assets.getTileAt(spritesheet, 0, regionYPositon));
		stationary_down  = new TextureComponent(Assets.getTileAt(spritesheet, 4, regionYPositon));
		stationary_right = new TextureComponent(Assets.getTileAt(spritesheet, 8, regionYPositon));
		stationary_left  = new TextureComponent(Assets.getTileAt(spritesheet, 12, regionYPositon));
		
		// Initialise the animation components.
		walking_up = new AnimationComponent(new Animation(1f/4f, new TextureRegion[] {
				Assets.getTileAt(spritesheet, 0, regionYPositon),
				Assets.getTileAt(spritesheet, 1, regionYPositon),
				Assets.getTileAt(spritesheet, 2, regionYPositon),
				Assets.getTileAt(spritesheet, 3, regionYPositon)
		}));
		walking_down = new AnimationComponent(new Animation(1f/4f, new TextureRegion[] {
				Assets.getTileAt(spritesheet, 4, regionYPositon),
				Assets.getTileAt(spritesheet, 5, regionYPositon),
				Assets.getTileAt(spritesheet, 6, regionYPositon),
				Assets.getTileAt(spritesheet, 7, regionYPositon)
		}));
		walking_right = new AnimationComponent(new Animation(1f/4f, new TextureRegion[] {
				Assets.getTileAt(spritesheet, 8, regionYPositon),
				Assets.getTileAt(spritesheet, 9, regionYPositon),
				Assets.getTileAt(spritesheet, 10, regionYPositon),
				Assets.getTileAt(spritesheet, 11, regionYPositon)
		}));
		walking_left = new AnimationComponent(new Animation(1f/4f, new TextureRegion[] {
				Assets.getTileAt(spritesheet, 12, regionYPositon),
				Assets.getTileAt(spritesheet, 13, regionYPositon),
				Assets.getTileAt(spritesheet, 14, regionYPositon),
				Assets.getTileAt(spritesheet, 15, regionYPositon)
		}));
	}

	/**
	 * Get the texture component for this character.
	 * @param direction
	 * @return texture component
     */
	public TextureComponent getTextureComponent(Direction direction) {
		switch(direction) {
			case DOWN:
				return stationary_down;
			case LEFT:
				return stationary_left;
			case RIGHT:
				return stationary_right;
			case UP:
				return stationary_up;
			default:
				return stationary_down;
		}
	}

	/**
	 * Get the animation component for this character.
	 * @param direction
	 * @return animation component
	 */
	public AnimationComponent getAnimationComponent(Direction direction) {
		switch(direction) {
			case DOWN:
				return walking_down;
			case LEFT:
				return walking_left;
			case RIGHT:
				return walking_right;
			case UP:
				return walking_up;
			default:
				return walking_down;
		}
	}
}
