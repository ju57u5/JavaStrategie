package tk.ju57u5v.engine;

import java.awt.Graphics;

public class Entity extends GameObject {

	
	
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
		super(game);
	}
	
	public void stopMovement() {
		movement = false;
	}

	public void update() {

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
	
	public boolean isMoving() {
		return movement;
	}
	
}
