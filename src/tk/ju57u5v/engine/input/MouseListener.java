package tk.ju57u5v.engine.input;

import java.awt.event.MouseEvent;

public interface MouseListener {
	
	public void mousedrag(int startx, int starty, int endx, int endy, MouseEvent e);
	
	public void mouseclick(int x, int y, MouseEvent e);
}
