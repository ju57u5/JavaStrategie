package tk.ju57u5v.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.sun.java.swing.plaf.windows.resources.windows;



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
				render();
			}
		}
	}
	
	private void render() {
		/*strategy = game.window.getBufferStrategy();
		if (strategy == null) {
			game.window.createBufferStrategy(3);
			return;
		}
		
		do {
			do {
				Graphics g = strategy.getDrawGraphics();
				
				g.drawImage(dbImage, 0, 0, null);
				if (!pause) {
				
				
				}
				//renderer.update(dbImage.getGraphics());
				renderer.update(g);
				work();
				g.dispose();
				
			} while (strategy.contentsRestored());
			
			strategy.show();
		} while (strategy.contentsLost());
		Toolkit.getDefaultToolkit().sync();*/
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
