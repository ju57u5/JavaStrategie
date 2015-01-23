package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel implements WindowListener {

	/**
	 * Verknüpfung zur Hauptklasse
	 */
	private Game game;

	/**
	 * Frame des Spiels
	 */
	private JFrame frame = new JFrame();

	/**
	 * Timer Variable, um die FPS zu überprüfen
	 */
	private long timer = System.currentTimeMillis();

	/**
	 * FPS
	 */
	private int frames = 0;

	/**
	 * UPS
	 */
	private int ups = 0;

	/**
	 * Titel des Fensters
	 */
	private String title = "Strategie-JavaGame";

	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public Window(Game game) {
		this.game = game;

		frame.setTitle(title); // Fenstertitel setzen
		frame.setSize(1200, 900);
		frame.addWindowListener(this);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setIgnoreRepaint(true);
		frame.add(this);
		setLocation(0, 0);
	}

	/**
	 * Setzt die UPS (genutzt vom Gamerunner)
	 * 
	 * @param ups
	 */
	protected void setUps(int ups) {
		this.ups = ups;
	}

	/**
	 * Rendert alle Komponenten des Spiels über den Renderer
	 */
	@Override
	protected void paintComponent(Graphics g) {
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
			frame.setTitle(title + "   |   " + ups + " ups " + frames + " fps");
			frames = 0;
		}
		repaint();
	}

	/**
	 * Gibt das SpielFenster zurück
	 * 
	 * @return
	 */
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
