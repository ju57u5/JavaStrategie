package tk.ju57u5v.game;

import java.awt.Graphics;

import tk.ju57u5v.engine.Entity;
import tk.ju57u5v.engine.Game;

public class Dorfzentrum extends Entity {
	
	
	
	public Dorfzentrum(Game game) {
		super(game);
		
		game.getGameRunner().getRenderer().registerEntity(this);
		
		animationManager.createAnimation("walk", new String[]{"katze1","katze2","katze3","katze4"}, 7);
		animationManager.createAnimation("stand", new String[]{"katze1"}, 1);
		animationManager.selectAnimation("walk");
		game.getResourceManager().setDimensionsFromResource("katze3", this);
	}
	
	@Override
	public void render (Graphics g) {
			g.drawImage( game.getResourceManager().getResource(getAnimationQuery()), getRelativX(), getRelativY(), null);
	}
	
	@Override
	public void update() {
		if (isMoving()) {
			animationManager.selectAnimation("walk");
		} else {
			animationManager.selectAnimation("stand");
		}
	}
}
