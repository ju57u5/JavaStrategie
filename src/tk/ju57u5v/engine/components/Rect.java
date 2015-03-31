package tk.ju57u5v.engine.components;

public class Rect {

	/**
	 * Position des Rects
	 */
	private Vec2 position = new Vec2(0, 0);

	/**
	 * z-Koordinate
	 */
	private int z = 0;

	/**
	 * Höhe und Breite des Rects
	 */
	private Vec2 dimension = new Vec2(0, 0);

	/**
	 * Relative Positon des Rects
	 */
	private Vec2 relativePosition = new Vec2(0, 0);

	/**
	 * Relative Isometrische Position des Rects
	 */
	private Vec2 relativeIsoPosition = new Vec2(0, 0);

	// Methoden
	/**
	 * Setzt die Position
	 */
	public void setPosition(double x, double y) {
		position.x = x;
		position.y = y;
	}

	/**
	 * Setzt x-Koordinate
	 * 
	 * @param x
	 *            x-Koordinate
	 */
	public void setX(double x) {
		position.x = x;
	}

	/**
	 * Setzt y-Koordinate
	 * 
	 * @param y
	 *            y-Koordinate
	 */
	public void setY(double y) {
		position.y = y;
	}

	/**
	 * Gibt die x-Koordinate zurück
	 * 
	 * @return
	 */
	public int getX() {
		return (int) Math.floor(position.x);
	}

	/**
	 * Gibt die y-Koordinate zurück
	 * 
	 * @return
	 */
	public int getY() {
		return (int) Math.floor(position.y);
	}

	/**
	 * Gibt die relative y-Koordinate zurück
	 * 
	 * @return
	 */
	public int getRelativX() {
		return (int) Math.floor(relativePosition.x);
	}

	/**
	 * Gibt die relative y-Koordinate zurück
	 * 
	 * @return
	 */
	public int getRelativY() {
		return (int) Math.floor(relativePosition.y);
	}

	/**
	 * Gibt die Breite zurück
	 * 
	 * @return
	 */
	public int getWidth() {
		return (int) Math.floor(dimension.x);
	}

	/**
	 * Gibt die Höhe zurück
	 * 
	 * @return
	 */
	public int getHeight() {
		return (int) Math.floor(dimension.y);
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
		dimension.x=width;
		dimension.y=height;
	}

	/**
	 * Setzt die Breite
	 * 
	 * @param width
	 *            Breite
	 */
	public void setWidth(int width) {
		dimension.x = width;
	}

	/**
	 * Setzt die Höhe
	 * 
	 * @param height
	 *            Höhe
	 */
	public void setHeight(int height) {
		dimension.y = height;
	}

	/**
	 * Setzt die relative x-Koordinate
	 * 
	 * @param relativX
	 *            relative x-Koordinate
	 */
	public void setRelativX(double relativX) {
		relativePosition.x = relativX;
	}

	/**
	 * Setzt die relative y-Koordinate
	 * 
	 * @param relativY
	 *            relative y-Koordinate
	 */
	public void setRelativY(double relativY) {
		relativePosition.y = relativY;
	}

	/**
	 * Gibt die relative isometrische x-Koordinate zurück
	 * 
	 * @return relative isometrische x-Koordinate
	 */
	public int getRelativIsoX() {
		return (int) Math.floor(relativeIsoPosition.x);
	}

	/**
	 * Gibt die relative isometrische y-Koordinate zurück
	 * 
	 * @return relative isometrische y-Koordinate
	 */
	public int getRelativIsoY() {
		return (int) Math.floor(relativeIsoPosition.y);
	}

	/**
	 * Gibt die isometrische y-Koordinate zurück
	 * 
	 * @return isometrische y-Koordinate
	 */
	public int getIsoX() {
		return (int) Math.floor(position.x - position.y);
	}

	/**
	 * Gibt die isometrische y-Koordinate zurück
	 * 
	 * @return isometrische y-Koordinate
	 */
	public int getIsoY() {
		return (int) Math.floor((position.x + position.y) / 2 - z);
	}

	/**
	 * Setzt die relative isometrische x-Koordinate
	 * 
	 * @param x
	 *            relative isometrische x-Koordinate
	 */
	public void setRelativIsoX(double x) {
		relativeIsoPosition.x = x;
	}

	/**
	 * Setzt die relative isometrische y-Koordinate
	 * 
	 * @param y
	 *            relative isometrische y-Koordinate
	 */
	public void setRelativIsoY(double y) {
		relativeIsoPosition.y = y;
	}

	/**
	 * Setzt die z-Koordinate
	 * 
	 * @param z
	 *            z-Koordinate
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * Gibt die z-Koordinate zurück
	 * 
	 * @return z z-Koordinate
	 */
	public int getZ() {
		return z;
	}

	public Vec2 getPosition() {
		return position;
	}

	public void setPosition(Vec2 position) {
		this.position = position;
	}

	public Vec2 getDimension() {
		return dimension;
	}

	public void setDimension(Vec2 dimension) {
		this.dimension = dimension;
	}
	
}
