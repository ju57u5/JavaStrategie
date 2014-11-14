package tk.ju57u5v.game;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import tk.ju57u5v.engine.Game;

public class StrategieGame extends Game implements MouseWheelListener {

	public StrategieGame() {
		super();
		gameRunner = new GameRunner(this);
		Dorfzentrum dorfzentrum = new Dorfzentrum(this);
		dorfzentrum.setPosition(100, 100);
		Dorfzentrum dorfzentrum2 = new Dorfzentrum(this);
		dorfzentrum2.setPosition(300, 300);
		
		window.addMouseWheelListener(this);
		
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
}
