package tk.ju57u5v.engine.console;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.TwoDMath;
import tk.ju57u5v.engine.animation.Animation;

public class CodeManager {

	/**
	 * Speicher alle Kommandos
	 */
	private HashMap<String, Command> commands = new HashMap<String, Command>();

	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public CodeManager() {
		registerCommands();
	}

	/**
	 * Verarbeitet eine Config Datei aus dem Config Ordner
	 * 
	 * @param pFileName
	 *            Name der Datei
	 */
	public void processCFG(String pFileName) {
		String content = Game.getResourceManager().getFile(pFileName);
		processContent(content);
	}

	/**
	 * Verarbeitet Code in content
	 * 
	 * @param content
	 *            Code
	 */
	public void processContent(String content) {
		String[] commands = content.split(";");
		for (String command : commands) {
			processCode(command);
		}
	}

	/**
	 * Verarbeitet einen Config Datei aus einem InputStream
	 * 
	 * @param stream
	 *            Stream der Datei
	 */
	public void processCFG(InputStream stream) {
		String content = Game.getResourceManager().getFile(stream);
		processContent(content);
	}

	/**
	 * Verarbeitet einen Kommando
	 * 
	 * @param pCode
	 *            Kommando als String
	 */
	public void processCode(String pCode) {

		String[] parts = pCode.trim().split("\\s+");
		parts[0].toLowerCase();
		boolean executed = false;

		// Auskommentierter Code:
		if (parts[0].startsWith("//") || parts[0].startsWith("#"))
			return;

		for (HashMap.Entry<String, Command> entry : commands.entrySet()) {
			if (entry.getKey().equals(parts[0])) {
				commands.get(entry.getKey()).call(Game.getGame(), pCode, parts);
				executed = true;
				break; // Wir wollen nicht alle Commandos durchgehen
			}
		}

		// Handel Convars without set-Method
		if (!executed) {
			for (HashMap.Entry<String, ConVar> entry : Game.getConsole().getConVarManager().getVars().entrySet()) {
				if (entry.getKey().equals(parts[0])) {
					if (parts.length < 2 || parts[1].trim().equals("")) {
						entry.getValue().logVarInfo();
					} else {
						Game.getConsole().getConVar(parts[0]).setValue(parts[1]);
					}
					executed = true;
					break; // Wir wollen nicht alle Commandos durchgehen
				}
			}
		}

		if (!executed)
			Game.getConsole().log("Unknown Command: " + pCode);
	}

	/**
	 * Recombiniert ein StringArray zu einem String ab dem Index start
	 * 
	 * @param pStringArray
	 *            Array zum Recombinieren
	 * @param start
	 *            Startindex ab dem Recombiniert wird
	 * @return
	 */
	private String recombine(String[] pStringArray, int start) {
		String combined = "";
		for (int c = start; c < pStringArray.length; c++) {
			combined += pStringArray[c] + " ";
		}
		return combined;
	}

	/**
	 * F�gt ein Kommando hinzu
	 * 
	 * @param query
	 *            Query des Kommandos
	 * @param command
	 *            Kommando Interface
	 */
	public void addCommand(String query, Command command) {
		commands.put(query, command);
	}

