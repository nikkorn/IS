package com.itemshop.speech;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * A component defining something spoken by an entity.
 */
public class SpeechComponent implements Component {
	
	/** The speech box. */
	public Entity speechBox;
	
	/** The speech text. */
	public String speechText;
	
	/** The speech duration (milis). */
	public long duration;
	
	/** The display start time. */
	public long displayStartTime;
	
	/**
	 * Create a new instance of SpeechComponent.
	 * @param speech
	 */
	public SpeechComponent(String speech) {
		this(speech, 2000);
	}
	
	/**
	 * Create a new instance of SpeechComponent.
	 * @param speech
	 * @param duration
	 */
	public SpeechComponent(String speech, long duration) {
		this.speechText = speech;
		this.duration   = duration;
	}
}
