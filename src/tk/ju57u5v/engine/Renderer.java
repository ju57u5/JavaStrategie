package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Rendert alle Resourcen und Verwaltet zu rendernde Objecte.
 * @author Justus
 *
 */
public class Renderer {

	protected ArrayList<Entity> entities = new ArrayList<Entity>();
	protected ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	private boolean render = false;
	private boolean update = false;
	private Game game;

	public Renderer(Game game) {
		this.game = game;
	}

	/**
	 * Registriert ein Entity und gibt den Index des Entities zurück.
	 * @param pEntity
	 */
	public int registerEntity(Entity pEntity) {
		entities.add(pEntity);
		game.kamera.setRelativPostion(pEntity);
		return entities.size()-1;
	}

	/**
	 * Updated die Entities.
	 */
	public void update() {
		if (update) {
			updateEntities();
		}
	}
	
	/**
	 * Rendert die Entities und GameObjects, die Registriert wurden.
	 * @param g
	 */
	public void render(Graphics2D g) {
		if (render) {
			renderEntities(g);
			renderGameObjects(g);
		}
	}

	private void updateEntities() {
		for (int c = 0; c < entities.size();c++) {
			entities.get(c).animationManager.setTick(game.gameRunner.ticks);
			entities.get(c).updateMovement();
			entities.get(c).update();
		}
	}

	private void renderEntities(Graphics2D g) {
		for (int c = 0; c < entities.size();c++) {
			if (game.kamera.isRenderNeeded(entities.get(c))) {
				entities.get(c).render(g);
			}
		}
	}

	private void renderGameObjects(Graphics2D g) {
		for (int c = 0; c < gameObjects.size();c++) {
			gameObjects.get(c).render(g);
		}
	}

	/**
	 * Soll gerendert werden?
	 * @param render
	 */
	public void doRender(boolean render) {
		this.render = render;
	}

	/**
	 * Soll ein Update gemacht werden?
	 * @param update
	 */
	public void doUpdate(boolean update) {
		this.update = update;
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
}
