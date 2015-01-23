package tk.ju57u5v.engine.input;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import tk.ju57u5v.engine.Game;

public class MouseHandler implements MouseListener, MouseMotionListener {

	/**
	 * interne Startvariable des Drags auf der x-Achse
	 */
	private int startx = 0;

	/**
	 * interne Endvariable des Drags auf der y-Achse
	 */
	private int starty = 0;

	/**
	 * interne Endvariable des Drags auf der y-Achse
	 */
	private int endx = 0;

	/**
	 * interne Endvariable des Drags auf der y-Achse
	 */
	private int endy = 0;

	/**
	 * interne Variable eines Mouseclicks auf der x-Achse
	 */
	private int clickx = 0;
	
	/**
	 * interne Variable eines Mouseclicks auf der y-Achse
	 */
	private int clicky = 0;
	
	/**
	 * interne Variable, die bei einem Drag die aktuelle Position des Mauszeigers auf der x-Achse beschreibt
	 */
	private int currentx = 0;
	
	/**
	 * interne Variable, die bei einem Drag die aktuelle Position des Mauszeigers auf der y-Achse beschreibt
	 */
	private int currenty = 0;
	
	/**
	 * interne Variable, die angibt ob ein Drag stattfindet
	 */
	private boolean dragging = false;
	
	/**
	 * interne Variable, die angibt ob ein Drag gezeichnet wird
	 */
	private boolean drawDrag = false;
	
	/**
	 * interne Variable, die angibt ob ein Drag mit der rechten Maustaste erfasst wird
	 */
	private boolean rightDrag = true;
	
	/**
	 * interne Variable, die angibt ob ein Drag mit der linken Maustaste erfasst wird
	 */
	private boolean leftDrag = true;
	
	/**
	 * interne Variable, die angibt, mit welchem Button gedraggt wird
	 */
	int dragButton = 0;
	
	/**
	 * Verknüpfung zum Haupelement der Engine
	 */
	private Game game;
	
	/**
	 * ArrayList mit allen MouseListenern, die registriert wurden
	 */
	ArrayList<tk.ju57u5v.engine.input.MouseListener> listeners = new ArrayList<tk.ju57u5v.engine.input.MouseListener>();

	//Methoden
	/**
	 * Construktor
	 * @param game
	 */
	public MouseHandler(Game game) {
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clickx = e.getX();
		clicky = e.getY();
		notifyListeners("click", e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!dragging) {
			startx = e.getX();
			starty = e.getY();
			currentx = e.getX();
			currenty = e.getY();
			dragging = true;
			dragButton = e.getButton();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (dragging) {
			endx = e.getX();
			endy = e.getY();
			dragging = false;

			// Wenn dragging von einer Maustaste nicht erfasst werden soll
			// interpretiere es als klick
			if (!leftDrag && e.getButton() == MouseEvent.BUTTON1) {
				notifyListeners("click", e);
				return;
			}
			if (!rightDrag && e.getButton() == MouseEvent.BUTTON3) {
				notifyListeners("click", e);
				return;
			}
			notifyListeners("drag", e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Setzt den zustand des Draggens
	 * 
	 * @param dragging
	 */
	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}

	/**
	 * Fügt ein Klasse mit dem Interface MouseListener als Listener hinzu
	 * 
	 * @param listener
	 */
	public void addMouseListener(tk.ju57u5v.engine.input.MouseListener listener) {
		listeners.add(listener);
	}

	/**
	 * Benachrichtigt die Listener über einen Drag oder Klick
	 * 
	 * @param type
	 * Gibt den Typ der Benachrichtigung an: "click", bzw. "drag"
	 * @param e
	 */
	private void notifyListeners(String type, MouseEvent e) {
		for (int c = 0; c < listeners.size(); c++) {
			if (type.equals("drag")) {
				listeners.get(c).mousedrag(startx, starty, endx, endy, e);
			} else if (type.equals("click")) {
				listeners.get(c).mouseclick(clickx, clicky, e);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!leftDrag && dragButton == MouseEvent.BUTTON1) {
			return;
		}
		if (!rightDrag && dragButton == MouseEvent.BUTTON3) {
			return;
		}
		currentx = e.getX();
		currenty = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	/**
	 * Malt, wenn eingeschaltet, ein Rechteck des Drags
	 * 
	 * @param g
	 */
	public void drawDrag(Graphics2D g) {
		int firstx = startx < currentx ? startx : currentx;
		int firsty = starty < currenty ? starty : currenty;
		int secondx = startx >= currentx ? startx : currentx;
		int secondy = starty >= currenty ? starty : currenty;

		if (dragging && drawDrag) {
			g.drawRect(firstx, firsty, secondx - firstx, secondy - firsty);
		}
	}

	/**
	 * Legt fest ob ein Drag mit der rechten Maustaste erfasst werden soll.
	 * 
	 * @param rightDrag
	 */
	public void setRightDrag(boolean rightDrag) {
		this.rightDrag = rightDrag;
	}

	/**
	 * Legt fest ob ein Drag mit der linken Maustaste erfasst werden soll.
	 * 
	 * @param leftDrag
	 */
	public void setLeftDrag(boolean leftDrag) {
		this.leftDrag = leftDrag;
	}

	/**
	 * Legt fest ob ein erfasster Drag als Rechteck gezeichnet werden soll.
	 * 
	 * @param drawDrag
	 */
	public void setDrawDrag(boolean drawDrag) {
		this.drawDrag = drawDrag;
	}
}
