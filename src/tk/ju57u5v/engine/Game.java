package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game {
	
	private BufferedImage mainBufferImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_BGR);
	GameRunner gameRunner = new GameRunner();
	Kamera kamera = new Kamera(this);
	
	public Game() {
		// TODO Auto-generated constructor stub
	}
	
	public Graphics getMainGraphics() {
		return mainBufferImage.getGraphics();
	}
	
	
}
