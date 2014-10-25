package tk.ju57u5v.engine;

public class TwoDMath {
	public static boolean isInRect (int x, int y, int rectX, int rectY, int rectWidth, int rectHeight) {
		return (x<=(rectX+rectWidth) && y<=(rectY+rectHeight) && x>=rectX && y>=rectY);
	}
}
