package tk.ju57u5v.engine;

import java.awt.Graphics;

public class Entity extends Position {

	protected Game game;
	protected AnimationManager animationManager = new AnimationManager();
	private int xMovePosition = 0;
	private int yMovePosition = 0;
	private int movementSpeed = 2;
	private double radian = 0;
	private double currentX = 0;
	private double currentY = 0;
	private int xDifference = 0;
	private int yDifference = 0;
	private boolean movement = false;

	public Entity(Game game) {
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
	
	public void stopMovement() {
		movement = false;
	}

	public void update() {

	}

	/**
	 * Rendert das Entity. Wenn es nicht überschrieben wird, wird eine
	 * Standartgrafik gerendert.
	 * 
	 * @param g
	 */
	public void render(Graphics g) {

	}

	protected String getAnimationQuery() {
		return animationManager.getcurrentPicture();
	}

	public void moveTo(int x, int y, int speed) {
		this.movementSpeed = speed;
		this.xMovePosition = x;
		this.yMovePosition = y;
		xDifference = x - getX();
		yDifference = y - getY();
		currentX = getX();
		currentY = getY();
		radian = Math.atan2(yDifference,xDifference);
		movement = true;
	}

	public void updateMovement() {
		if (movement) {
			currentX += Math.cos(radian) * movementSpeed;
			currentY += Math.sin(radian) * movementSpeed;

			if (xDifference > 0 && currentX > xMovePosition) {
				currentX = xMovePosition;
				movement = false;
			} else if (xDifference < 0 && currentX < xMovePosition) {
				currentX = xMovePosition;
				movement = false;
			}

			if (yDifference > 0 && currentY > yMovePosition) {
				currentY = yMovePosition;
				movement = false;
			} else if (yDifference < 0 && currentY < yMovePosition) {
				currentY = yMovePosition;
				movement = false;
			}

			setPosition((int) currentX, (int) currentY);
		}
	}
	
}
