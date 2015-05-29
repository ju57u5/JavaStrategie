package tk.ju57u5v.engine;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.List;
import tk.ju57u5v.engine.console.CodeManager;
import tk.ju57u5v.engine.console.Console;
import tk.ju57u5v.engine.gui.GuiHandler;
import tk.ju57u5v.engine.input.BindHandler;
import tk.ju57u5v.engine.network.Client;
import tk.ju57u5v.engine.network.Server;
import tk.ju57u5v.engine.world.MapLoader;
import tk.ju57u5v.engine.world.TileManager;

public abstract class Game {

	/**
	 * Sun property pointing the main class and its arguments. Might not be
	 * defined on non Hotspot VM implementations. Für den Restart
	 */
	public static final String SUN_JAVA_COMMAND = "sun.java.command";

	/**
	 * Name des Spiels
	 */
	protected static String name = "Engine";
	
	/**
	 * Argumente des Spiels
	 */
	protected String[] args;

	/**
	 * Hauptklasse des Spiels
	 */
	protected static Game game;

	/**
	 * Kamera des Spiels
	 */
	protected static Kamera kamera = new Kamera();

	/**
	 * Fenster des Spiels
	 */
	protected static Window window = new Window();

	/**
	 * ResourcenManager des Spiels
	 */
	protected static ResourceManager resourceManager = new ResourceManager();

	/**
	 * Updater des Spiels
	 */
	protected static Updater updater = new Updater();

	/**
	 * Code Manager des Spiels
	 */
	protected static CodeManager codeManager = new CodeManager();

	/**
	 * Tastatur Input Handler des Spiels
	 */
	protected static BindHandler bindHandler = new BindHandler();

	/**
	 * Map Loader des Spiels
	 */
	protected static MapLoader mapLoader = new MapLoader();

	/**
	 * Entwickler Konsole des Spiels
	 */
	protected static Console console = new Console();

	/**
	 * GameRunner des Spiels
	 */
	protected static GameRunner gameRunner = new GameRunner();

	/**
	 * Renderer des Spiels
	 */
	protected static Renderer renderer = new Renderer();
	/**
	 * Server-Object des Spiels
	 */
	protected static Server server;// = new Server();

	/**
	 * Client-Object des Spiels
	 */
	protected static Client client;// = new Client();

	/**
	 * TileManager des Spiels
	 */
	protected static TileManager tileManager = new TileManager(500, 500);

	/**
	 * GuiHandler des Spiels
	 */
	protected static GuiHandler guiHandler = new GuiHandler();

	// Methoden
	/**
	 * Constructor
	 */
	public Game(String[] args) {
		this.args = args;
	}

	/**
	 * Builder des Spiels
	 */
	public static Game build(Game game) {
		Game.resourceManager.setupFiles();
		Game.game = game;
		game.startGame();
		return game;
	}

	/**
	 * Intitialisiert das Game (Muss aufgeführt werden, damit der Renderer
	 * Überschrieben werden kann.)
	 */
	public void initalizeGame() {
		window.addMouseListener(bindHandler);
		window.addMouseMotionListener(bindHandler);
		window.getFrame().addKeyListener(bindHandler);

		//Alle Argumente des Spiels werden als ConsoleCommands interpretiert
		for (String arg : args) {
			String[] commands = arg.split(";");
			for (String command : commands) {
				codeManager.processCode(command);
			}
		}
	}

	public void startGame() {
		// Load Convars
		codeManager.processCFG("varsafe.cfg");
		// Load Config
		codeManager.processCFG("config.cfg");
		//Aktiviet Rendern und Updaten
		renderer.doUpdate(true);
		renderer.doRender(true);
		renderer.doGuiRender(true);
	}

	/**
	 * Gibt den Resourcen-Manager zurück
	 * 
	 * @return
	 */
	public static ResourceManager getResourceManager() {
		return resourceManager;
	}

	/**
	 * Gibt den Resourcen-Manager zurück
	 * 
	 * @return
	 */
	public static GameRunner getGameRunner() {
		return gameRunner;
	}

	/**
	 * Gibt das Main-Fenster zurück
	 * 
	 * @return
	 */
	public static Window getWindow() {
		return window;
	}

	/**
	 * Gibt die Kamera zurück
	 * 
	 * @return
	 */
	public static Kamera getKamera() {
		return kamera;
	}

	/**
	 * Gibt den Bind-Handler zurück
	 * 
	 * @return
	 */
	public static BindHandler getBindHandler() {
		return bindHandler;
	}

	/**
	 * Gibt den Code-Manager zurück
	 * 
	 * @return
	 */
	public static CodeManager getCodeManager() {
		return codeManager;
	}

	/**
	 * Gibt den Updater zurück
	 * 
	 * @return
	 */
	public static Updater getUpdater() {
		return updater;
	}

	/**
	 * Gibt die EntwicklerConsole zurück zurück
	 * 
	 * @return
	 */
	public static Console getConsole() {
		return console;
	}

	/**
	 * Gibt den MapLoader zurück
	 * 
	 * @return
	 */
	public static MapLoader getMapLoader() {
		return mapLoader;
	}

	/**
	 * Gibt den TileManager zurück
	 * 
	 * @return TileManager
	 */
	public static TileManager getTileManager() {
		return tileManager;
	}

	/**
	 * Setzt den Namen des Spiels
	 * @param name Name des Spiels
	 */
	public static void setName(String name) {
		Game.name = name;
	}

	/**
	 * Gibt die Hauptklasse des Spiels zurück
	 * 
	 * @return
	 */
	public static Game getGame() {
		return game;
	}

	public static Server getServer() {
		return server;
	}

	public static Client getClient() {
		return client;
	}

	public static Renderer getRenderer() {
		return renderer;
	}
	
	public abstract void tick();

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
			cmd.append("\"copyupdate;\"");
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
