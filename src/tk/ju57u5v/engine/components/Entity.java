package tk.ju57u5v.engine.components;

import static tk.ju57u5v.engine.Game.*;

public class Entity extends GameObject implements Updatetable {
	
	/**
	 * Position zu der das Entity bewegt wird.
	 */
	private Vec2 movePosition = new Vec2(0, 0);

	/**
	 * Bewegungsvektor des Entitys
	 */
	private Vec2 velocity = new Vec2(0, 0);
	
	/**
	 * gibt an ob sich das Entity bewegt
	 */
	private boolean movement = false;

	// Methoden
	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public Entity() {
		super();
	}

	/**
	 * beendet das Movement des Entitys
	 */
	public void stopMovement() {
		movement = false;
	}

	/**
	 * Update-Methode des Entitys (sollte �berschrieben werden)
	 */
	public void update() {

	}

	/**
	 * Intitialisiert das Entity
	 */
	protected void initialise() {
		getGameRunner().getRenderer().registerEntity(this);
	}

	/**
	 * Entl�dt das Entity
	 */
	public void unload() {
		getGameRunner().getRenderer().removeEntity(this);
	}

	/**
	 * Bewegt das Entity zur Postion(x,y) mit der Geschwindigkeit speed
	 * 
	 * @param x
	 *            x-Postion des Entitys
	 * @param y
	 *            y-Postion des Entitys
	 * @param speed
	 *            Geschwindigkeit der Bewegung
	 */
	public void moveTo(double x, double y, double speed) {
		movePosition.x=x;
		movePosition.y=y;
		velocity = movePosition.minus(getPosition());
		velocity = velocity.normalize();
		velocity = velocity.multiply(speed);
		
		movement = true;
	}

	/**
	 * updated das Movement
	 */
	public void updateMovement() {
		if (movement) {
			setPosition(getPosition().plus(velocity));

			Vec2 difference = movePosition.minus(getPosition());

			if (difference.length()<velocity.length()) {
				setPosition(movePosition);
				movement=false;
			}
		}
	}

	/**
	 * Gibt zur�ck ob sich das Entity bewegt
	 * 
	 * @return
	 */
	public boolean isMoving() {
		return movement;
	}

}
