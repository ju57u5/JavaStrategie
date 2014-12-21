package tk.ju57u5v.game;

import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.GameObject;

public class Haus extends GameObject{

	private int health=10;
	
	
	public Haus(Game game) {
		super(game);
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}
	
	
}
