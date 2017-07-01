package com.itemshop.input;

import com.badlogic.ashley.core.Component;

/**
 * An entity's input component describing how it should respond to mouse interaction.
 */
public class MouseComponent implements Component {

	/** Whether the entity is being clicked. */
	public boolean isClicked;
	
	/** Triggered when the click starts. */
	public MouseInputAction onBeginClick;

	/** Triggered while the click is happening. */
	public MouseInputAction onClicking;

	/** Triggered when the click is released. */
	public MouseInputAction onEndClick;

	/** Whether the entity is being hovered over. */
	public boolean isHovered;

	/** Triggered when the mouse moves over the entity. */
	public MouseInputAction onBeginHover;

	/** Triggered while the mouse over the entity. */
	public MouseInputAction onHovering;

	/** Triggered when the mouse moves away from the entity. */
	public MouseInputAction onEndHover;
}
