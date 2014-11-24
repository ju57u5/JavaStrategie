package tk.ju57u5v.engine;

import java.io.IOException;


public class CodeManager {

	private Game game;

	public CodeManager(Game game) {
		this.game = game;
		processCFG("config.cfg");
	}

	public void processCFG(String pFileName) {
		String content = game.resourceManager.getFile(pFileName);
		
		String[] commands = content.split(";");
		for (String command : commands) {
			processCode(command);
		}
	}

	public void processCode(String pCode) {
		
		String[] parts = pCode.trim().split("\\s+");
		parts[0].toLowerCase();
		
		switch (parts[0]) {
			case "echo":
				echo(pCode);
				break;
			case "update":
				update(parts);
				break;
			case "register":
				register(parts);
				break;
			case "dialog":
				
				break;
			case "set":
				set(parts);
				break;
			case "exec":
				exec(parts);
				break;
			case "startupdate":
				game.updater.updateFiles();
				break;
			case "download":
				download(parts);
				break;
	
			default:
				break;
		}
	}

	private void set(String[] parts) {
		
	}

	private void register(String[] parts) {
		if (parts[2].equals("as")) {
			game.resourceManager.loadImage(parts[1], parts[3]);
		} else {
			System.out.println("Error, usage: register <Filename> as <Query>");
		}
	}

	private void update(String[] parts) {
		if (parts[2].equals("to")) {
			game.updater.registerUpdatableFile(parts[1], parts[3]);
		} else {
			System.out.println("Error, usage: update <URL> to <Path>");
		}
	}

	private void echo(String pCode) {
		String[] parts = pCode.split("\\s+");
		String echo=recombine(parts);
		System.out.println(echo);
	}
	
	private String recombine (String[] pStringArray) {
		String combined = "";
		for (int c=0;c<pStringArray.length;c++) {
			if (c>0) {
				combined += pStringArray[c]+" ";
			}
		}
		return combined;
	}
	
	private void exec (String[] parts) {
		processCFG(recombine(parts));
	}
	
	private void download (String[] parts) {
		if (parts[2].equals("to")) {
			try {
				game.updater.download(parts[1], parts[3], false);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			System.out.println("Error, usage: download <URL> to <Path>");
		}
	}
}
