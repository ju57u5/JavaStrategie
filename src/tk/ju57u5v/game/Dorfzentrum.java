package tk.ju57u5v.game;

import java.awt.Graphics;

import tk.ju57u5v.engine.Entity;
import tk.ju57u5v.engine.Game;

public class Dorfzentrum extends Entity {
	
	
	
	public Dorfzentrum(Game game) {
		super(game);
		game.getResourceManager().loadImage("test.png", "test");
		game.getGameRunner().getRenderer().registerEntity(this);
	}
	
	@Override
	public void render (Graphics g) {
		g.drawImage( game.getResourceManager().getScaledResource("test"), getRelativX(), getRelativY(), null);
	}
}