	/**
	 * Registriert alle Commandos
	 */
	private void registerCommands() {
		addCommand("echo", (game, pCode, parts) -> {
			String echo = recombine(parts, 1);
			Game.getConsole().log(echo);
		});

		addCommand("register", (game, pCode, parts) -> {
			if (parts[2].equals("as")) {
				Game.getResourceManager().loadImage(parts[1], parts[3]);
				Game.getConsole().log("Registered "+parts[1]+" as "+ parts[3]);
			} else {
				Game.getConsole().log("Error, usage: register <Filename> as <Query>");
			}
		});

		addCommand("update", (game, pCode, parts) -> {
			if (parts[2].equals("to")) {
				Game.getUpdater().registerUpdatableFile(parts[1], parts[3]);
			} else {
				Game.getConsole().log("Error, usage: update <URL> to <Path>");
			}
		});

		addCommand("exec", (game, pCode, parts) -> {
			processCFG(recombine(parts, 1));
		});

		addCommand("download", (game, pCode, parts) -> {
			if (parts[2].equals("to")) {
				try {
					Game.getUpdater().download(parts[1], parts[3], false, null);
					Game.getConsole().log("Download of " + parts[1] + " done!");
				} catch (IOException e) {
					Game.getConsole().log("Download of " + parts[1] + " failed!");
				}

			} else {
				Game.getConsole().log("Error, usage: download <URL> to <Path>");
			}
		});

		addCommand("bind", (game, pCode, parts) -> {
			int keycode;
			if (TwoDMath.isNumeric(parts[1].trim())) {
				keycode = Integer.parseInt(parts[1].trim());
			} else {
				keycode = parts[1].trim().toUpperCase().charAt(0);
			}
			Game.getBindHandler().bind(keycode, parts[2].trim());
			Game.getConsole().log("Bound " + parts[2] + " to " + parts[1]);
		});

		addCommand("unbind", (game, pCode, parts) -> {
			int keycode;
			if (TwoDMath.isNumeric(parts[1].trim())) {
				keycode = Integer.parseInt(parts[1].trim());
			} else {
				keycode = parts[1].trim().toUpperCase().charAt(0);
			}
			Game.getBindHandler().unbind(keycode);
			Game.getConsole().log("Unbound " + parts[1]);
		});

		addCommand("animation", (game, pCode, parts) -> {
			String[] querys = new String[parts.length - 3];
			for (int c = 3; c < parts.length; c++) {
				querys[c - 3] = parts[c];
			}
			Game.getResourceManager().saveAnimation(parts[1], new Animation(querys, Integer.parseInt(parts[2])));
		});

		addCommand("quit", (game, pCode, parts) -> {
			System.exit(0);
		});

		addCommand("set", (game, pCode, parts) -> {
			ConVar var = Game.getConsole().getConVar(parts[1].trim());
			// Wenn die Variable nicht existiert, erstellen wir sie.
				if (var == null) {
					Game.getConsole().def(parts[1].trim());
					var = Game.getConsole().getConVar(parts[1].trim());
				}
				var.setValue(parts[2].trim());
				Game.getConsole().log(parts[1].trim() + " == '" + parts[2].trim() + "'");
			});

		addCommand("def", (game, pCode, parts) -> {
			Game.getConsole().def(parts[1].trim(), parts[2].trim(), recombine(parts, 3));
			Game.getConsole().getConVar(parts[1]);
		});

		addCommand("startupdate", (game, pCode, parts) -> {
			Game.getUpdater().updateFiles(false);
		});

		addCommand("forceupdate", (game, pCode, parts) -> {
			Game.getUpdater().updateFiles(true);
		});

		addCommand("savevars", (game, pCode, parts) -> {
			Game.getConsole().getConVarManager().safeVars();
		});

		addCommand("map", (game, pCode, parts) -> {
			Game.getMapLoader().loadMap(parts[1]);
		});

		addCommand("unloadmap", (game, pCode, parts) -> {
			Game.getMapLoader().unloadMap();
		});

		addCommand("connect", (game, pCode, parts) -> {
			Game.getClient().connect(parts[1].trim(), 27015);
		});

		addCommand("/hack", (game, pCode, parts) -> {
			Game.getWindow().setTitle("<INSERT_GOOD_NAME_HERE>   |   Olaf was here");
			Game.getConsole().log("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			Game.getConsole().log("OLAF WAS HERE! FUCK YOU HACKER! WOW! GET REKT! WER BENUTZT EIGENTLICH SLASH IN DER CONSOLE?");
			Game.getConsole().log("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		});

		addCommand("restart", (game, pCode, parts) -> {
			try {
				Game.restartApplication(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		addCommand("fullscreen", (game, pCode, parts) -> {
			Game.getWindow().goFullScreen();
		});

		addCommand("copyupdate", (game, pCode, parts) -> {
			Game.getUpdater().copyUpdate();
		});
	}

	/**
	 * Gibt alle Commandos in einer ArrayList zur�ck
	 * 
	 * @return
	 */
	public HashMap<String, Command> getCommands() {
		return commands;
	}
}
