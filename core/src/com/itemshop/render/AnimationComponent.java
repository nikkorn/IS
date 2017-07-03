package com.itemshop.render;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * A component describing the animation to use in drawing the component.
 */
public class AnimationComponent implements Component {

	/** The animation to use in drawing the component. */
	public Animation animation;
	
	/**
	 * Create a new instance of AnimationComponent.
	 * @param animation
	 */
	public AnimationComponent(Animation animation) { this.animation = animation; }
}
