package tk.ju57u5v.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import tk.ju57u5v.engine.Game;

public class StrategieGame extends Game implements MouseWheelListener,MouseListener {

	Dorfzentrum dorfzentrum;
	
	public StrategieGame() {
		super();
		gameRunner = new GameRunner(this);
		dorfzentrum = new Dorfzentrum(this);
		dorfzentrum.setPosition(100, 100);
		Dorfzentrum dorfzentrum2 = new Dorfzentrum(this);
		dorfzentrum2.setPosition(300, 300);
		
		window.addMouseWheelListener(this);
		window.addMouseListener(this);
		kamera.setPosition(300, 300);
		initalizeGame();
		
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
		dorfzentrum.moveTo(kamera.toRealX(e.getX()), kamera.toRealY(e.getY()),1);
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
}
