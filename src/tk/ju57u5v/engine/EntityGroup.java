package tk.ju57u5v.engine;

import java.util.ArrayList;

import tk.ju57u5v.engine.components.Entity;

public class EntityGroup {

	/**
	 * Speicher aller Entitys der Gruppe
	 */
	ArrayList<Entity> entities = new ArrayList<Entity>();

	/**
	 * F�gt ein Entity der Gruppe hinzu
	 * 
	 * @param e
	 *            Entity zum hinzuf�gen
	 */
	public void add(Entity e) {
		entities.add(e);
	}

}
