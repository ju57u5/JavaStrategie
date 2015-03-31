package tk.ju57u5v.engine.console;

import tk.ju57u5v.engine.Game;

public class ConVar {
	private String name;
	private String value = "";
	private String defaultValue = "0";
	private String description = "";


	public ConVar(String name) {
		this(name, "0", "A normal Convar.");
	}

	public ConVar(String name, String defaultValue, String description) {
		this.name = name;
		this.defaultValue = defaultValue;
		this.description = description;
	}

	/**
	 * Gibt die Convar als String zurück.
	 * 
	 * @return Wert der Convar
	 */
	public String getString() {
		return value;
	}

	/**
	 * Gibt die Convar als Integer zurück. Kommanzahlen werden gerundet, ist die
	 * Convar ein String wird 0 zurückgegeben
	 * 
	 * @return Wert der Convar
	 */
	public int getInt() {
		try {
			return (int) Math.floor(Double.parseDouble(value));
		} catch (NumberFormatException e) {
			if (defaultValue != null) {
				try {
					return (int) Math.floor(Double.parseDouble(defaultValue));
				} catch (NumberFormatException ex) {
					Game.getConsole().log("This is no double. Returning 0.");
				}
			}
			return 0;
		}
	}

	/**
	 * Gibt die Convar als Double zurück. Ist die Convar keine Double, wird 0.0
	 * zurückgegeben.
	 * 
	 * @return Wert der Convar
	 */
	public double getDouble() {
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			if (defaultValue != null) {
				try {
					return Double.parseDouble(defaultValue);
				} catch (NumberFormatException ex) {
					Game.getConsole().log("This is no double. Returning 0.");
				}
			}
			return 0.0;
		}
	}

	/**
	 * Setzt den Wert der Convar
	 * 
	 * @param value
	 *            Wert der Convar
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Setzt den Wert der Convar
	 * 
	 * @param value
	 *            Wert der Convar
	 */
	public void setValue(int value) {
		this.value = value + "";
	}

	/**
	 * Setzt den Wert der Convar
	 * 
	 * @param value
	 *            Wert der Convar
	 */
	public void setValue(double value) {
		this.value = value + "";
	}

	/**
	 * Gibt die Beschreibung der ConVar zurück.
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setzt die Beschreibung der Convar.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gibt den Standartwert der ConVar zurück.
	 * @return
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Gbit den Namen der ConVar zurück.
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Loggt den Infotext der ConVar in der Console
	 * 
	 * @param name
	 *            Name der Convar
	 */
	public void logVarInfo() {
		Game.getConsole().log(name + " == '" + value + "' (def: " + defaultValue + ") - " + description);
	}

	/**
	 * Gibt die Convar als Boolean zurück. 1=true alles Andere=false
	 * 
	 * @return Wert der Convar
	 */
	public boolean getBoolean() {
		try {
			return Integer.parseInt(value) != 0;
		} catch (NumberFormatException e) {
			if (defaultValue != null)
				return Integer.parseInt(defaultValue) != 0;
			return false;
		}
	}
}
