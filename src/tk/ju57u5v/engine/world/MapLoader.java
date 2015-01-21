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

	private Game game;
	private Map currentMap;

	public MapLoader(Game game) {
		this.game = game;
	}

	public void loadMap(String name) {
		try {
			String className = "tk.ju57u5v.oakland.Map";
			File mapFile = new File(game.getResourceManager().getBasePath(), "maps/" + name);
			JarFile mapJar = new JarFile(mapFile);

			URL fileURL = mapFile.toURI().toURL();
			String jarURL = "jar:" + fileURL + "!/";
			URL urls[] = { new URL(jarURL) };

			URLClassLoader classLoader = new URLClassLoader(urls);

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
}