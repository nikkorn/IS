package com.itemshop.schedule.activities;

import com.itemshop.schedule.Activity;

/**
 * A simple talking activity.
 */
public class TalkActivity extends Activity {
	
	/** The speech. */
	private String speech;
	
	/**
	 * Create a new instance of the TalkActivity class.
	 * @param speech
	 */
	public TalkActivity(String speech) {
		this.speech = speech;
	}
	
	@Override
	public void onBegin() {}
	
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
