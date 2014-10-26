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
		window.addMouseWheelListener(this);
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
