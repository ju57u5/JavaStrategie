package tk.ju57u5v.engine;

import java.util.ArrayList;

public class GameRunner extends Thread {

	/**
	 * Verknüpfung zur Hauptklasse
	 */
	protected Game game;

	/**
	 * Renderer des Spiels
	 */
	Renderer renderer;

	/**
	 * Gesamtanzahl der Ticks des Spiels
	 */
	long ticks = 0;

	// Methoden
	/**
	 * Constructor
	 * 
	 * @param game
	 */
	public GameRunner(Game game) {
		this.game = game;
		renderer = new Renderer(game);
		this.start();
	}

	/**
	 * <b>Main Thread</b> Updated alle Spielkomponenten
	 */
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

				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					game.window.setUps(updates);
					updates = 0;
				}
			}
		}
	}

	/**
	 * Wird bei jedem Update Ausgeführt; sollte überschrieben werden.
	 */
	public void work() {

	}

	/**
	 * Pausiert die Gameupdates
	 * 
	 * @param pause
	 */
	public void setPause(boolean pause) {
		renderer.doUpdate(!pause); // Wenn Pause an ist werden die Entitys nicht
									// geupdated.
	}

	/**
	 * Gibt alle Entitys in einer ArrayList zurück
	 * 
	 * @return
	 */
	public ArrayList<Entity> getEntities() {
		return this.renderer.entities;
	}

	/**
	 * Gibt alle GameObjects in einer ArrayList zurück
	 * 
	 * @return
	 */
	public ArrayList<GameObject> getGameObjects() {
		return this.renderer.gameObjects;
	}

	/**
	 * Gibt den Renderer zurück
	 * 
	 * @return
	 */
	public Renderer getRenderer() {
		return renderer;
	}

	/**
	 * Update Methode des Gamerunners<br>
	 * Öffnet die Console
	 */
	private void update() {
		if (game.bindHandler.bindActive("+console")) {
			game.console.getFrame().setVisible(true);
		}
	}

}
