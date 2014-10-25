package tk.ju57u5v.engine;

import java.util.ArrayList;



public class GameRunner extends Thread {
	
	protected Game game;
	private Renderer renderer; 
	private boolean pause = false;
	
	public GameRunner(Game game) {
		this.game = game;
		renderer = new Renderer(game);
		this.start();
	}
	
	public void run() {
		while (true) {
			game.getMainGraphics().drawRect(0, 0, game.getMainImage().getWidth(game.window), game.getMainImage().getHeight(game.window));
			if (!pause) {
				
				
			}
			renderer.update();
			work();
			game.window.paint(game.window.getGraphics());
		}
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
