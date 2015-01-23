package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.awt.Image;

import tk.ju57u5v.engine.console.CodeManager;
import tk.ju57u5v.engine.console.Console;
import tk.ju57u5v.engine.input.BindHandler;
import tk.ju57u5v.engine.input.MouseHandler;
import tk.ju57u5v.engine.world.MapLoader;

public class Game {

	/**
	 * Kamera des Spiels
	 */
	protected Kamera kamera = new Kamera(this);

	/**
	 * Fenster des Spiels
	 */
	protected Window window = new Window(this);

	/**
	 * ResourcenManager des Spiels
	 */
	protected ResourceManager resourceManager = new ResourceManager(this);

	/**
	 * Updater des Spiels
	 */
	protected Updater updater = new Updater(this, resourceManager.getBasePath());

	/**
	 * Code Manager des Spiels
	 */
	protected CodeManager codeManager = new CodeManager(this);

	/**
	 * Maus Input Handler des Spiels
	 */
	protected MouseHandler mouseHandler = new MouseHandler(this);

	/**
	 * Tastatur Input Handler des Spiels
	 */
	protected BindHandler bindHandler = new BindHandler();

	/**
	 * Player Object des Spiels
	 */
	protected Player player = new Player();

	/**
	 * Map Loader des Spiels
	 */
	protected MapLoader mapLoader = new MapLoader(this);

	/**
	 * Entwickler Konsole des Spiels
	 */
	protected Console console = new Console(this);

	/**
	 * GameRunner des Spiels
	 */
	protected GameRunner gameRunner = new GameRunner(this);

	// Methoden
	/**
	 * Constructor
	 */
	public Game() {
	}

	/**
	 * Intitialisiert das Game (ermöglicht Updaten und rendern)
	 */
	public void initalizeGame() {
		window.addMouseListener(mouseHandler);
		window.addMouseMotionListener(mouseHandler);
		window.getFrame().addKeyListener(bindHandler);
		resourceManager.checkConfig();
		// Load Convars
		codeManager.processCFG("varsafe.cfg");
		// Load Config
		codeManager.processCFG("config.cfg");
		mapLoader.loadMap("oakland.jar");
		gameRunner.renderer.doUpdate(true);
		gameRunner.renderer.doRender(true);
	}

	/**
	 * Gibt den Resourcen-Manager zurück
	 * 
	 * @return
	 */
	public ResourceManager getResourceManager() {
		return resourceManager;
	}

	/**
	 * Gibt den Resourcen-Manager zurück
	 * 
	 * @return
	 */
	public GameRunner getGameRunner() {
		return gameRunner;
	}

	/**
	 * Gibt das Main-Fenster zurück
	 * 
	 * @return
	 */
	public Window getWindow() {
		return window;
	}

	/**
	 * Gibt die Kamera zurück
	 * 
	 * @return
	 */
	public Kamera getKamera() {
		return kamera;
	}

	/**
	 * Gibt den Bind-Handler zurück
	 * 
	 * @return
	 */
	public BindHandler getBindHandler() {
		return bindHandler;
	}

	/**
	 * Gibt den Code-Manager zurück
	 * 
	 * @return
	 */
	public CodeManager getCodeManager() {
		return codeManager;
	}

	/**
	 * Gibt den Updater zurück
	 * 
	 * @return
	 */
	public Updater getUpdater() {
		return updater;
	}

	/**
	 * Gibt die EntwicklerConsole zurück zurück
	 * 
	 * @return
	 */
	public Console getConsole() {
		return console;
	}

	/**
	 * Gibt den MapLoader zurück
	 * 
	 * @return
	 */
	public MapLoader getMapLoader() {
		return mapLoader;
	}

}
