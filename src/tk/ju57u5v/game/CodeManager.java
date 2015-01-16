package tk.ju57u5v.game;

import tk.ju57u5v.engine.Game;



public class CodeManager extends tk.ju57u5v.engine.console.CodeManager {

	public CodeManager(Game game) {
		super(game);
	}
	
	public void processUnitSettings(String pFileName, Unit unit) {
		String content = game.getResourceManager().getFile(pFileName);
		
		String[] commands = content.split(";");
		for (String command : commands) {
			processUnitSetting(command, unit);
		}
	}
	
	private void processUnitSetting(String pCode, Unit unit) {
		String[] parts = pCode.trim().split(":");
		parts[0].toLowerCase().trim();
		
		switch (parts[0]) {
			case "health":
				unit.setHealth(toInt(parts[1]));
				break;
		}
	}
	
	public void processBuildingSettings(String pFileName, Building building) {
		String content = game.getResourceManager().getFile(pFileName);
		
		String[] commands = content.split(";");
		for (String command : commands) {
			processBuildingSetting(command, building);
		}
	}
	
	private void processBuildingSetting(String pCode, Building building) {
		String[] parts = pCode.trim().split(":");
		parts[0].toLowerCase().trim();
		
		switch (parts[0]) {
			case "health":
				building.setHealth(toInt(parts[1]));
				break;
		}
	}
	
	private int toInt(String string) {
		return Integer.parseInt(string);
	}

}
