package tk.ju57u5v.engine.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import tk.ju57u5v.engine.components.Vec2;

public class Sprite {

	private BufferedImage image;
	
	public Sprite(BufferedImage image) {
		this.image=image;
	}
	
	public void draw (Graphics2D g, int x, int y) {
		g.drawImage(image, x, y, null);
	}
	
	public void draw (Graphics2D g, Vec2 position) {
		draw(g, position.getX(), position.getY());
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}

	public Vec2 getDimensions() {
		return new Vec2(getWidth(),getHeight());
	}
	public BufferedImage getImage() {
		return image;
	}

}
