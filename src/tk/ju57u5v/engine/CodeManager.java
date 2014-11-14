package tk.ju57u5v.engine;


public class CodeManager {

	private Game game;

	public CodeManager(Game game) {
		this.game = game;
		processCFG("test.cfg");
	}

	public void processCFG(String pFileName) {
		String content = game.resourceManager.getFile(pFileName);
		
		String[] commands = content.split(";");
		for (String command : commands) {
			processCode(command);
		}
	}

	public void processCode(String pCode) {
		
		String[] parts = pCode.toLowerCase().trim().split("\\s+");
		
		switch (parts[0]) {
			case "echo":
				echo(pCode);
				break;
			case "download":
				download(parts);
				break;
			case "register":
				register(parts);
				break;
			case "dialog":
				
				break;
			case "set":
				set(parts);
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
			System.out.println("register <Filename> as <Query>");
		}
	}

	private void download(String[] parts) {
		
	}

	private void echo(String pCode) {
		String[] parts = pCode.split("\\s+");
		String echo="";
		for (int c=0;c<parts.length;c++) {
			if (c>0) {
				echo += parts[c]+" ";
			}
		}
		System.out.println(echo);
	}
}
