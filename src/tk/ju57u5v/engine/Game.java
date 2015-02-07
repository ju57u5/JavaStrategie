package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.List;

import com.sun.tracing.dtrace.ArgsAttributes;

import tk.ju57u5v.engine.console.CodeManager;
import tk.ju57u5v.engine.console.Console;
import tk.ju57u5v.engine.input.BindHandler;
import tk.ju57u5v.engine.input.MouseHandler;
import tk.ju57u5v.engine.netcode.Client;
import tk.ju57u5v.engine.netcode.Server;
import tk.ju57u5v.engine.world.MapLoader;
import tk.ju57u5v.engine.world.TileManager;

public class Game {

	/**
	 * Sun property pointing the main class and its arguments. Might not be
	 * defined on non Hotspot VM implementations. Für den Restart
	 */
	public static final String SUN_JAVA_COMMAND = "sun.java.command";
	
	/**
	 * Argumente des Spiels
	 */
	protected String[] args;
	
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

	/**
	 * Server-Object des Spiels
	 */
	protected Server server;// = new Server(this);

	/**
	 * Client-Object des Spiels
	 */
	protected Client client;// = new Client(this, "127.0.0.1", 27015);
	
	/**
	 * TileManager des Spiels
	 */
	protected TileManager tileManager = new TileManager(this, 500, 500);

	// Methoden
	/**
	 * Constructor
	 */
	public Game(String[] args) {
		this.args=args;
	}

	/**
	 * Intitialisiert das Game (ermöglicht Updaten und rendern)
	 */
	public void initalizeGame() {
		window.addMouseListener(mouseHandler);
		window.addMouseMotionListener(mouseHandler);
		window.getFrame().addKeyListener(bindHandler);
		resourceManager.checkConfig();
		//Copy Update
		for (String arg : args) {
			if (arg.equals("update"))
				updater.copyUpdate();
		}
		// Load Convars
		codeManager.processCFG("varsafe.cfg");
		// Load Config
		codeManager.processCFG("config.cfg");
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

	/**
	 * Restart the current Java application
	 * 
	 * @param runBeforeRestart
	 *            some custom code to be run before restarting
	 * @throws IOException
	 */
	public static void restartApplication(Runnable runBeforeRestart) throws IOException {
		try {
			// java binary
			String java = System.getProperty("java.home") + "/bin/java";
			// vm arguments
			List<String> vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
			StringBuffer vmArgsOneLine = new StringBuffer();
			for (String arg : vmArguments) {
				// if it's the agent argument : we ignore it otherwise the
				// address of the old application and the new one will be in
				// conflict
				if (!arg.contains("-agentlib")) {
					vmArgsOneLine.append(arg);
					vmArgsOneLine.append(" ");
				}
			}
			// init the command to execute, add the vm args
			final StringBuffer cmd = new StringBuffer("\"" + java + "\" " + vmArgsOneLine);

			// program main and program arguments
			String[] mainCommand = System.getProperty(SUN_JAVA_COMMAND).split(" ");
			// program main is a jar
			System.out.println(mainCommand[0]);
			if (mainCommand[0].endsWith(".jar")) {
				// if it's a jar, add -jar mainJar
				cmd.append("-jar " + new File(mainCommand[0]).getPath());
			} else {
				// else it's a .class, add the classpath and mainClass
				cmd.append("-cp \"" + System.getProperty("java.class.path") + "\" " + mainCommand[0]);
			}
			// finally add program arguments
			for (int i = 1; i < mainCommand.length; i++) {
				cmd.append(" ");
				cmd.append(mainCommand[i]);
			}
			// execute the command in a shutdown hook, to be sure that all the
			// resources have been disposed before restarting the application
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					try {
						Runtime.getRuntime().exec(cmd.toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			// execute some custom code before restarting
			if (runBeforeRestart != null) {
				runBeforeRestart.run();
			}
			// exit
			System.exit(0);
		} catch (Exception e) {
			// something went wrong
			throw new IOException("Error while trying to restart the application", e);
		}
	}

	/**
	 * Startet die geupdatete Jar basePath/update/Game.jar
	 * 
	 * @throws IOException
	 */
	public void startUpdatedJar() throws IOException {
		try {
			// java binary
			String java = System.getProperty("java.home") + "/bin/java";
			// vm arguments
			List<String> vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
			StringBuffer vmArgsOneLine = new StringBuffer();
			for (String arg : vmArguments) {
				// if it's the agent argument : we ignore it otherwise the
				// address of the old application and the new one will be in
				// conflict
				if (!arg.contains("-agentlib")) {
					vmArgsOneLine.append(arg);
					vmArgsOneLine.append(" ");
				}
			}
			// init the command to execute, add the vm args
			final StringBuffer cmd = new StringBuffer("\"" + java + "\" " + vmArgsOneLine);

			// program main and program arguments
			String[] mainCommand = System.getProperty(SUN_JAVA_COMMAND).split(" ");
			// program main is a jar
			cmd.append("-jar " + new File(resourceManager.getBasePath(), "/update/Game.jar").getPath());

			// finally add program arguments
			for (int i = 1; i < mainCommand.length; i++) {
				cmd.append(" ");
				cmd.append(mainCommand[i]);
			}
			cmd.append(" update");
			// execute the command in a shutdown hook, to be sure that all the
			// resources have been disposed before restarting the application
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					try {
						Runtime.getRuntime().exec(cmd.toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			// exit
			System.exit(0);
		} catch (Exception e) {
			// something went wrong
			throw new IOException("Error while trying to restart the application", e);
		}
	}

}
