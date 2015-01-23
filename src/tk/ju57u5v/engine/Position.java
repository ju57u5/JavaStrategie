package tk.ju57u5v.engine;

public class Position {

	/**
	 * x-Koordinate
	 */
	private int x = 0;

	/**
	 * y-Koordinate
	 */
	private int y = 0;

	/**
	 * Breite
	 */
	private int width = 0;

	/**
	 * Höhe
	 */
	private int height = 0;

	/**
	 * relative x-Koordinate
	 */
	private int relativX = 0;

	/**
	 * relative y-Koordinate
	 */
	private int relativY = 0;

	// Methoden
	/**
	 * Setzt die Position
	 * 
	 * @param x
	 *            x-Koordinate
	 * @param y
	 *            y-Koordinate
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Setzt x-Koordinate
	 * 
	 * @param x
	 *            x-Koordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Setzt y-Koordinate
	 * 
	 * @param y
	 *            y-Koordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gibt die x-Koordinate zurück
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gibt die y-Koordinate zurück
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gibt die relative y-Koordinate zurück
	 * 
	 * @return
	 */
	public int getRelativX() {
		return relativX;
	}

	/**
	 * Gibt die relative y-Koordinate zurück
	 * 
	 * @return
	 */
	public int getRelativY() {
		return relativY;
	}

	/**
	 * Gibt die Breite zurück
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gibt die Höhe zurück
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Setzt die Höhe und Breite (Dimensionen)
	 * 
	 * @param width
	 *            Breite
	 * @param height
	 *            Höhe
	 */
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Setzt die Breite
	 * 
	 * @param width
	 *            Breite
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Setzt die Höhe
	 * 
	 * @param height
	 *            Höhe
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Setzt die relative x-Koordinate
	 * 
	 * @param relativX
	 *            relative x-Koordinate
	 */
	public void setRelativX(int relativX) {
		this.relativX = relativX;
	}

	/**
	 * Setzt die relative y-Koordinate
	 * 
	 * @param relativY
	 *            relative y-Koordinate
	 */
	public void setRelativY(int relativY) {
		this.relativY = relativY;
	}
}
