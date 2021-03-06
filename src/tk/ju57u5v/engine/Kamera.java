package tk.ju57u5v.engine;

import java.awt.image.BufferedImage;

import tk.ju57u5v.engine.components.Entity;
import tk.ju57u5v.engine.components.GameObject;
import tk.ju57u5v.engine.components.Rect;
import tk.ju57u5v.engine.components.Vec2;

public class Kamera extends Rect {

	/**
	 * Isometrische Darstellung
	 */
	private boolean isometric = true;

	// Methoden
	/**
	 * Consturctor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public Kamera() {
		setWidth(1200);
		setHeight(900);
	}

	/**
	 * Updated alle relativen Positionen der GameObjects und Entitys
	 */
	private void updateRelativPositions() {
		for (Entity entity : Game.renderer.getEntities()) {
			setRelativPostion(entity);
		}

		for (GameObject gameObject : Game.renderer.getGameObjects()) {
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
			pEntity.setRelativeIsoPosition(pEntity.getIsoPosition().minus(getPosition()));
			return;
		}
		pEntity.setRelativePosition(pEntity.getPosition().minus(getPosition()));
	}

	/**
	 * Setzt die relative Position eines GameObjects
	 * 
	 * @param pObject
	 *            GameObject
	 */
	public void setRelativPostion(GameObject pObject) {
		if (isometric) {
			pObject.setRelativeIsoPosition(pObject.getIsoPosition().minus(getPosition()));
			return;
		}
		pObject.setRelativePosition(pObject.getPosition().minus(getPosition()));
	}

	/**
	 * Pr�ft ob ein Entity geupdated werden muss
	 * 
	 * @param entity
	 *            Entity
	 * @return
	 */
	public boolean isRenderNeeded(Entity entity) {
		if (isometric) {
			return TwoDMath.isRectInRect(entity.getRelativIsoX(), entity.getRelativIsoY(), entity.getWidth(), entity.getHeight(), 0, 0, this.getWidth(), this.getHeight());
		}
		return (TwoDMath.isRectInRect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), this.getX(), this.getY(), this.getWidth(), this.getHeight()));
	}

	/**
	 * Pr�ft ob ein GameObject geupdated werden muss
	 * 
	 * @param gameObject
	 *            GameObject
	 * @return
	 */
	public boolean isRenderNeeded(GameObject gameObject) {
		if (isometric) {
			return TwoDMath.isRectInRect(gameObject.getRelativIsoX(), gameObject.getRelativIsoY(), gameObject.getWidth(), gameObject.getHeight(), 0, 0, this.getWidth(), this.getHeight());
		}
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
		double scaleX = ((double) getWidth() / (double) Game.window.getWidth());
		double scaleY = ((double) getHeight() / (double) Game.window.getHeight());

		return ResourceManager.scale(resource, resource.getType(), (int) (resource.getWidth() * scaleX), (int) (resource.getHeight() * scaleY), scaleX, scaleY);
	}

	/**
	 * Setzt die Position der Kamera
	 */
	@Override
	public void setPosition(double x, double y) {
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

	/**
	 * Konvertiert eine realative Position in eine Reale
	 * @param pos realtiver Positionsvektor
	 * @return realer Positionsvektor
	 */
	public Vec2 toRealPosition(Vec2 pos) {
		return new Vec2(pos.x + this.getPosition().x, pos.y + this.getPosition().y);
	}

	public boolean isIsometric() {
		return isometric;
	}

	public void setIsometric(boolean isometric) {
		this.isometric = isometric;
	}
}
