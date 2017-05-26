package factories;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.itemshop.game.Assets;
import com.itemshop.game.Constants;
import com.itemshop.movement.NonWalkableTileComponent;
import com.itemshop.render.PositionComponent;
import com.itemshop.render.SizeComponent;
import com.itemshop.render.TextureComponent;
import com.itemshop.tilemap.TilePositionComponent;

/**
 * Factory for producing entities specific to the game town. 
 */
public class TownEntityFactory {
	
	/**
	 * Creates the town.
	 * @param engine The engine to add the entity to.
	 */
	public static void createTown(Engine engine) {
		
		// Create the town tile entities.
		createTownTiles(engine);
	}
	
	/**
	 * Creates the town tile entities.
	 * @param engine The engine to add the entity to.
	 */
	private static void createTownTiles(Engine engine) {
		
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
				
				// Create the tile entity.
				Entity entity = new Entity();
				
				entity.add(new SizeComponent(Constants.TILE_SIZE, Constants.TILE_SIZE));
				entity.add(new TilePositionComponent(x, y));
				entity.add(new PositionComponent(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE));
				
				// Add tile entity characteristics based on type.
				addTileEntityCharacteristics(entity, map.getRGB(x, map.getHeight() - (1 + y)));
				
				// Add the tile entity to the engine.
				engine.addEntity(entity);
			}
		}
	}
	
	/**
	 * Add characteristics to tile based on its type as defined by map pixel colour.
	 * @param entity
	 * @param rgb
	 */
	private static void addTileEntityCharacteristics(Entity entity, int rgb) {
		
		// The tile characteristics are defined by its type, which is
		// in turn based on the map image pixel which represents it.
		switch (rgb) {
		
			// GRASS
			case -14771389:
				entity.add(new TextureComponent(Assets.grass_tile));
				break;
				
			// PATH
			case -206:
				entity.add(new TextureComponent(Assets.path_tile));
				break;
			
			// WALL
			case -4949926:
				entity.add(new TextureComponent(Assets.wall_tile));
				entity.add(new NonWalkableTileComponent());
				break;
		}
	}
}
