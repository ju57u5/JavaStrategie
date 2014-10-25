package tk.ju57u5v.game;

import tk.ju57u5v.engine.Entity;
import tk.ju57u5v.engine.Game;

public class Dorfzentrum extends Entity {
	
	
	
	public Dorfzentrum(Game game) {
		super(game);
		game.getResourceManager().loadImage("test.png", "test");
		game.getGameRunner().getRenderer().registerEntity(this);
	}
	
	public void render () {
		game.getMainGraphics().drawImage( game.getResourceManager().getScaledResource("test"), getRelativX(), getRelativY(), game.getWindow());
	}
}
