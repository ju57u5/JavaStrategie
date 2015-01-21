package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameObject extends Position {
	
	protected Game game;
	protected AnimationManager animationManager = new AnimationManager();
	
	public GameObject (Game game) {
		this.game = game;
	}

	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		game.kamera.setRelativPostion(this);
	}

	@Override
	public void setX(int x) {
		super.setX(x);
		game.kamera.setRelativPostion(this);
	}

	@Override
	public void setY(int y) {
		super.setY(y);
		game.kamera.setRelativPostion(this);
	}
	
	protected String getAnimationQuery() {
		return animationManager.getcurrentPicture();
	}
	
	/**
	 * Rendert das GameObject. Wenn es nicht überschrieben wird, wird eine
	 * Standartgrafik gerendert.
	 * 
	 * @param g
	 */
	public void render(Graphics2D g) {

	}
	
	public void unload() {
		game.gameRunner.renderer.removeGameObject(this);
	}
	
	protected void initialise() {
		game.gameRunner.renderer.registerGameObject(this);
	}
}
