package com.itemshop.input;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.ashley.core.Component;

/**
 * An entity's input component describing how it should respond to key presses etc.
 */
public class KeyboardComponent implements Component {
	
	/** The map of input codes to actions. */
	public Map<Integer, InputAction> actions;
	
	/**
	 * Constructs the input component instance.
	 */
	public KeyboardComponent() {
		actions = new HashMap<Integer, InputAction>();
	}
}
