package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel {
	Game game;
	BufferStrategy strategy;
	
	private JFrame frame = new JFrame();
	private long timer = System.currentTimeMillis();
	private int frames=0;
	private int ups=0;
	private String title="Strategie-JavaGame";
	
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
		
		frame.addKeyListener(game);
		
		frame.setTitle(title); // Fenstertitel setzen
		frame.setSize(1200,900); 
		frame.addWindowListener(new WindowListener());
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
		frame.setIgnoreRepaint(true);
		frame.add(this);
		setLocation(0, 0);
		
	}
	
	protected void setUps(int ups) {
		this.ups = ups;
	}
	
	@Override
	protected void paintComponent(Graphics g)  {
		if (game.gameRunner != null) {
			if (game.gameRunner.renderer != null) {
				g.clearRect(0, 0, getWidth(), getHeight());
				game.gameRunner.renderer.render(g);
			}
		}
		frames++;
		if (System.currentTimeMillis() - timer > 1000) {
			timer += 1000;
			frame.setTitle(title+"   |   "+ups+" ups "+frames+" fps");
			frames=0;
		}
		
		repaint();
	}
}
