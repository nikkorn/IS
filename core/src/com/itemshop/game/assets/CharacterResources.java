package com.itemshop.game.assets;

import com.badlogic.gdx.graphics.Texture;
import com.itemshop.character.ISCharacter;
import com.itemshop.movement.Direction;
import com.itemshop.render.AnimationComponent;
import com.itemshop.render.TextureComponent;

/**
 * Assets for a specific character type.
 */
public class CharacterResources {
	
	private TextureComponent stationary_up;
	private TextureComponent stationary_right;
	private TextureComponent stationary_left;
	private TextureComponent stationary_down;
	
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
		stationary_down  = new TextureComponent(Assets.getSpriteAt(spritesheet, 0, regionYPositon));
		stationary_left  = new TextureComponent(Assets.getSpriteAt(spritesheet, 1, regionYPositon));
		stationary_up    = new TextureComponent(Assets.getSpriteAt(spritesheet, 2, regionYPositon));
		stationary_right = new TextureComponent(Assets.getSpriteAt(spritesheet, 3, regionYPositon));
		
		// TODO Initialise the animation components.
	}
	
	public TextureComponent getTexture(Direction direction) {
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
}
