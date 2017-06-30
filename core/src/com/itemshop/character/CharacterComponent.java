package com.itemshop.character;

import com.badlogic.ashley.core.Component;

/**
 * A component describing a Character.
 */
public class CharacterComponent implements Component {
	
	/** The character. */
	public ISCharacter character;
	
	/**
	 * Create a new instance of CharacterComponent.
	 * @param character
	 */
	public CharacterComponent(ISCharacter character) { this.character = character; }
}
