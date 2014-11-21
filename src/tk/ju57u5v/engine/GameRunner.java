package tk.ju57u5v.engine;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class GameRunner extends Thread {
	
	protected Game game;
	Renderer renderer; 
	private boolean pause = false;
	protected BufferStrategy strategy;
	protected BufferedImage dbImage = new BufferedImage(1200, 900, BufferedImage.TYPE_INT_BGR);
	
	public GameRunner(Game game) {
		this.game = game;
		renderer = new Renderer(game);
		this.start();
	}
	
	public void run() {
		while (true) {
			synchronized(getClass()) { 
				try {
					Thread.sleep(5);
				} catch (InterruptedException ex) {
				}
				work();
				
			}
		}
	}
	
	/**
	 * Wird bei jedem Update Ausgeführt; sollte überschrieben werden.
	 */
	public void work() {
		
	}
	
	public void setPause (boolean pause) {
		this.pause=pause;
		renderer.doUpdate(!pause); //Wenn Pause an ist werden die Entitys nicht geupdated.
	}
	
	public ArrayList<Entity> getEntities () {
		return this.renderer.entities;
	}
	
	public ArrayList<GameObject> getGameObjects () {
		return this.renderer.gameObjects;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}
	
}
