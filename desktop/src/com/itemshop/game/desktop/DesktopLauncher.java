package com.itemshop.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.itemshop.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		new LwjglApplication(new Game(), buildConfiguration(arg));
	}
	
	private static LwjglApplicationConfiguration buildConfiguration (String[] arguments) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Item Shoppe";
		config.addIcon("images/icons/icon-16.png", FileType.Internal);
		
		// Define argument defaults.
		config.width = 1024;
		config.height = 768;
		config.fullscreen = false;
		config.resizable = true;

		// Process arguments.
		int current = 0;
		while (current < arguments.length ) {
			switch(arguments[current]) {
				case "-w" :
				case "--width" :
					// Could throw "Out of bounds" exception.
					config.width = Integer.parseInt(arguments[current + 1]);
					current += 2;
					break;
					
				case "-h" :
				case "--height" :
					// Could throw "Out of bounds" exception.
					config.height = Integer.parseInt(arguments[current + 1]);
					current += 2;
					break;
					
				case "-f" :
				case "--fullscreen" :
					// Prevent resizing when full screen.
					config.fullscreen = true;
					config.resizable = false;
					current += 1;
					break;
					
				case "-r" :
				case "--resizable" :
					// Prevent full screen when resizing.
					config.resizable = true;
					config.fullscreen = false;
					current += 1;
					break;
					
				default:
					throw new IllegalArgumentException("Invalid Command-Line Arguments.");
			}
		}

		return config;
	}
}
