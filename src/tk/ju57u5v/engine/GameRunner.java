package tk.ju57u5v.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class GameRunner extends Thread {
	
	protected Game game;
	private Renderer renderer; 
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
			
			render();
			
		}
	}
	
	private void render() {
		strategy = game.window.getBufferStrategy();
		if (strategy == null) {
			game.window.createBufferStrategy(4);
			return;
		}
		 do {
			do {
				Graphics g = strategy.getDrawGraphics();
				g.setColor(Color.WHITE);
				g.drawImage(dbImage, 0, 0, game.window);
				if (!pause) {
				
				
				}
				renderer.update(dbImage.getGraphics());
				work();
				g.dispose();
			} while (strategy.contentsRestored());
			strategy.show();
		 } while (strategy.contentsLost());
	}
	
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
