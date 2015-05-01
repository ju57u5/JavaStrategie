package tk.ju57u5v.engine.components;

import java.util.ArrayList;

public class EntityGroup {

	/**
	 * Speicher aller Entitys der Gruppe
	 */
	ArrayList<Entity> entities = new ArrayList<Entity>();

	/**
	 * Fügt ein Entity der Gruppe hinzu
	 * 
	 * @param e
	 *            Entity zum hinzufügen
	 */
	public void add(Entity e) {
		entities.add(e);
	}

}
