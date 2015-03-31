package tk.ju57u5v.engine.world;

import java.util.ArrayList;

import tk.ju57u5v.engine.components.Entity;
import tk.ju57u5v.engine.components.GameObject;

public class Map {

	/**
	 * Map Name
	 */
	static String mapName;

	/**
	 * Entitys der Map
	 */
	protected ArrayList<Entity> mapEntities = new ArrayList<Entity>();

	/**
	 * GameObjects der Map
	 */
	protected ArrayList<GameObject> mapGameObjects = new ArrayList<GameObject>();

	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public Map() {
	}

	/**
	 * Wird beim Laden der Map ausgeführt
	 */
	public void onLoad() {

	}

	/**
	 * Wird beim entladen der Map ausgeführt
	 */
	public void onUnLoad() {

	}
}
