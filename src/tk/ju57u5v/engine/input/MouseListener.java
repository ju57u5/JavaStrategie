package tk.ju57u5v.engine.input;

import java.awt.event.MouseEvent;

public interface MouseListener {

	/**
	 * Wird aufgerufen wenn die Maus gedraggt wurde
	 * 
	 * @param startx
	 * @param starty
	 * @param endx
	 * @param endy
	 * @param e
	 */
	public void mousedrag(int startx, int starty, int endx, int endy, MouseEvent e);

	/**
	 * Wird aufgerufen, wenn die Maus geklickt wurde
	 * 
	 * @param x
	 * @param y
	 * @param e
	 */
	public void mouseclick(int x, int y, MouseEvent e);
}
