package com.itemshop.job;

import com.badlogic.ashley.core.Component;

/**
 * A component that defines the job role of an entity.
 */
public class JobComponent implements Component {

	/** The job role. */
	public Job job;
	
	/**
	 * Create a new instance of JobComponent.
	 * @param job
	 */
	public JobComponent(Job job) {
		this.job = job;
	}
}
