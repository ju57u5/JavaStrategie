package tk.ju57u5v.engine;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameRunner extends Thread {

	protected Game game;
	Renderer renderer;
	protected BufferStrategy strategy;
	protected BufferedImage dbImage = new BufferedImage(1200, 900, BufferedImage.TYPE_INT_BGR);
	long ticks = 0;
	
	public GameRunner(Game game) {
		this.game = game;
		renderer = new Renderer(game);
		this.start();
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double waitTime = 1000000000.0 / 60.0;
		double delta = 0;
		int updates = 0;

		while (true) {
			synchronized (getClass()) {
				long now = System.nanoTime();
				delta += (now - lastTime) / waitTime;
				lastTime = now;
				while (delta >= 1) {
					game.gameRunner.renderer.update();
					update();
					work();
					updates++;
					delta--;
					ticks++;
				}
				
				if (System.currentTimeMillis() - timer >1000) {
					timer += 1000;
					game.window.setUps(updates);
					updates=0;
				}
			}
		}
	}

	/**
	 * Wird bei jedem Update Ausgeführt; sollte überschrieben werden.
	 */
	public void work() {

	}

	public void setPause(boolean pause) {
		// this.pause=pause;
		renderer.doUpdate(!pause); // Wenn Pause an ist werden die Entitys nicht
									// geupdated.
	}

	public ArrayList<Entity> getEntities() {
		return this.renderer.entities;
	}

	public ArrayList<GameObject> getGameObjects() {
		return this.renderer.gameObjects;
	}

	public Renderer getRenderer() {
		return renderer;
	}
	
	private void update() {
		if (game.bindHandler.bindActive("+console")) {
			game.console.getFrame().setVisible(true);
		}
	}

}
