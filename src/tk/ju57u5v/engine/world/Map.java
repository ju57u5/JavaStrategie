package tk.ju57u5v.engine.world;

import java.util.ArrayList;

import tk.ju57u5v.engine.Entity;
import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.GameObject;

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
	 * Verknüpfung zur Hauptklasse
	 */
	protected Game game;

	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public Map(Game game) {
		this.game = game;
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
