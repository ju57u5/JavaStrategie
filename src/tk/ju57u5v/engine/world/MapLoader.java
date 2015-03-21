package tk.ju57u5v.engine.world;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

import tk.ju57u5v.engine.Game;

public class MapLoader {

	/**
	 * Verknüpfung zur Hauptklasse
	 */
	private Game game;

	/**
	 * Aktuelle Map
	 */
	private Map currentMap;

	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public MapLoader(Game game) {
		this.game = game;
	}

	/**
	 * Lädt eine Map aus dem Mapfolder
	 * 
	 * @param name
	 *            Name der Map Datei
	 */
	public void loadMap(String name) {
		// Wenn eine Map geladen ist unloade sie
		if (currentMap != null)
			currentMap.onUnLoad();

		try {
			File mapFile = new File(Game.getResourceManager().getBasePath(), "maps/" + name);

			URL fileURL = mapFile.toURI().toURL();
			String jarURL = "jar:" + fileURL + "!/";
			URL urls[] = { new URL(jarURL) };

			URLClassLoader classLoader = new URLClassLoader(urls);
			Game.getCodeManager().processCFG(classLoader.getResourceAsStream("map.cfg"));
			String className = Game.getConsole().getString("mapClass");

			try {
				Constructor<?> mapConstructor;

				mapConstructor = Class.forName(className, true, classLoader).getDeclaredConstructor(Game.class);
				mapConstructor.setAccessible(true);

				currentMap = (Map) mapConstructor.newInstance(game);
				currentMap.onLoad();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Entlädt die aktuelle Map
	 */
	public void unloadMap() {
		currentMap.onUnLoad();
		currentMap = null;
	}
}
