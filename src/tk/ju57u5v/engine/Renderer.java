package tk.ju57u5v.engine;

import java.awt.Graphics2D;
import java.util.ArrayList;

import tk.ju57u5v.engine.gui.GuiElement;

/**
 * Rendert alle Resourcen und Verwaltet zu rendernde Objecte.
 * 
 * @author Justus
 *
 */
public class Renderer {

	/**
	 * ArrayList mit registrierten Entitys
	 */
	protected ArrayList<Entity> entities = new ArrayList<Entity>();

	/**
	 * ArrayList mit registrierten GameObjects
	 */
	protected ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	/**
	 * ArrayList mit registrierten GuiElmenten
	 */
	protected ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();

	/**
	 * Gibt an ob gerendert wird
	 */
	private boolean render = false;

	/**
	 * Gibt an ob geupdated wird
	 */
	private boolean update = false;

	/**
	 * Gibt an ob das Gui gerendert wird
	 */
	private boolean guiRender = false;

	/**
	 * Verknüpfung zur Haupklasse
	 */
	private Game game;

	// Methoden
	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public Renderer(Game game) {
		this.game = game;
	}

	/**
	 * Registriert ein Entity und gibt den Index des Entities zurück.
	 * 
	 * @param pEntity
	 *            Entity das Registriert wird
	 * @return Index des Entitys
	 */
	public int registerEntity(Entity pEntity) {
		entities.add(pEntity);
		game.kamera.setRelativPostion(pEntity);
		return entities.size() - 1;
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
	 * Rendert die Entities und GameObjects, die registriert wurden.
	 * 
	 * @param g
	 *            Graphics2D Object des Spiels
	 */
	public void render(Graphics2D g) {
		if (render) {
			renderGameObjects(g);
			renderEntities(g);
		}
		if (guiRender)
			renderGuiElements(g);
	}

	/**
	 * Updated die Entitys, die registriert wurden
	 */
	private void updateEntities() {
		for (int c = 0; c < entities.size(); c++) {
			entities.get(c).updateMovement();
			entities.get(c).update();
		}
	}

	/**
	 * Rendert die Entitys, die registriert wurden
	 * 
	 * @param g
	 *            Graphics2D Object des Spiels
	 */
	private void renderEntities(Graphics2D g) {
		for (int c = 0; c < entities.size(); c++) {
			if (game.kamera.isRenderNeeded(entities.get(c))) {
				entities.get(c).render(g);
			}
		}
	}

	/**
	 * Rendert die GameObjects, die registriert wurden
	 * 
	 * @param g
	 *            Graphics2D Object des Spiels
	 */
	private void renderGameObjects(Graphics2D g) {
		for (int c = 0; c < gameObjects.size(); c++) {
			if (game.kamera.isRenderNeeded(gameObjects.get(c))) {
				gameObjects.get(c).render(g);
			}
		}
	}

	private void renderGuiElements(Graphics2D g) {
		for (int c = 0; c < guiElements.size(); c++) {
			guiElements.get(c).render(g);
		}
	}

	/**
	 * Soll gerendert werden?
	 * 
	 * @param render
	 *            Status ob gerendert wird
	 */
	public void doRender(boolean render) {
		this.render = render;
	}

	/**
	 * Soll ein Update gemacht werden?
	 * 
	 * @param update
	 *            Status ob geupdated wird
	 */
	public void doUpdate(boolean update) {
		this.update = update;
	}

	/**
	 * Soll das Gui gerendert werden.
	 * 
	 * @param guiRender
	 */
	public void doGuiRender(boolean guiRender) {
		this.guiRender = guiRender;
	}

	/**
	 * Gibt die registrierten Entitys in einer ArrayList zurück.
	 * 
	 * @return Entities
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * Gibt die alle registrierten GameObjects in einer ArrayList zurück.
	 * 
	 * @return GameObjects
	 */
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	/**
	 * Gibt die alle registrierten GuiElmente in einer ArrayList zurück.
	 * 
	 * @return
	 */
	public ArrayList<GuiElement> getGuiElements() {
		return guiElements;
	}

	/**
	 * Entfernt das registrierte Entity e
	 * 
	 * @param e
	 *            Entity das entfernt wird
	 */
	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	/**
	 * Entfernt das registrierte GameObject g
	 * 
	 * @param g
	 *            GameObject das entfernt wird
	 */
	public void removeGameObject(GameObject g) {
		entities.remove(g);
	}

	/**
	 * Entfernt das registrierte GuiElement
	 * 
	 * @param g
	 *            GuiElement das entfernt wird
	 */
	public void removeGuiElement(GuiElement g) {
		guiElements.remove(g);
	}

	/**
	 * Registriert das Gameobject g
	 * 
	 * @param g
	 *            GameObject das registriert wird
	 */
	public void registerGameObject(GameObject g) {
		gameObjects.add(g);
	}

	/**
	 * Registriert ein GuiElement
	 * 
	 * @param g
	 *            GuiElement das registriert wird
	 */
	public void registerGuiElement(GuiElement g) {
		guiElements.add(g);
	}
}
