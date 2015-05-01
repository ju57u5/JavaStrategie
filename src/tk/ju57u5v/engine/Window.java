package tk.ju57u5v.engine;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel implements WindowListener, ComponentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 * Gibt an ob Fullscreen an ist
	 */
	private boolean fullscreen = false;

	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public Window() {
		frame.setTitle(title); // Fenstertitel setzen
		frame.setSize(1200, 900);
		frame.addWindowListener(this);
		this.addComponentListener(this);
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
	 *            Updates per Second
	 */
	protected void setUps(int ups) {
		this.ups = ups;
	}

	/**
	 * Rendert alle Komponenten des Spiels über den Renderer
	 */
	@Override
	protected void paintComponent(Graphics g) {
		if (Game.gameRunner != null) {
			if (Game.renderer != null) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				Game.renderer.render((Graphics2D) g);
			}
		}
		frames++;
		if (System.currentTimeMillis() - timer > 1000) {
			timer += 1000;
			frame.setTitle(title + "   |   " + ups + " ups " + frames + " fps");
			Game.console.getFrame().setTitle("Console" + "   |   " + ups + " ups " + frames + " fps");
			frames = 0;
		}
		repaint();
	}

	/**
	 * Gibt das SpielFenster zurück
	 * 
	 * @return Fenster des Spiels
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
		if (Game.window != null && Game.kamera != null && !fullscreen)
			Game.window.getFrame().setSize(Game.kamera.getWidth(), Game.kamera.getHeight());
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		Game.bindHandler.unactiveAll();
	}

	/**
	 * Setzt den Titel des Fensters
	 * 
	 * @param title
	 *            Titel
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		if (!fullscreen)
			Game.kamera.setDimensions(e.getComponent().getWidth(), e.getComponent().getHeight());
	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}

	/**
	 * Aktiviert den FullscreenModus
	 */
	public void goFullScreen() {
		GraphicsDevice myDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		DisplayMode oldDisplayMode = myDevice.getDisplayMode();

		if (myDevice.isFullScreenSupported()) {
			frame.dispose();
			frame.setUndecorated(true);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
			frame.setResizable(false);
			Game.kamera.setDimensions((int) screenSize.getWidth(), (int) screenSize.getHeight());

			frame.repaint();
			myDevice.setFullScreenWindow(frame);
			myDevice.setDisplayMode(new DisplayMode((int) screenSize.getWidth(), (int) screenSize.getHeight(), oldDisplayMode.getBitDepth(), 144));
			fullscreen = true;
		}
	}

	/**
	 * Gibt zurück ob der Fullscreen Modus angeschaltet ist.
	 * 
	 * @return Fullscreen Modus
	 */
	public boolean isFullscreen() {
		return fullscreen;
	}
}
