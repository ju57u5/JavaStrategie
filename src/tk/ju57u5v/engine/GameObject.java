package tk.ju57u5v.engine;

import java.awt.Graphics2D;
import static tk.ju57u5v.engine.Game.*;

public class GameObject extends Position {

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
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		kamera.setRelativPostion(this);
	}

	/**
	 * Setzt die x-Position des GameObjects
	 */
	@Override
	public void setX(int x) {
		super.setX(x);
		kamera.setRelativPostion(this);
	}

	/**
	 * Setzt die y-Position des GameObjects
	 */
	@Override
	public void setY(int y) {
		super.setY(y);
		kamera.setRelativPostion(this);
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
		gameRunner.renderer.removeGameObject(this);
	}

	/**
	 * Initialisiert das GameObject
	 */
	protected void initialise() {
		gameRunner.renderer.registerGameObject(this);
	}
}
