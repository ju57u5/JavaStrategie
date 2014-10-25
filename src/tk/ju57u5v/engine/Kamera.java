package tk.ju57u5v.engine;

import java.awt.image.BufferedImage;

public class Kamera extends Position {
	
	private Game game;
	
	public Kamera(Game game) {
		this.game = game;
		setWidth(1200);
		setHeight(900);
		
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
	
	public void setRelativPostion (Entity pEntity) {
		
		pEntity.setRelativX(pEntity.getX()+getX());
		pEntity.setRelativY(pEntity.getY()+getY());
	}
	
	
	
	public boolean isRenderNeeded (Entity entity) {
		return (TwoDMath.isInRect(entity.getRelativX(), entity.getRelativY(), this.getX(), this.getY(), this.getWidth(), this.getHeight()));
	}
	
	public BufferedImage scaleResource (BufferedImage resource) {
		double scaleX = ((double) getWidth()/(double)game.window.getWidth());
		double scaleY = ((double) getHeight()/(double) game.window.getHeight());
		
		return ResourceManager.scale(resource, resource.getType(), (int) (resource.getWidth()*scaleX), (int) (resource.getHeight()*scaleY), scaleX, scaleY);
	}
	
	@Override
	public void setPosition (int x, int y) {
		super.setPosition(x, y);
		updateRelativPositions();
	}
	
	public void setX (int x) {
		super.setX(x);
		updateRelativPositions();
	}
	
	public void setY (int y) {
		super.setY(y);
		updateRelativPositions();
	}
	
	
}
