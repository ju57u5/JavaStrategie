package tk.ju57u5v.engine.components;

public class Rect {

	/**
	 * x-Koordinate
	 */
	private int x = 0;

	/**
	 * y-Koordinate
	 */
	private int y = 0;

	/**
	 * z-Koordinate
	 */
	private int z = 0;

	/**
	 * Breite
	 */
	private int width = 0;

	/**
	 * H�he
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

	/**
	 * relative isometrische x-Koordinate
	 */
	private int relativIsoX = 0;

	/**
	 * relative isometrische y-Koordinate
	 */
	private int relativIsoY = 0;

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
	 * Gibt die x-Koordinate zur�ck
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gibt die y-Koordinate zur�ck
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gibt die relative y-Koordinate zur�ck
	 * 
	 * @return
	 */
	public int getRelativX() {
		return relativX;
	}

	/**
	 * Gibt die relative y-Koordinate zur�ck
	 * 
	 * @return
	 */
	public int getRelativY() {
		return relativY;
	}

	/**
	 * Gibt die Breite zur�ck
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gibt die H�he zur�ck
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Setzt die H�he und Breite (Dimensionen)
	 * 
	 * @param width
	 *            Breite
	 * @param height
	 *            H�he
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
	 * Setzt die H�he
	 * 
	 * @param height
	 *            H�he
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

	/**
	 * Gibt die relative isometrische x-Koordinate zur�ck
	 * 
	 * @return relative isometrische x-Koordinate
	 */
	public int getRelativIsoX() {
		return relativIsoX;
	}

	/**
	 * Gibt die relative isometrische y-Koordinate zur�ck
	 * 
	 * @return relative isometrische y-Koordinate
	 */
	public int getRelativIsoY() {
		return relativIsoY;
	}

	/**
	 * Gibt die isometrische y-Koordinate zur�ck
	 * 
	 * @return isometrische y-Koordinate
	 */
	public int getIsoX() {
		return x - y;
	}

	/**
	 * Gibt die isometrische y-Koordinate zur�ck
	 * 
	 * @return isometrische y-Koordinate
	 */
	public int getIsoY() {
		return (x + y) / 2 - z;
	}

	/**
	 * Setzt die relative isometrische x-Koordinate
	 * 
	 * @param x
	 *            relative isometrische x-Koordinate
	 */
	public void setRelativIsoX(int x) {
		relativIsoX = x;
	}

	/**
	 * Setzt die relative isometrische y-Koordinate
	 * 
	 * @param y
	 *            relative isometrische y-Koordinate
	 */
	public void setRelativIsoY(int y) {
		relativIsoY = y;
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
	 * Gibt die z-Koordinate zur�ck
	 * 
	 * @return z z-Koordinate
	 */
	public int getZ() {
		return z;
	}
}
