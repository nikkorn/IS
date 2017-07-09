package com.itemshop.character.walking;

import com.badlogic.ashley.core.Component;

/**
 * An entity's walking component describing how it should respond to changes in walking state.
 */
public class WalkComponent implements Component {

	/** Flag which defines whether the player is walking. */
	public boolean isWalking = false;

	/** Triggered when the entity starts walking. */
	public WalkingAction onStart;

	/** Triggered when the entity changes direction while walking. */
	public WalkingAction onDirectionChange;

	/** Triggered when the entity stops walking. */
	public WalkingAction onStop;
}
