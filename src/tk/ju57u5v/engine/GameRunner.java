package tk.ju57u5v.engine;

import java.util.ArrayList;



public class GameRunner extends Thread {
	
	private Renderer renderer = new Renderer();
	private boolean pause = false;
	
	public GameRunner() {
		this.start();
	}
	
	public void run() {
		if (!pause) {
			
		
		}
		renderer.update();
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
	
}
