package tk.ju57u5v.engine;

import java.awt.image.BufferedImage;

public class Kamera extends Position {

	/**
	 * Verknüpfung zur Hauptklasse
	 */
	private Game game;
	
	/**
	 * Isometrische Darstellung
	 */
	private boolean isometric=true;

	// Methoden
	/**
	 * Consturctor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
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
			setRelativPostion(entity);
		}

		for (GameObject gameObject : game.gameRunner.getGameObjects()) {
			setRelativPostion(gameObject);
		}
	}

	/**
	 * Setzt die realtive Position eines Entitys
	 * 
	 * @param pEntity
	 *            Entity
	 */
	public void setRelativPostion(Entity pEntity) {
		if (isometric) {
			pEntity.setRelativIsoX(pEntity.getIsoX() - getX());
			pEntity.setRelativIsoY(pEntity.getIsoY() - getY());
			return;
		}
		pEntity.setRelativX(pEntity.getX() - getX());
		pEntity.setRelativY(pEntity.getY() - getY());
	}

	/**
	 * Setzt die relative Position eines GameObjects
	 * 
	 * @param pObject
	 *            GameObject
	 */
	public void setRelativPostion(GameObject pObject) {
		if (isometric) {
			pObject.setRelativIsoX(pObject.getIsoX() - getX());
			pObject.setRelativIsoY(pObject.getIsoY() - getY());
			return;
		}
		pObject.setRelativX(pObject.getX() - getX());
		pObject.setRelativY(pObject.getY() - getY());
	}

	/**
	 * Prüft ob ein Entity geupdated werden muss
	 * 
	 * @param entity
	 *            Entity
	 * @return
	 */
	public boolean isRenderNeeded(Entity entity) {
		if (isometric)
			return TwoDMath.isRectInRect(entity.getRelativIsoX(), entity.getRelativIsoY(), entity.getWidth(), entity.getHeight(), 0, 0, this.getWidth(), this.getHeight());
		return (TwoDMath.isRectInRect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), this.getX(), this.getY(), this.getWidth(), this.getHeight()));
	}

	/**
	 * Prüft ob ein GameObject geupdated werden muss
	 * 
	 * @param gameObject
	 *            GameObject
	 * @return
	 */
	public boolean isRenderNeeded(GameObject gameObject) {
		if (isometric)
			return TwoDMath.isRectInRect(gameObject.getRelativIsoX(), gameObject.getRelativIsoY(), gameObject.getWidth(), gameObject.getHeight(), 0, 0, this.getWidth(), this.getHeight());
		return (TwoDMath.isRectInRect(gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight(), this.getX(), this.getY(), this.getWidth(), this.getHeight()));
	}

	/**
	 * Skaliert eine Resource anhand des Zooms der Kamera
	 * 
	 * @param resource
	 *            Resource die skaliert werden soll
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
	 *            x-Koordinate
	 * @return
	 */
	public int toRealX(int x) {
		return x + this.getX();
	}

	/**
	 * Konvertiert eine relative y-Koordinate zu einer Realen
	 * 
	 * @param y
	 *            y-Koordinate
	 * @return
	 */
	public int toRealY(int y) {
		return y + this.getY();
	}
}
