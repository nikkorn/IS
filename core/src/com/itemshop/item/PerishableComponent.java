package com.itemshop.item;

import com.badlogic.ashley.core.Component;

/**
 * A component that defines a perishable entity.
 */
public class PerishableComponent implements Component {
	
	/** The rate of quality loss (points per day). */
	public int qualityLossPerDay;
	
	/**
	 * Create a new instance of PerishableComponent.
	 */
	public PerishableComponent(int qualityLossPerDay) {
		this.qualityLossPerDay = qualityLossPerDay;
	}
}
