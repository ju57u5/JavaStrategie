package tk.ju57u5v.engine.console;

import java.util.HashMap;

import tk.ju57u5v.engine.Game;

public class ConVarManager {
	private HashMap<String, String> vars = new HashMap<String, String>();
	private Game game;

	public ConVarManager(Game game) {
		this.game=game;
	}

	public void set(String name, String value) {
		vars.put(name, value);
	}

	public String getString(String name) {
		return vars.get(name);
	}

	public int getInt(String name) {
		try {
			return Integer.parseInt(vars.get(name));
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public double getDouble(String name) {
		try {
			return Double.parseDouble(name);
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}
	
	public boolean getBoolean (String name) {
		try {
			return Integer.parseInt(vars.get(name)) != 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
