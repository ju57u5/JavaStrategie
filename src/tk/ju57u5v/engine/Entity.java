package tk.ju57u5v.engine;

import java.awt.Graphics;

public class Entity extends Position {

	protected Game game;
	protected AnimationManager animationManager = new AnimationManager();
	private int xMovement=0;
	private int yMovement=0;
	private int xMovementSpeed=0;
	private int yMovementSpeed=0;

	public Entity(Game game) {
		this.game = game;
	}

	@Override
	public void setPosition(int x, int y) {
		super.setX(x);
		super.setY(y);
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

	public void update() {

	}

	/**
	 * Rendert das Entity. Wenn es nicht überschrieben wird, wird eine Standartgrafik gerendert.
	 * @param g
	 */
	public void render(Graphics g) {

	}
	
	protected String getAnimationQuery() {
		return animationManager.getcurrentPicture();
	}
	
	public void moveTo(int x, int y, int speed) {
		this.xMovementSpeed=speed;
		this.xMovement=x-getX();
		this.yMovement=y-getY();
		
	}
	
	public void updateMovement() {
		if (xMovement<0) {
			//setX(getX()+movementSpeed);
		} else {
			//setX(getX()-movementSpeed);
		}
		
		if (yMovement<0) {
			//setY(movementSpeed);
		} else {
			//setY(movementSpeed);
		}
	}
}
