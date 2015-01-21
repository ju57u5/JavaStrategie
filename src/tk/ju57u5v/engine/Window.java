package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel implements WindowListener {
	Game game;
	BufferStrategy strategy;
	
	private JFrame frame = new JFrame();
	private long timer = System.currentTimeMillis();
	private int frames=0;
	private int ups=0;
	private String title="Strategie-JavaGame";
	
	public Window(Game game) {
		this.game=game;
		
		frame.setTitle(title); // Fenstertitel setzen
		frame.setSize(1200,900); 
		frame.addWindowListener(this);
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
				game.gameRunner.renderer.render((Graphics2D) g);
				game.mouseHandler.drawDrag((Graphics2D) g);
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

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		e.getWindow().dispose(); // Fenster "killen"
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		game.bindHandler.unactiveAll();
	}
}
