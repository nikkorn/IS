package com.itemshop.item;

import com.badlogic.ashley.core.Component;

/**
 * A component that defines the quality of an entity.
 */
public class QualityComponent implements Component {
	
	/** The max quality. */
	private static int MAX_QUALITY = 100;

	/** The quality. */
	private int quality;
	
	/**
	 * Create a new instance of QualityComponent.
	 */
	public QualityComponent() {
		this.setQuality(MAX_QUALITY);
	}
	
	/**
	 * Create a new instance of QualityComponent.
	 * @param quality
	 */
	public QualityComponent(int quality) {
		this.setQuality(quality);
	}
	
	/**
	 * Get the quality.
	 * @returns quality
	 */
	public int getQuality() { return quality; }
	
	/**
	 * Set the quality.
	 * @param quality
	 */
	public void setQuality(int quality) {
		
		// The quality must be a value between 0 and the max quality.
		if (quality < 0) {
			this.quality = 0;
		} else if (quality > MAX_QUALITY) {
			this.quality = MAX_QUALITY;
		} else {
			this.quality = quality;
		}
	}
}
