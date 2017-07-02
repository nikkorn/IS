package com.itemshop.factories;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.itemshop.factories.tiles.BookcaseFactory;
import com.itemshop.factories.tiles.CobbleWallFactory;
import com.itemshop.factories.tiles.DisplayCaseFactory;
import com.itemshop.factories.tiles.DoorFactory;
import com.itemshop.factories.tiles.GrassFactory;
import com.itemshop.factories.tiles.PathFactory;
import com.itemshop.factories.tiles.TableFactory;
import com.itemshop.factories.tiles.TileFactory;
import com.itemshop.factories.tiles.TillFactory;
import com.itemshop.factories.tiles.UnknownFactory;
import com.itemshop.factories.tiles.WallFactory;
import com.itemshop.factories.tiles.WaterFactory;
import com.itemshop.factories.tiles.WaterfallFactory;
import com.itemshop.factories.tiles.WoodFactory;

/**
 * Factory for producing entities specific to the game town. 
 */
public class TownEntityFactory {
	/** The RNG to use throughout the factory. */
	private static Random rng = new Random(12345);
	
	@SuppressWarnings("serial")
	static Map<Integer, TileFactory> colorMap = new HashMap<Integer, TileFactory>() {{
		put(-9590228, new GrassFactory());
		put(-2436002, new PathFactory());
		put(-9080479, new WallFactory());
		put(-2970983, new WoodFactory());
		put(-9583926, new WaterFactory());
		put(-10912306, new WaterfallFactory());
		put(-8022623, new CobbleWallFactory());
		put(-11646386, new DoorFactory());
		put(-12311500, new BookcaseFactory());
		put(-3127736, new DisplayCaseFactory());
		put(-2167082, new TableFactory());
		put(-13618067, new TillFactory());
	}};
	
	static TileFactory defaultFactory = new UnknownFactory();
	
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
			int colorAbove = 0;
			for (int y = 0; y < map.getHeight(); y++) {
				int color = map.getRGB(x, map.getHeight() - (1 + y));
				
				// Add tile entity characteristics based on type.
				TileFactory factory = getFactory(color);
				factory.create(engine, rng, x, y, colorAbove == color);
				
				colorAbove = color;
			}
		}
	}
	
	private static TileFactory getFactory(int color) {
		if (colorMap.containsKey(color)) {
			return colorMap.get(color);
		} else {
			System.out.println("Unknown color in map: " + color);
			return defaultFactory;
		}
	}
}
