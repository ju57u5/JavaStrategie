package tk.ju57u5v.engine;

import java.util.ArrayList;

import tk.ju57u5v.engine.components.Entity;
import tk.ju57u5v.engine.components.GameObject;

public class GameRunner extends Thread {

	/**
	 * Renderer des Spiels
	 */
	protected Renderer renderer;

	/**
	 * Gesamtanzahl der Ticks des Spiels
	 */
	private long ticks = 0;

	// Methoden
	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public GameRunner() {
		renderer = new Renderer();
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
					Game.gameRunner.renderer.update();
					update();
					work();
					updates++;
					delta--;
					ticks++;
				}

				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					Game.window.setUps(updates);
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
	 *            Status der Pausierung
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
		if (Game.bindHandler.bindActive("+console")) {
			Game.console.getFrame().setVisible(true);
		}
	}

	/**
	 * Gibt die Tick-Anzahl des Gamerunners seit Start des Spiels zurück.
	 * 
	 * @return Anzahl der Ticks
	 */
	public long getTicks() {
		return ticks;
	}
}
