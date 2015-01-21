package tk.ju57u5v.engine.console;

import java.io.IOException;
import java.util.HashMap;
import tk.ju57u5v.engine.Animation;
import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.TwoDMath;

public class CodeManager {

	protected Game game;
	private HashMap<String, Command> commands = new HashMap<String, Command>();

	public CodeManager(Game game) {
		this.game = game;
		registerCommands();
	}

	public void processCFG(String pFileName) {
		String content = game.getResourceManager().getFile(pFileName);

		String[] commands = content.split(";");
		for (String command : commands) {
			processCode(command);
		}
	}

	public void processCode(String pCode) {

		String[] parts = pCode.trim().split("\\s+");
		parts[0].toLowerCase();
		boolean executed = false;

		for (HashMap.Entry<String, Command> entry : commands.entrySet()) {
			if (entry.getKey().equals(parts[0])) {
				commands.get(entry.getKey()).call(game, pCode, parts);
				executed=true;
				break; // Wir wollen nicht alle Commandos durchgehen
			}
		}
		
		//Handel Convars without set-Method
		if (!executed) {
			for (HashMap.Entry<String, String> entry : game.getConsole().getConVarManager().getVars().entrySet()) {
				if (entry.getKey().equals(parts[0])) {
					if (parts.length < 2 || parts[1].trim().equals("")) {
						game.getConsole().logVarInfo(parts[0]);
					} else {
						game.getConsole().set(parts[0], parts[1]);
					}
					executed=true;
				}
			}
		}
		
		if (!executed) game.getConsole().log("Unknown Command: " + pCode);
	}

	private String recombine(String[] pStringArray, int start) {
		String combined = "";
		for (int c = start; c < pStringArray.length; c++) {
			combined += pStringArray[c] + " ";
		}
		return combined;
	}

	public void addCommand(String query, Command command) {
		commands.put(query, command);
	}

	private void registerCommands() {
		addCommand("echo", (game, pCode, parts) -> {
			String echo = recombine(parts, 1);
			game.getConsole().log(echo);
		});

		addCommand("register", (game, pCode, parts) -> {
			if (parts[2].equals("as")) {
				game.getResourceManager().loadImage(parts[1], parts[3]);
			} else {
				game.getConsole().log("Error, usage: register <Filename> as <Query>");
			}
		});

		addCommand("update", (game, pCode, parts) -> {
			if (parts[2].equals("to")) {
				game.getUpdater().registerUpdatableFile(parts[1], parts[3]);
			} else {
				game.getConsole().log("Error, usage: update <URL> to <Path>");
			}
		});

		addCommand("exec", (game, pCode, parts) -> {
			processCFG(recombine(parts,1));
		});

		addCommand("download", (game, pCode, parts) -> {
			if (parts[2].equals("to")) {
				try {
					game.getUpdater().download(parts[1], parts[3], false);
					game.getConsole().log("Download of " + parts[1] + " done!");
				} catch (IOException e) {
					game.getConsole().log("Download of " + parts[1] + " failed!");
				}

			} else {
				game.getConsole().log("Error, usage: download <URL> to <Path>");
			}
		});

		addCommand("bind", (game, pCode, parts) -> {
			int keycode;
			if (TwoDMath.isNumeric(parts[1].trim())) {
				keycode = Integer.parseInt(parts[1].trim());
			} else {
				keycode = parts[1].trim().toUpperCase().charAt(0);
			}
			game.getBindHandler().bind(keycode, parts[2].trim());
			game.getConsole().log("Bound " + parts[2] + " to " + parts[1]);
		});

		addCommand("unbind", (game, pCode, parts) -> {
			int keycode;
			if (TwoDMath.isNumeric(parts[1].trim())) {
				keycode = Integer.parseInt(parts[1].trim());
			} else {
				keycode = parts[1].trim().toUpperCase().charAt(0);
			}
			game.getBindHandler().unbind(keycode);
			game.getConsole().log("Unbound " + parts[1]);
		});

		addCommand("animation", (game, pCode, parts) -> {
			String[] querys = new String[parts.length - 3];
			for (int c = 3; c < parts.length; c++) {
				querys[c - 3] = parts[c];
			}
			game.getResourceManager().saveAnimation(parts[1], new Animation(querys, Integer.parseInt(parts[2])));
		});

		addCommand("quit", (game, pCode, parts) -> {
			System.exit(0);
		});

		addCommand("set", (game, pCode, parts) -> {
			game.getConsole().set(parts[1].trim(), parts[2].trim());
			game.getConsole().log(parts[1].trim() + " == '" + parts[2].trim() + "'");
		});
		
		addCommand("def", (game, pCode, parts) -> {
			game.getConsole().def(parts[1].trim(), parts[2].trim(), recombine(parts, 3));
			game.getConsole().logVarInfo(parts[1]);
		});

		addCommand("startupdate", (game, pCode, parts) -> {
			game.getUpdater().updateFiles(false);
		});

		addCommand("forceupdate", (game, pCode, parts) -> {
			game.getUpdater().updateFiles(true);
		});
		
		addCommand("savevars", (game, pCode, parts) -> {
			game.getConsole().getConVarManager().safeVars();
		});
	}

	public HashMap<String, Command> getCommands() {
		return commands;
	}
}