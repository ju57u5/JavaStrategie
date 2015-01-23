package tk.ju57u5v.engine;

public class TwoDMath {

	/**
	 * PRüft ob ein Punkt in einem Rechteck ist
	 * 
	 * @param x
	 * @param y
	 * @param rectX
	 * @param rectY
	 * @param rectWidth
	 * @param rectHeight
	 * @return
	 */
	public static boolean isInRect(int x, int y, int rectX, int rectY, int rectWidth, int rectHeight) {
		return (x <= (rectX + rectWidth) && y <= (rectY + rectHeight) && x >= rectX && y >= rectY);
	}

	/**
	 * Prüft ob ein Rechteck in einem Rechteck ist
	 * 
	 * @param x1
	 * @param y1
	 * @param width1
	 * @param height1
	 * @param x2
	 * @param y2
	 * @param width2
	 * @param height2
	 * @return
	 */
	public static boolean isRectInRect(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
		return (x2 <= (x1 + width1) && (x2 + width2) >= x1 && (y1 + height1) >= y2 && y1 <= (y2 + height2));
	}

	/**
	 * Prüft ob ein String zu einem Integer kopiert werden sein
	 * 
	 * @param str
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
}
