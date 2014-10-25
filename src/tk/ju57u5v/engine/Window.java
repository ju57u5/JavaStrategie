package tk.ju57u5v.engine;

import java.awt.Canvas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Window extends Canvas{
	Game game;
	BufferStrategy strategy;
	
	private JFrame frame = new JFrame();
	
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
		
		
		frame.setTitle("Strategie-JavaGame"); // Fenstertitel setzen
		frame.setSize(1200,900); 
		frame.addWindowListener(new WindowListener());
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
		
		
	}
	
	
	
}
