package tk.ju57u5v.engine;

public class Position {
	
	private int x=0;
	private int y=0;
	private int width=0;
	private int height=0;
	private int relativX=0;
	private int relativY=0;
	
	public void setPosition (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX (int x) {
		this.x=x;
	}
	
	public void setY (int y) {
		this.y=y;
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
	
	public int getRelativX () {
		return relativX;
	}
	
	public int getRelativY () {
		return relativY;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setDimensions (int width, int height) {
		this.width=width;
		this.height=height;
	}
	
	public void setWidth(int width) {
		this.width=width;
	}
	
	public void setHeight(int height) {
		this.height=height;
	}
	
	public void setRelativX (int relativX) {
		this.relativX=relativX;
	}
	
	public void setRelativY (int relativY) {
		this.relativY=relativY;
	}
}
