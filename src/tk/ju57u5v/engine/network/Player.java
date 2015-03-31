package tk.ju57u5v.engine.network;

import java.util.ArrayList;

import tk.ju57u5v.engine.components.Entity;
import tk.ju57u5v.engine.components.GameObject;

public class Player {

	/**
	 * ID des Spielers
	 */
	private int playerID = 0;

	/**
	 * Alle Enities, die dem Spieler geh�ren
	 */
	private ArrayList<Entity> entities = new ArrayList<Entity>();

	/**
	 * Alle GameObjects, die dem Spieler geh�ren
	 */
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	/**
	 * Name des Spielers
	 */
	private String name;

	// Methoden
	/**
	 * Gibt die Spieler-ID zur�ck
	 * 
	 * @return ID des Spielers
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * Setzt die Spieler-ID
	 * 
	 * @param playerEntityID
	 *            ID des Spielers
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	/**
	 * Gibt die ArrayList mit allen Enities des Spielers zur�ck.
	 * 
	 * @return Enities des Spielers
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * Gibt die ArrayList mit allen GameObjects des Spielers zur�ck.
	 * 
	 * @return GameObjects des Spielers
	 */
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	/**
	 * Gibt des Namen des Spielers zur�ck
	 * 
	 * @return Name des Spielers
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Namen des Spielers
	 * 
	 * @param name
	 *            Name des Spielers
	 */
	public void setName(String name) {
		this.name = name;
	}

}
