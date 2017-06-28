package com.itemshop.game.assets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.itemshop.character.FacingDirection;
import com.itemshop.character.ISCharacter;
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
	public CharacterResources(ISCharacter character) {
		
		// Get the correct directory based on the character.
		String dir = character.name().toLowerCase();
		
		// Load the relevant assets.
		stationary_up    = new TextureComponent(new Texture(Gdx.files.internal("images/characters/"+ dir +"/stationary_up.png")));
		stationary_down  = new TextureComponent(new Texture(Gdx.files.internal("images/characters/"+ dir +"/stationary_down.png")));
		stationary_left  = new TextureComponent(new Texture(Gdx.files.internal("images/characters/"+ dir +"/stationary_left.png")));
		stationary_right = new TextureComponent(new Texture(Gdx.files.internal("images/characters/"+ dir +"/stationary_right.png")));
		
		// TODO Initialise the animation components.
	}
	
	/**
	 * Get a visual component for this character, either an animation or texture.
	 * @param direction
	 * @param isWalking
	 * @return visual component
	 */
	public Component getVisualComponent(FacingDirection direction, boolean isWalking) {
		switch(direction) {
			case DOWN:
				return isWalking ? walking_down : stationary_down;
			case LEFT:
				return isWalking ? walking_left : stationary_left;
			case RIGHT:
				return isWalking ? walking_right : stationary_right;
			case UP:
				return isWalking ? walking_up : stationary_up;
			default:
				return stationary_down;
		}
	}
}
