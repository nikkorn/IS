package com.itemshop.schedule;

import java.util.ArrayList;

/**
 * A list of activities which handles interrupting the currently active activity (if there is one) when cleared.
 */
public class ActivityList extends ArrayList<Activity> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Clear the list of activities after interrupting the first one if it has already begun.
	 */
	@Override
	public void clear() {
		// If we have an currently processing activity ...
		if (this.size() > 0 && this.get(0).hasBegun()) {
			// ... then interrupt it.
			this.get(0).onInterrupt();
		}
		// Call through to clear the list.
		super.clear();
	}
}
