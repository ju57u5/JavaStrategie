package tk.ju57u5v.engine.console;

import java.util.HashMap;

import tk.ju57u5v.engine.Game;

public class ConVarManager {

	/**
	 * HashMap mit den Convars
	 */
	private HashMap<String, String> vars = new HashMap<String, String>();

	/**
	 * HashMap mit den Default Zust�nden der Convars
	 */
	private HashMap<String, String> defaults = new HashMap<String, String>();

	/**
	 * HashMap mit den Beschreibungen der Convars
	 */
	private HashMap<String, String> descriptions = new HashMap<String, String>();

	/**
	 * Verkn�pfung zur Hauptklasse
	 */
	private Game game;

	/**
	 * Constructor
	 * 
	 * @param game
	 */
	public ConVarManager(Game game) {
		this.game = game;
	}

	/**
	 * Setzt den Wert einer Convar
	 * 
	 * @param name
	 *            Name der Convar
	 * @param value
	 *            Wert der Convar
	 */
	public void set(String name, String value) {
		vars.put(name, value);
	}

	/**
	 * Definiert eine Convar
	 * 
	 * @param name
	 *            Name der Convar
	 * @param defaultValue
	 *            Standartwert der Convar
	 * @param description
	 *            Beschreibung der Convar
	 */
	public void def(String name, String defaultValue, String description) {
		vars.put(name, defaultValue);
		defaults.put(name, defaultValue);
		descriptions.put(name, description);
	}

	/**
	 * Gibt eine Convar als String zur�ck
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public String getString(String name) {
		return vars.get(name);
	}

	/**
	 * Gibt eine Convar als Int zur�ck
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public int getInt(String name) {
		try {
			return Integer.parseInt(vars.get(name));
		} catch (NumberFormatException e) {
			if (getDefaultValue(name) != null)
				return Integer.parseInt(defaults.get(name));
			return 0;
		}
	}

	/**
	 * Gibt eine Convar als Double zur�ck
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public double getDouble(String name) {
		try {
			return Double.parseDouble(vars.get(name));
		} catch (NumberFormatException e) {
			if (getDefaultValue(name) != null)
				return Double.parseDouble(defaults.get(name));
			return 0.0;
		}
	}

	/**
	 * Gibt eine Convar als Boolean zur�ck
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public boolean getBoolean(String name) {
		try {
			return Integer.parseInt(vars.get(name)) != 0;
		} catch (NumberFormatException e) {
			if (getDefaultValue(name) != null)
				return Integer.parseInt(defaults.get(name)) != 0;
			return false;
		}
	}

	/**
	 * Gibt den Standartwert einer Convar zur�ck
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public String getDefaultValue(String name) {
		return defaults.get(name);
	}

	/**
	 * Gibt die Beschreibung einer Convar zur�ck
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public String getDescription(String name) {
		return descriptions.get(name);
	}

	/**
	 * Gibt die HashMap der Variablen zur�ck
	 * 
	 * @return
	 */
	public HashMap<String, String> getVars() {
		return vars;
	}

	/**
	 * Speichert die Variablen im Config Ordner in der Datei "varsafe.cfg"
	 */
	public void safeVars() {
		String commands = "";
		for (HashMap.Entry<String, String> entry : this.getVars().entrySet()) {
			String varName = entry.getKey();
			commands += "def " + varName + " " + getDefaultValue(varName) + " " + getDescription(varName) + ";\n" + "set " + varName + " " + getString(varName) + ";\n";
		}
		game.getResourceManager().writeToFile("varsafe.cfg", commands);
	}
}
