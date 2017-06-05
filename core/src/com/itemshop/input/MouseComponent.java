package com.itemshop.input;

import com.badlogic.ashley.core.Component;

/**
 * An entity's input component describing how it should respond to key presses etc.
 */
public class MouseComponent implements Component {

	/** Whether the entity is being clicked. */
	public boolean isClicked;
	
	/** Triggered when the click starts. */
	public InputAction onBeginClick;

	/** Triggered when the click is released. */
	public InputAction onEndClick;

	/** Whether the entity is being hovered over. */
	public boolean isHovered;

	/** Triggered when the mouse moves over the entity. */
	public InputAction onBeginHover;

	/** Triggered when the mouse moves away from the entity. */
	public InputAction onEndHover;
}
