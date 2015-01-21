package tk.ju57u5v.engine.world;

import java.util.ArrayList;

import tk.ju57u5v.engine.Entity;
import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.GameObject;

public class Map {
	
	static String mapName;
	protected ArrayList<Entity> mapEntities = new ArrayList<Entity>();
	protected ArrayList<GameObject> mapGameObjects = new ArrayList<GameObject>();
	protected Game game;
	
	public Map (Game game) {
		this.game=game;
	}

	public void onLoad() {
		
	}
	
	public void onUnLoad() {
		
	}
}
