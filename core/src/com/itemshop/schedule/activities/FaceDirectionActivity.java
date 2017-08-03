package com.itemshop.schedule.activities;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.itemshop.character.FacingDirectionComponent;
import com.itemshop.movement.Direction;
import com.itemshop.schedule.Activity;

/**
 * An activity which changes a characters facing direction.
 */
public class FaceDirectionActivity extends Activity {

    /** The facing direction component mapper. */
    private static ComponentMapper<FacingDirectionComponent> facingDirectionMapper
            = ComponentMapper.getFor(FacingDirectionComponent.class);

    /** The direction to face. */
    private Direction direction;

    /** The entity. */
    private Entity entity;

    /**
     * Create a new instance of the FaceDirectionActivity class.
     * @param entity the entity the action applies to
     * @param direction the direction to face
     */
    public FaceDirectionActivity(Entity entity, Direction direction) {
        this.entity    = entity;
        this.direction = direction;
    }

    @Override
    public void onBegin() {}

    @Override
    public void perform() {
        if (facingDirectionMapper.has(entity)) {
            facingDirectionMapper.get(entity).direction = direction;
        } else {
            entity.add(new FacingDirectionComponent(direction));
        }
        this.finish();
    }

    @Override
    public void onEnd() {}

    @Override
    public void onInterrupt() {}
}

