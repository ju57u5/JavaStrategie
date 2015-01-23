package tk.ju57u5v.engine;

import java.awt.image.BufferedImage;

public class Kamera extends Position {

	/**
	 * Verknüpfung zur Hauptklasse
	 */
	private Game game;

	// Methoden
	/**
	 * Consturctor
	 * 
	 * @param game
	 */
	public Kamera(Game game) {
		this.game = game;
		setWidth(1200);
		setHeight(900);

	}

	/**
	 * Updated alle relativen Positionen der GameObjects und Entitys
	 */
	private void updateRelativPositions() {
		for (Entity entity : game.gameRunner.getEntities()) {
			entity.setRelativX(entity.getX() - getX());
			entity.setRelativY(entity.getY() - getY());
		}

		for (GameObject gameObject : game.gameRunner.getGameObjects()) {
			gameObject.setRelativX(gameObject.getX() - getX());
			gameObject.setRelativY(gameObject.getY() - getY());
		}
	}

	/**
	 * Setzt die realtive Position eines Entitys
	 * 
	 * @param pEntity
	 */
	public void setRelativPostion(Entity pEntity) {
		pEntity.setRelativX(pEntity.getX() - getX());
		pEntity.setRelativY(pEntity.getY() - getY());
	}

	/**
	 * Setzt die relative Position eines GameObjects
	 * 
	 * @param pObject
	 */
	public void setRelativPostion(GameObject pObject) {
		pObject.setRelativX(pObject.getX() - getX());
		pObject.setRelativY(pObject.getY() - getY());
	}

	/**
	 * Prüft ob ein Entity geupdated werden muss
	 * 
	 * @param entity
	 * @return
	 */
	public boolean isRenderNeeded(Entity entity) {
		return (TwoDMath.isRectInRect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), this.getX(), this.getY(), this.getWidth(), this.getHeight()));
	}

	/**
	 * Prüft ob ein GameObject geupdated werden muss
	 * 
	 * @param entity
	 * @return
	 */
	public boolean isRenderNeeded(GameObject gameObject) {
		return (TwoDMath.isRectInRect(gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight(), this.getX(), this.getY(), this.getWidth(), this.getHeight()));
	}

	/**
	 * Skaliert eine Resource anhand des Zooms der Kamera
	 * 
	 * @param resource
	 * @return
	 */
	public BufferedImage scaleResource(BufferedImage resource) {
		double scaleX = ((double) getWidth() / (double) game.window.getWidth());
		double scaleY = ((double) getHeight() / (double) game.window.getHeight());

		return ResourceManager.scale(resource, resource.getType(), (int) (resource.getWidth() * scaleX), (int) (resource.getHeight() * scaleY), scaleX, scaleY);
	}

	/**
	 * Setzt die Position der Kamera
	 */
	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		updateRelativPositions();
	}

	/**
	 * Setzt die x-Position der Kamera
	 */
	public void setX(int x) {
		super.setX(x);
		updateRelativPositions();
	}

	/**
	 * Setzt die y-Position der Kamera
	 */
	public void setY(int y) {
		super.setY(y);
		updateRelativPositions();
	}

	/**
	 * Konvertiert eine relative x-Koordinate zu einer Realen
	 * 
	 * @param x
	 * @return
	 */
	public int toRealX(int x) {
		return x + this.getX();
	}

	/**
	 * Konvertiert eine relative y-Koordinate zu einer Realen
	 * 
	 * @param y
	 * @return
	 */
	public int toRealY(int y) {
		return y + this.getY();
	}
}
