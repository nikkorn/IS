package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.Entity;
import com.itemshop.schedule.Activity;
import com.itemshop.speech.SpeechComponent;

/**
 * A simple talking activity.
 */
public class TalkActivity extends Activity {
	
	/** The speech. */
	private String speech;
	
	/** The talker. */
	private Entity talker;
	
	/**
	 * Create a new instance of the TalkActivity class.
	 * @param talker
	 * @param engine
	 * @param speech
	 */
	public TalkActivity(Entity talker, String speech) {
		this.talker = talker;
		this.speech = speech;
	}
	
	@Override
	public void onBegin() {
		talker.add(new SpeechComponent(speech));
	}
	
	@Override
	public void perform() {

		// Say something!
		System.out.println(speech);
		
		// We are done!
		this.finish();
	}
	
	@Override
	public void onEnd() {} 
	
	@Override
	public void onInterrupt() {}
}
