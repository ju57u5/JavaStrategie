package tk.ju57u5v.engine;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends Frame{
	Game game;
	
	class WindowListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
				e.getWindow().dispose(); // Fenster "killen"
				System.exit(0);
		}
	}
	
	public Window(Game game) {
		this.game=game;
		this.addKeyListener(game);
		setTitle("Strategie-JavaGame"); // Fenstertitel setzen
		setSize(1200,900); 
		addWindowListener(new WindowListener());
		setLocationRelativeTo(null); 
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(game.getMainImage(),0,0,this);
	}
}
