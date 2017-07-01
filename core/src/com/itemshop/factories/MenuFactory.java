package com.itemshop.factories;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.itemshop.game.Assets;
import com.itemshop.input.InputAction;
import com.itemshop.input.KeyboardComponent;
import com.itemshop.input.MouseComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.state.State;
import com.itemshop.state.StateSystem;
import com.itemshop.ui.ScreenPositionComponent;

/**
 * Factory for creating the menu.
 */
public class MenuFactory {

	private static final float BUTTON_WIDTH = 0.5f;
	private static final float BUTTON_HEIGHT = 0.2f;

	/**
	 * Creates the menu.
	 * @param engine The engine to add the entities to.
	 */
	public static void create(Engine engine) {
		
		// Get the button textures.
		TextureComponent normal = new TextureComponent(Assets.play);
		TextureComponent hover = new TextureComponent(Assets.play_hover);
		TextureComponent click = new TextureComponent(Assets.play_click);
		
		Entity entity = new Entity();

		// Position the button and give it an initial texture.
		entity.add(new ScreenPositionComponent(0.5f - (BUTTON_WIDTH / 2), 0.5f - (BUTTON_HEIGHT / 2), BUTTON_WIDTH, BUTTON_HEIGHT));
		entity.add(normal);
		
		// Create the action to start the game.
		InputAction startGame = (deltaTime) -> {
			engine.getSystem(StateSystem.class).setState(State.Game);
		};
		
		// Set up the texture swapping and start game on click..
		MouseComponent mouseHandler = new MouseComponent();
		mouseHandler.onBeginHover = (deltaTime, deltaPosition) -> {
			entity.remove(TextureComponent.class);
			entity.add(hover);
		};
		mouseHandler.onEndHover = (deltaTime, deltaPosition) -> {
			entity.remove(TextureComponent.class);
			entity.add(normal);
		};
		mouseHandler.onBeginClick = (deltaTime, deltaPosition) -> {
			entity.remove(TextureComponent.class);
			entity.add(click);
		};
		mouseHandler.onEndClick = (deltaTime, deltaPosition) -> {
			startGame.perform(deltaTime);
		};
		entity.add(mouseHandler);
		
		// Allow enter and space to start the game as well.
		KeyboardComponent keyboardHandler = new KeyboardComponent();
		keyboardHandler.actions.put(Input.Keys.ENTER, startGame);
		keyboardHandler.actions.put(Input.Keys.SPACE, startGame);
		entity.add(keyboardHandler);
		
		engine.addEntity(entity);
	}
}
