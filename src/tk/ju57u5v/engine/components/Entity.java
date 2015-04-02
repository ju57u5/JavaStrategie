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
	 * Update-Methode des Entitys (sollte überschrieben werden)
	 */
	public void update() {

	}

	/**
	 * Intitialisiert das Entity
	 */
	protected void initialise() {
		getRenderer().registerEntity(this);
	}

	/**
	 * Entlädt das Entity
	 */
	public void unload() {
		getRenderer().removeEntity(this);
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
		//Move Position und aktuelle Postion sind gleich
		if (new Vec2(x, y).minus(getPosition()).isNullVec()) {
			return;
		}
		//System.out.println("ersetze "+movePosition.x+" durch "+x);
		//movePosition.x=x;
		//System.out.println("ist jetzt "+movePosition.x);
		//movePosition.y=y;
		//TODO: Herausfinden wieso man hier ein neues Object erstellen muss und nicht einfach x und y überschreiben kann O.o
		movePosition = new Vec2(x, y);
		//System.out.println(movePosition+" "+getPosition());
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
	 * Gibt zurück ob sich das Entity bewegt
	 * 
	 * @return
	 */
	public boolean isMoving() {
		return movement;
	}

}
