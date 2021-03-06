package com.itemshop.factories.tiles;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.itemshop.game.assets.Assets;
import com.itemshop.input.MouseComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.TextureComponent;
import java.util.Random;

/**
 * Factory for creating a town info board.
 */
public class InfoBoardFactory implements TileFactory {

    /**
     * Creates the entity.
     * @param engine The engine to add the entity to.
     * @param random The random number generator to use.
     * @param x The x position.
     * @param y The y position.
     * @param sameAbove Whether the tile above is of the same type.
     */
    public Entity create(Engine engine, Random random, int x, int y, boolean sameAbove) {
        // Create the tile entity.
        Entity entity = new Entity();

        // Add the entities components.
        entity.add(new PositionComponent(x, y));
        entity.add(new TextureComponent(Assets.town_info_board));

        // Do something when the info board is clicked on.
        MouseComponent mouseComponent = new MouseComponent();
        mouseComponent.onBeginClick = (deltaTime, deltaPosition) -> {
            System.out.println("I am the town info board!");
        };
        entity.add(mouseComponent);

        // Add the tile entity to the engine.
        engine.addEntity(entity);
        
        return entity;
    }
}