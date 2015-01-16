package tk.ju57u5v.game;

import tk.ju57u5v.engine.Entity;
import tk.ju57u5v.engine.Game;

public class Unit extends Entity{
	
	private int speed=1;
	private int health=10;
	private int damage=1;
	private int attackSpeed=1;
	private boolean alive=false;
	
	public Unit(Game game) {
		super(game);
	}
	
	public void setHealth(int health) {
		this.health=health;
	}
	
	public void setSpeed(int speed) {
		this.speed=speed;
	}
	
	public void setDamage(int damage) {
		this.damage=damage;
	}
	
	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed=attackSpeed;
	}

	public int getSpeed() {
		return speed;
	}

	public int getHealth() {
		return health;
	}

	public int getDamage() {
		return damage;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}
	
	public void moveTo(int x, int y) {
		this.moveTo(x, y, speed);
	}
}
