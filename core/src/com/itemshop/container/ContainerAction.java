package com.itemshop.container;

import com.badlogic.ashley.core.Entity;

/**
 * An action that can be performed in response to an entity being added or removed from a container.
 */
public interface ContainerAction {

	 /**
     * Performs the action.
     * @param entity the entity.
     */
    void perform(Entity entity);
}
