package com.itemshop.lighting;

import com.badlogic.ashley.core.Component;

/**
 * A component used to define an entity as a light source.
 */
public class LightSourceComponent implements Component {
	
	/** The tile radius of the lighting effect. */
	public int radius = 1;
	
	/** Flag defining whether the light source is lit */
	public boolean isLit = false;
	
	/**
	 * Light the light source
	 */
	public void light() {
		isLit = true;
		this.onLight.perform();
	}
	
	/**
	 * Extinguish the light source
	 */
	public void extinguish() {
		isLit = false;
		this.onExtinguish.perform();
	}
	
	/** Triggered when the light source is lit. */
	public LightSourceAction onLight;

	/** Triggered when the light source is extinguished. */
	public LightSourceAction onExtinguish;
}
