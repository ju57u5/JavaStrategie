package tk.ju57u5v.game;

import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.GameObject;

public class Building extends GameObject{

	private int health=10;
	
	
	public Building(Game game) {
		super(game);
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}
	
	
}
