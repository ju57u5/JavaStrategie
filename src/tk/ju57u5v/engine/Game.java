package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Game implements KeyListener {

	protected Kamera kamera = new Kamera(this);
	protected Window window = new Window(this);
	protected ResourceManager resourceManager = new ResourceManager(this);
	protected CodeManager codeManager = new CodeManager(this);
	private Image mainBufferImage = window.createImage(1920, 1080);
	protected GameRunner gameRunner = new GameRunner(this);
	protected boolean[] pressedKeys = new boolean[1000];

	public Game() {
		
	}
	
	public void initalizeGame() {
		gameRunner.renderer.doUpdate(true);
		gameRunner.renderer.doRender(true);
	}
	
	public Graphics getMainGraphics() {
		return gameRunner.strategy.getDrawGraphics();
	}

	public Image getMainImage() {
		return mainBufferImage;
	}

	public ResourceManager getResourceManager() {
		return resourceManager;
	}

	public GameRunner getGameRunner() {
		return gameRunner;
	}

	public Window getWindow() {
		return window;
	}

	public Kamera getKamera() {
		return kamera;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		pressedKeys[e.getKeyCode()] = true;
		// kamera.setDimensions(kamera.getWidth()-1, kamera.getHeight()-1);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressedKeys[e.getKeyCode()] = false;
		// kamera.setDimensions(kamera.getWidth()-1, kamera.getHeight()-1);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean isKeyPressed(int keyCode) {
		return pressedKeys[keyCode];
	}

}
