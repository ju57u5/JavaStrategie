package tk.ju57u5v.engine.console;

import java.util.HashMap;

import tk.ju57u5v.engine.Game;

public class ConVarManager {
	private HashMap<String, String> vars = new HashMap<String, String>();
	private HashMap<String, String> defaults = new HashMap<String, String>();
	private HashMap<String, String> descriptions = new HashMap<String, String>();
	private Game game;

	public ConVarManager(Game game) {
		this.game = game;
	}

	public void set(String name, String value) {
		vars.put(name, value);
	}

	public void def(String name, String defaultValue, String description) {
		vars.put(name, defaultValue);
		defaults.put(name, defaultValue);
		descriptions.put(name, description);
	}

	public String getString(String name) {
		return vars.get(name);
	}

	public int getInt(String name) {
		try {
			return Integer.parseInt(vars.get(name));
		} catch (NumberFormatException e) {
			if (getDefaultValue(name) != null)
				return Integer.parseInt(defaults.get(name));
			return 0;
		}
	}

	public double getDouble(String name) {
		try {
			return Double.parseDouble(vars.get(name));
		} catch (NumberFormatException e) {
			if (getDefaultValue(name) != null)
				return Double.parseDouble(defaults.get(name));
			return 0.0;
		}
	}

	public boolean getBoolean(String name) {
		try {
			return Integer.parseInt(vars.get(name)) != 0;
		} catch (NumberFormatException e) {
			if (getDefaultValue(name) != null)
				return Integer.parseInt(defaults.get(name)) != 0;
			return false;
		}
	}

	public String getDefaultValue(String name) {
		return defaults.get(name);
	}

	public String getDescription(String name) {
		return descriptions.get(name);
	}

	public HashMap<String, String> getVars() {
		return vars;
	}

	public void safeVars() {
		String commands = "";
		for (HashMap.Entry<String, String> entry : this.getVars().entrySet()) {
			String varName = entry.getKey();
			commands += "def " + varName + " " + getDefaultValue(varName) + " " + getDescription(varName) + ";\n";
		}
		game.getResourceManager().writeToFile("varsafe.cfg", commands);
	}
}
