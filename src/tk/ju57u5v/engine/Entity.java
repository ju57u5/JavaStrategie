package tk.ju57u5v.engine;

public class Entity extends Position {
	
	protected Game game;
	
	public Entity(Game game) {
		this.game=game;
	}
	
	public void setPosition (int x, int y) {
		super.setX(x);
		super.setY(y);
		game.kamera.setRelativPostion(this);
	}
	
	public void setX (int x) {
		super.setX(x);
		game.kamera.setRelativPostion(this);
	}
	
	public void setY (int y) {
		super.setY(y);
		game.kamera.setRelativPostion(this);
	}
	
	public void update () {
		
	}
	
	public void render () {
		
	}
	
	
}
