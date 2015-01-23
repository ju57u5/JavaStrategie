package tk.ju57u5v.engine;

public class Entity extends GameObject {

	/**
	 * x-Position, zu der sich das Entity bewegt
	 */
	private int xMovePosition = 0;

	/**
	 * y-Position, zu der sich das Entity bewegt
	 */
	private int yMovePosition = 0;

	/**
	 * Geschwindigkeit, mit der sich das Entity bewegt
	 */
	private int movementSpeed = 2;

	/**
	 * Winkel, in dem sich das Entity bewegt im Bogenmaß
	 */
	private double radian = 0;

	/**
	 * aktuelle (temporäre) x-Position des Entitys während des Bewegens
	 */
	private double currentX = 0;

	/**
	 * aktuelle (temporäre) y-Position des Entitys während des Bewegens
	 */
	private double currentY = 0;

	/**
	 * gibt die x-Differenz zur xMovePosition an
	 */
	private int xDifference = 0;

	/**
	 * gibt die y-Differenz zur yMovePosition an
	 */
	private int yDifference = 0;

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
	public Entity(Game game) {
		super(game);
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
		game.gameRunner.renderer.registerEntity(this);
	}

	/**
	 * Entlädt das Entity
	 */
	public void unload() {
		game.gameRunner.renderer.removeEntity(this);
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
	public void moveTo(int x, int y, int speed) {
		this.movementSpeed = speed;
		this.xMovePosition = x;
		this.yMovePosition = y;
		xDifference = x - getX();
		yDifference = y - getY();
		currentX = getX();
		currentY = getY();
		radian = Math.atan2(yDifference, xDifference);
		movement = true;
	}

	/**
	 * updated das Movement
	 */
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

	/**
	 * Gibt zurück ob sich das Entity bewegt
	 * 
	 * @return
	 */
	public boolean isMoving() {
		return movement;
	}

	/**
	 * Speichert eine Animation des globalen Animationmanagers mit der query
	 * unter newQuery
	 * 
	 * @param newQuery
	 *            Query im lokalen Manager
	 * @param query
	 *            Query im globalen Manager
	 */
	protected void getSavedAnimation(String newQuery, String query) {
		animationManager.putAnimationString(newQuery, game.resourceManager.getAnimation(query));
	}
}
