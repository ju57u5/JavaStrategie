package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameObject extends Position {

	/**
	 * Zugriff auf das Spiel
	 */
	protected Game game;

	/**
	 * Animation Manager des GameObjects
	 */
	protected AnimationManager animationManager = new AnimationManager();

	// Methoden
	/**
	 * 
	 * @param game
	 */
	public GameObject(Game game) {
		this.game = game;
	}

	/**
	 * Setzt die Position des GameObjects
	 */
	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		game.kamera.setRelativPostion(this);
	}

	/**
	 * Setzt die x-Position des GameObjects
	 */
	@Override
	public void setX(int x) {
		super.setX(x);
		game.kamera.setRelativPostion(this);
	}

	/**
	 * Setzt die y-Position des GameObjects
	 */
	@Override
	public void setY(int y) {
		super.setY(y);
		game.kamera.setRelativPostion(this);
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
	 */
	public void render(Graphics2D g) {

	}

	/**
	 * Entlädt das GameObject
	 */
	public void unload() {
		game.gameRunner.renderer.removeGameObject(this);
	}

	/**
	 * Initialisiert das GameObject
	 */
	protected void initialise() {
		game.gameRunner.renderer.registerGameObject(this);
	}
}
