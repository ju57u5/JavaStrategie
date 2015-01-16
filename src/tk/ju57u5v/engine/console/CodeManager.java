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

		for (HashMap.Entry<String, Command> entry : commands.entrySet()) {
			if (entry.getKey().equals(parts[0])) {
				commands.get(entry.getKey()).call(game, pCode, parts);
				break; // Wir wollen nicht alle Commandos durchgehen
			}
		}
	}

	private String recombine(String[] pStringArray) {
		String combined = "";
		for (int c = 1; c < pStringArray.length; c++) {
			combined += pStringArray[c] + " ";
		}
		return combined;
	}

	public void addCommand(String query, Command command) {
		commands.put(query, command);
	}

	public void registerCommands() {
		addCommand("echo", (game, pCode, parts) -> {
			String echo = recombine(parts);
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
			processCFG(recombine(parts));
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

		addCommand("startupdate", (game, pCode, parts) -> {
			game.getUpdater().updateFiles();
		});

		addCommand("bind", (game, pCode, parts) -> {
			int keycode;
			if (TwoDMath.isNumeric(parts[1].trim())) {
				keycode = Integer.parseInt(parts[1].trim());
			} else {
				keycode = parts[1].trim().toUpperCase().toCharArray()[0];
			}
			game.getBindHandler().bind(keycode, parts[2].trim());
			game.getConsole().log("Bound " + parts[2] + " to " + parts[1]);
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
	}
}
