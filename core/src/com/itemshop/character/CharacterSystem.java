package com.itemshop.character;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.itemshop.game.assets.Assets;
import com.itemshop.movement.MovementTileTransitionComponent;
import com.itemshop.render.AnimationComponent;
import com.itemshop.render.TextureComponent;

/**
 * Handles processing of each Character entity.
 */
public class CharacterSystem extends IteratingSystem {
	
	/** Component mappers to get components from entities. */
    private ComponentMapper<CharacterComponent> characterMapper;
    private ComponentMapper<FacingDirectionComponent> facingDirectionMapper;
    private ComponentMapper<MovementTileTransitionComponent> tileTransitionMapper;
    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<AnimationComponent> animationMapper;
    
    /**
	 * Constructs the character system instance.
	 */
	public CharacterSystem() {
		super(Family.all(CharacterComponent.class).get());

		// Create the componentMappers.
		facingDirectionMapper = ComponentMapper.getFor(FacingDirectionComponent.class);
		tileTransitionMapper  = ComponentMapper.getFor(MovementTileTransitionComponent.class);
		characterMapper       = ComponentMapper.getFor(CharacterComponent.class);
		textureMapper         = ComponentMapper.getFor(TextureComponent.class);
		animationMapper       = ComponentMapper.getFor(AnimationComponent.class);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		
		// Get the character component.
		CharacterComponent character       = characterMapper.get(entity);
		FacingDirectionComponent direction = facingDirectionMapper.get(entity);
		
		// If we have a tile transition component then we are walking.
		if(tileTransitionMapper.has(entity)) {
			//
		} else {
			
		}
		
		// TODO Review!
		textureMapper.get(entity).region = Assets.getCharacterResources(character.character).getTexture(direction.direction).region;
	}
}
