package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.awt.Image;
import tk.ju57u5v.engine.console.CodeManager;
import tk.ju57u5v.engine.console.Console;
import tk.ju57u5v.engine.input.BindHandler;
import tk.ju57u5v.engine.input.MouseHandler;

public class Game {

	protected Kamera kamera = new Kamera(this);
	protected Window window = new Window(this);
	protected ResourceManager resourceManager = new ResourceManager(this);
	protected Updater updater = new Updater(this, resourceManager.getBasePath());
	protected CodeManager codeManager = new CodeManager(this);
	protected MouseHandler mouseHandler = new MouseHandler(this);
	protected BindHandler bindHandler = new BindHandler();
	protected Player player = new Player();
	protected Console console = new Console(this);
	private Image mainBufferImage = window.createImage(1920, 1080);
	protected GameRunner gameRunner = new GameRunner(this);

	public Game() {
	}
	
	public void initalizeGame() {
		window.addMouseListener(mouseHandler);
		window.addMouseMotionListener(mouseHandler);
		window.getFrame().addKeyListener(bindHandler);
		resourceManager.checkConfig();
		codeManager.processCFG("config.cfg");
		gameRunner.renderer.doUpdate(true);
		gameRunner.renderer.doRender(true);
	}
	
	public Graphics getMainGraphics() {
		return gameRunner.strategy.getDrawGraphics();
	}

	public Image getMainImage() {
		return mainBufferImage;
	}

	public ResourceManager getResourceManager() {
		return resourceManager;
	}

	public GameRunner getGameRunner() {
		return gameRunner;
	}

	public Window getWindow() {
		return window;
	}

	public Kamera getKamera() {
		return kamera;
	}
	
	public BindHandler getBindHandler() {
		return bindHandler;
	}

	public CodeManager getCodeManager() {
		return codeManager;
	}

	public Updater getUpdater() {
		return updater;
	}

	public Console getConsole() {
		return console;
	}
	
	
}
