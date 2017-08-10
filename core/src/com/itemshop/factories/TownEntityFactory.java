package com.itemshop.factories;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.itemshop.factories.tiles.*;
import com.itemshop.tiles.Area;
import com.itemshop.tiles.AreaComponent;

/**
 * Factory for producing entities specific to the game town. 
 */
public class TownEntityFactory {
	/** The RNG to use throughout the factory. */
	private static Random rng = new Random(12345);
	
	@SuppressWarnings("serial")
	private static Map<Integer, TileFactory> tileColorMap = new HashMap<Integer, TileFactory>() {{
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
		put(-6075996, new BinFactory());
		put(-8355840, new InfoBoardFactory());
		put(-16711681, new FountainFactory());
		put(-32985, new MailBoxFactory());
		put(-9048, new ChestFactory());
	}};
	
	@SuppressWarnings("serial")
	private static Map<Integer, Area> areaColorMap = new HashMap<Integer, Area>() {{
		put(-9399618, Area.SHOP);
		put(-14066, Area.STOREROOM);
		put(-20791, Area.SHOP_HOUSE);
		put(-6694422, Area.TOWN_SQUARE);
		put(-4621737, Area.BUILDING_1);
		put(-3620889, Area.BUILDING_2);
		put(-4856291, Area.BUILDING_3);
		put(-32985, Area.BUILDING_4);
		put(-6075996, Area.BUILDING_5);
		put(-3164279, Area.BUILDING_6);
		put(-16777216, Area.UNKNOWN);
	}};
	
	/** Factory to use when it is not possible to determine what the sprite should be. */
	private static TileFactory defaultFactory = new UnknownFactory();
	
	/**
	 * Creates the town.
	 * @param engine The engine to add the entity to.
	 */
	public static void createTown(Engine engine) {

		// For now, the town and area maps are stored as a .png image where a pixel maps to a tile.
		BufferedImage tileMap = null, areaMap = null;
		try {
			tileMap = ImageIO.read(Gdx.files.internal("images/map/town_map.png").file());
			areaMap = ImageIO.read(Gdx.files.internal("images/map/town_area_map.png").file());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Map the pixels to tile entities.
		for (int x = 0; x < tileMap.getWidth(); x++) {
			
			int colorAbove = 0;
			
			for (int y = 0; y < tileMap.getHeight(); y++) {
				
				// Get the pixel colours which represent the tile type and area.
				int tileColor = tileMap.getRGB(x, tileMap.getHeight() - (1 + y));
				int areaColor = areaMap.getRGB(x, tileMap.getHeight() - (1 + y));
				
				// Add tile entity characteristics based on type.
				TileFactory factory = getFactory(tileColor);
				Entity tile         = factory.create(engine, rng, x, y, colorAbove == tileColor);
				
				// Every tile should have an area component.
				tile.add(new AreaComponent(getArea(areaColor)));
				
				colorAbove = tileColor;
			}
		}
	}
	
	/**
	 * Gets the right tile factory for the specified colour, or the default if not found.
	 * @param color The colour from the map.
	 * @return The correct tile factory.
	 */
	private static TileFactory getFactory(int color) {
		if (tileColorMap.containsKey(color)) {
			return tileColorMap.get(color);
		} else {
			System.out.print("Unknown tile color in map: " + color + " - ");
			printColourRGB(color);
			return defaultFactory;
		}
	}
	
	/**
	 * Gets the right area for the specified colour, or the default if not found.
	 * @param color The colour from the map.
	 * @return The correct area.
	 */
	private static Area getArea(int color) {
		if (areaColorMap.containsKey(color)) {
			return areaColorMap.get(color);
		} else {
			System.out.print("Unknown area color in map: " + color + " - ");
			printColourRGB(color);
			return Area.UNKNOWN;
		}
	}
	
	/**
	 * Print the individual RGBvalues of a colour. 
	 * @param rgb The colour rgb value.
	 */
	private static void printColourRGB(int rgb) {
		Color color = new Color(rgb);
		System.out.println("R: " + color.getRed() + " G: " + color.getGreen() + " B: " + color.getBlue());
	}
}
