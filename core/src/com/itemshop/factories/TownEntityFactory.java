package com.itemshop.factories;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.itemshop.factories.tiles.GrassFactory;
import com.itemshop.factories.tiles.PathFactory;
import com.itemshop.factories.tiles.TileFactory;
import com.itemshop.factories.tiles.WallFactory;
import com.itemshop.game.Assets;
import com.itemshop.movement.NonWalkableTileComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.SizeComponent;
import com.itemshop.render.TextureComponent;

/**
 * Factory for producing entities specific to the game town. 
 */
public class TownEntityFactory {
	
	static Map<Integer, TileFactory> colorMap = new HashMap<Integer, TileFactory>() {{
		put(-14771389, new GrassFactory());
		put(-206, new PathFactory());
		put(-4949926, new WallFactory());
	}};
	
	/**
	 * Creates the town.
	 * @param engine The engine to add the entity to.
	 */
	public static void createTown(Engine engine) {

		// For now, the town map is stored as a .png image where a pixel maps to a tile.
		BufferedImage map = null;
		try {
			map = ImageIO.read(Gdx.files.internal("images/map/town_map.png").file());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Map the pixels to tile entities.
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				
				// Add tile entity characteristics based on type.
				int color = map.getRGB(x, map.getHeight() - (1 + y));
				TileFactory factory = colorMap.get(color);
				factory.create(engine, x, y);
			}
		}
	}
}
