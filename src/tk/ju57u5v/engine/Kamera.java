package tk.ju57u5v.engine;

public class Kamera extends Position {
	
	private Game game;
	
	public Kamera(Game game) {
		this.game = game;
	}
	
	private void updateRelativPositions () {
		for (Entity entity : game.gameRunner.getEntities()) {
			entity.setRelativX(entity.getX()+getX());
			entity.setRelativY(entity.getY()+getY());
		}
		
		for (GameObject gameObject : game.gameRunner.getGameObjects()) {
			gameObject.setRelativX(gameObject.getX()+getX());
			gameObject.setRelativY(gameObject.getY()+getY());
		}
	}
	
	public void setPositon (int x, int y) {
		super.setPosition(x, y);
		updateRelativPositions();
	}
	
	public boolean isRenderNeeded (Entity entity) {
		return true;
		//return (entity.getRelativX())
	}
	
}
