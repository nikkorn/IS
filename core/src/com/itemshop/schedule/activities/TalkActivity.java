package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.ComponentMapper;
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
	
	/** The required component mappers. */
    private static ComponentMapper<SpeechComponent> speechMapper = ComponentMapper.getFor(SpeechComponent.class);
	
	/**
	 * Create a new instance of the TalkActivity class.
	 * @param talker
	 * @param speech
	 */
	public TalkActivity(Entity talker, String speech) {
		this.talker = talker;
		this.speech = speech;
	}
	
	@Override
	public void onBegin() {
		// If the talker already has a speech component, then we need to get rid of the speech box entity it references.
		if (speechMapper.has(talker) && (speechMapper.get(talker).speechBox != null)) {
			this.getEngine().removeEntity(speechMapper.get(talker).speechBox);
		}
		// Add the new speech component.
		talker.add(new SpeechComponent(speech));
	}
	
	@Override
	public void perform() {
		// We are done!
		this.finish();
	}
	
	@Override
	public void onEnd() {} 
	
	@Override
	public void onInterrupt() {}
}
