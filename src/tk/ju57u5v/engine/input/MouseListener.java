package tk.ju57u5v.engine.input;

import java.awt.event.MouseEvent;

public interface MouseListener {

	/**
	 * Wird aufgerufen wenn die Maus gedraggt wurde
	 * 
	 * @param startx
	 *            Start des Drags auf der x-Achse
	 * @param starty
	 *            Start des Drags auf der y-Achse
	 * @param endx
	 *            Ende des Drags auf der x-Achse
	 * @param endy
	 *            Ende des Drags auf der y-Achse
	 * @param e
	 *            MouseEvent des MouseReleases des Drags
	 */
	public void mousedrag(int startx, int starty, int endx, int endy, MouseEvent e);

	/**
	 * Wird aufgerufen, wenn die Maus geklickt wurde
	 * 
	 * @param x
	 *            x-Koordinate des Klicks
	 * @param y
	 *            y-Koordinate des Klicks
	 * @param e
	 *            MouseEvent des Klicks
	 */
	public void mouseclick(int x, int y, MouseEvent e);
}
