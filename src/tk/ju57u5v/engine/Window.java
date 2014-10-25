package tk.ju57u5v.engine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Window extends JFrame{
	Game game;
	BufferStrategy strategy;
	
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
	
	
	
}
