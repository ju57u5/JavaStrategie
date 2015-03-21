package tk.ju57u5v.engine.console;

import java.util.HashMap;

import tk.ju57u5v.engine.Game;

public class ConVarManager {

	/**
	 * HashMap mit den Convars
	 */
	private HashMap<String, String> vars = new HashMap<String, String>();

	/**
	 * HashMap mit den Default Zuständen der Convars
	 */
	private HashMap<String, String> defaults = new HashMap<String, String>();

	/**
	 * HashMap mit den Beschreibungen der Convars
	 */
	private HashMap<String, String> descriptions = new HashMap<String, String>();

	/**
	 * Verknüpfung zur Hauptklasse
	 */
	private Game game;

	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
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
		// Habe mich gegen ein Setzen entschieden, da so def möglich ist ohne
		// den Wert der Convar zu verändern. Das ist toll, denn so können wir
		// eine Datei mit allen nötigen Convars erstellen, die auch nach dem
		// Varsafe ausgeführt werden kann.
		defaults.put(name, defaultValue);
		descriptions.put(name, description);
	}

	/**
	 * Gibt eine Convar als String zurück
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public String getString(String name) {
		return vars.get(name);
	}

	/**
	 * Gibt eine Convar als Int zurück
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public int getInt(String name) {
		try {
			return Integer.parseInt(vars.get(name));
		} catch (NumberFormatException e) {
			if (getDefaultValue(name) != null) {
				try {
					return Integer.parseInt(defaults.get(name));
				} catch (NumberFormatException ex) {
					Game.getConsole().log(name+" is no int. Returning 0.");
				}
			}
			return 0;
		}
	}

	/**
	 * Gibt eine Convar als Double zurück
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public double getDouble(String name) {
		try {
			return Double.parseDouble(vars.get(name));
		} catch (NumberFormatException e) {
			if (getDefaultValue(name) != null) {
				try {
					return Double.parseDouble(defaults.get(name));
				} catch (NumberFormatException ex) {
					Game.getConsole().log(name+" is no double. Returning 0.");
				}
			}
			return 0.0;
		}
	}

	/**
	 * Gibt eine Convar als Boolean zurück
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
	 * Gibt den Standartwert einer Convar zurück
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public String getDefaultValue(String name) {
		return defaults.get(name);
	}

	/**
	 * Gibt die Beschreibung einer Convar zurück
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public String getDescription(String name) {
		return descriptions.get(name);
	}

	/**
	 * Gibt die HashMap der Variablen zurück
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
		Game.getResourceManager().writeToFile("varsafe.cfg", commands);
	}
}
