package tk.ju57u5v.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import tk.ju57u5v.engine.Entity;
import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.TwoDMath;

public class StrategieGame extends Game implements MouseWheelListener,MouseListener,tk.ju57u5v.engine.input.MouseListener {

	Dorfzentrum dorfzentrum;
	
	public StrategieGame() {
		super();
		gameRunner = new GameRunner(this);
		player = new Player();
		
		window.addMouseWheelListener(this);
		window.addMouseListener(this);
		initalizeGame();
		dorfzentrum = new Dorfzentrum(this);
		dorfzentrum.setPosition(100, 100);
		Dorfzentrum dorfzentrum2 = new Dorfzentrum(this);
		dorfzentrum2.setPosition(300, 300);
		mouseHandler.addMouseListener(this);
	}

	public static void main(String[] args) {
		new StrategieGame();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		kamera.setWidth(kamera.getWidth() - e.getWheelRotation() * 10);
		kamera.setHeight(kamera.getHeight() - e.getWheelRotation() * 10);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		((Player) player).activeGroup.moveTo(kamera.toRealX(e.getX()), kamera.toRealY(e.getY()));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousedrag(int startx, int starty, int endx, int endy) {
		//((Player) player).activeGroup.clear();
		for (int c=0;c<gameRunner.getRenderer().getEntities().size();c++) {
			Entity e = gameRunner.getRenderer().getEntities().get(c);
			if (TwoDMath.isRectInRect(e.getX(), e.getY(), e.getWidth(), e.getHeight(), kamera.toRealX(startx), kamera.toRealY(starty), endx-startx, endy-starty)) {
				((Player) player).activeGroup.addUnit((Unit) e);
			}
		}
	}

	@Override
	public void mouseclick(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
