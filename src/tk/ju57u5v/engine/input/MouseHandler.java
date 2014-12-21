package tk.ju57u5v.engine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;



public class MouseHandler implements MouseListener {
	
	private int startx=0; 
	private int starty=0;
	private int endx=0;
	private int endy=0;
	private int clickx=0;
	private int clicky=0;
	private boolean dragging=false;
	ArrayList<tk.ju57u5v.engine.input.MouseListener> listeners = new ArrayList<tk.ju57u5v.engine.input.MouseListener>();

	@Override
	public void mouseClicked(MouseEvent e) {
		notifyListeners("click");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!dragging) {
			startx=e.getX();
			starty=e.getY();
			dragging=true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (dragging) {
			endx=e.getX();
			endy=e.getY();
			dragging=false;
			notifyListeners("drag");
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
	
	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}

	public void addMouseListener(tk.ju57u5v.engine.input.MouseListener listener) {
		listeners.add(listener);
	}
	
	private void notifyListeners(String type) {
		for (int c=0;c<listeners.size();c++) {
			if (type.equals("drag")) {
				listeners.get(c).mousedrag(startx, starty, endx, endy);
			} else if (type.equals("click")) {
				listeners.get(c).mouseclick(clickx, clicky);
			}
		}
	}
}
