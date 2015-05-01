package tk.ju57u5v.engine.components;

import java.awt.Graphics2D;

import tk.ju57u5v.engine.animation.AnimationManager;
import static tk.ju57u5v.engine.Game.*;

public class GameObject extends Rect implements Renderable{

	/**
	 * Animation Manager des GameObjects
	 */
	protected AnimationManager animationManager = new AnimationManager();

	// Methoden
	/**
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public GameObject() {
	}

	/**
	 * Setzt die Position des GameObjects
	 */
	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x, y);
		getKamera().setRelativPostion(this);
	}
	
	@Override
	public void setPosition(Vec2 position) {
		super.setPosition(position);
		getKamera().setRelativPostion(this);
	};

	/**
	 * Setzt die x-Position des GameObjects
	 */
	@Override
	public void setX(double x) {
		super.setX(x);
		getKamera().setRelativPostion(this);
	}

	/**
	 * Setzt die y-Position des GameObjects
	 */
	@Override
	public void setY(double y) {
		super.setY(y);
		getKamera().setRelativPostion(this);
	}

	/**
	 * 
	 * @return
	 */
	protected String getAnimationQuery() {
		return animationManager.getcurrentPicture();
	}

	/**
	 * Rendert das GameObject. (sollte Überschrieben werden)
	 * 
	 * @param g
	 *            Graphics2D Object des Spiels
	 */
	public void render(Graphics2D g) {

	}

	/**
	 * Entlädt das GameObject
	 */
	public void unload() {
		getRenderer().removeGameObject(this);
	}

	/**
	 * Initialisiert das GameObject
	 */
	protected void initialise() {
		getRenderer().registerGameObject(this);
	}
	
	/**
	 * Speichert eine Animation des globalen Animationmanagers mit der query
	 * unter newQuery
	 * 
	 * @param newQuery
	 *            Query im lokalen Manager
	 * @param query
	 *            Query im globalen Manager
	 */
	protected void getSavedAnimation(String newQuery, String query) {
		animationManager.putAnimationString(newQuery, getResourceManager().getAnimation(query));
	}
}
