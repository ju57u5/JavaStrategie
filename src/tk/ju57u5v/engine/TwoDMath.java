package tk.ju57u5v.engine;

import tk.ju57u5v.engine.components.Vec2;

public class TwoDMath {

	/**
	 * PRüft ob ein Punkt in einem Rechteck ist
	 * 
	 * @param x
	 *            x-Koordinate des Punktes
	 * @param y
	 *            y-Koordinate des Punktes
	 * @param rectX
	 *            x-Koordinate des Rechteckes
	 * @param rectY
	 *            y-Koordinate des Rechteckes
	 * @param rectWidth
	 *            Breite des Rechteckes
	 * @param rectHeight
	 *            Höhe des Rechteckes
	 * @return
	 */
	public static boolean isInRect(double x, double y, double rectX, double rectY, double rectWidth, double rectHeight) {
		return (x <= (rectX + rectWidth) && y <= (rectY + rectHeight) && x >= rectX && y >= rectY);
	}

	/**
	 * Prüft ob ein Rechteck in einem Rechteck ist
	 * 
	 * @param x1
	 *            x-Koordinate des 1.Rechteckes
	 * @param y1
	 *            y-Koordinate des 1.Rechteckes
	 * @param width1
	 *            Breite des 1.Rechteckes
	 * @param height1
	 *            Höhe des 1.Rechteckes
	 * @param x2
	 *            x-Koordinate des 2.Rechteckes
	 * @param y2
	 *            y-Koordinate des 2.Rechteckes
	 * @param width2
	 *            Breite des 2.Rechteckes
	 * @param height2
	 *            Höhe des 2.Rechteckes
	 * @return
	 */
	public static boolean isRectInRect(double x1, double y1, double width1, double height1, double x2, double y2, double width2, double height2) {
		return (x2 <= (x1 + width1) && (x2 + width2) >= x1 && (y1 + height1) >= y2 && y1 <= (y2 + height2));
	}

	/**
	 * Prüft ob ein String zu einem Integer konvertiert werden kann
	 * 
	 * @param str
	 *            String der Konvertiert werden soll
	 * @return
	 */
	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Kartesische in isometrische Koordinate
	 * 
	 * @param x
	 *            kartesisches x
	 * @param y
	 *            kartesisches y
	 * @return isometrisches x
	 */
	public static int toIsoX(int x, int y) {
		return x - y;
	}

	/**
	 * Kartesische in isometrische Koordinate
	 * 
	 * @param x
	 *            kartesisches x
	 * @param y
	 *            kartesisches y
	 * @return isometrisches x
	 */
	public static int toIsoY(int x, int y) {
		return (x + y) / 2;
	}

	/**
	 * Isometrische in kartesische Koordinate
	 * 
	 * @param x
	 *            kartesisches x
	 * @param y
	 *            kartesisches y
	 * @return isometrisches x
	 */
	public static int toCartX(int x, int y) {
		return (2 * y + x) / 2;
	}

	/**
	 * Isometrische in kartesische Koordinate
	 * 
	 * @param x
	 *            kartesisches x
	 * @param y
	 *            kartesisches y
	 * @return isometrisches y
	 */
	public static int toCartY(int x, int y) {
		return (2 * y - x) / 2;
	}

	/**
	 * Isometrische Position in kartesische Postition
	 * 
	 * @param pos
	 *            isometrischer Postitionsvektor
	 * @return kartesischer Positionsvektor
	 */
	public static Vec2 toCartPosition(Vec2 pos) {
		return new Vec2(toCartX(pos.getX(), pos.getY()), toCartY(pos.getX(), pos.getY()));
	}

	/**
	 * Kartesische Position in isometrische Postition
	 * 
	 * @param pos
	 *            kartesische Postitionsvektor
	 * @return isometrischer Positionsvektor
	 */
	public static Vec2 toIsoPosition(Vec2 pos) {
		return new Vec2(toIsoX(pos.getX(), pos.getY()), toIsoY(pos.getX(), pos.getY()));
	}
	
	public static boolean isRectInRect(Vec2 pos1, Vec2 dim1, Vec2 pos2, Vec2 dim2) {
		return isRectInRect(pos1.x, pos1.y, dim1.x, dim1.y, pos2.x, pos2.y, dim2.x, dim2.y);
	}
	
	public static boolean isInRect(Vec2 pos1, Vec2 pos2, Vec2 dim2) {
		return isInRect(pos1.x, pos1.y, pos2.x, pos2.y, dim2.x, dim2.y);
	}
}
