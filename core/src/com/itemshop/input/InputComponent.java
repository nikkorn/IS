package com.itemshop.input;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.ashley.core.Component;

public class InputComponent implements Component {
	public Map<Integer, InputAction> actions;
	public InputComponent() {
		actions = new HashMap<Integer, InputAction>();
	}
}
