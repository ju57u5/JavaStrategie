package tk.ju57u5v.engine.console;

import java.util.HashMap;

import tk.ju57u5v.engine.Game;

public class ConVarManager {

	/**
	 * HashMap mit den Convars
	 */
	private HashMap<String, ConVar> vars = new HashMap<String, ConVar>();

	/**
	 * Constructor
	 * 
	 */
	public ConVarManager() {
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
		vars.put(name, new ConVar(name, defaultValue, description));
	}

	/**
	 * Definiert eine Convar
	 * 
	 * @param name
	 *            Name der Convar
	 */
	public void def(String name) {
		vars.put(name, new ConVar(name));
	}

	/**
	 * Gibt die Convar anhand des Namens zurück
	 * @param name Name der Convar
	 * @return Convar
	 */
	public ConVar getConVar(String name) {
		return vars.get(name);
	}

	/**
	 * Gibt alle Convars in einer HashMap zurück.
	 * 
	 * @return ConVars
	 */
	public HashMap<String, ConVar> getVars() {
		return vars;
	}

	/**
	 * Speichert die Variablen im Config Ordner in der Datei "varsafe.cfg"
	 */
	public void safeVars() {
		String commands = "";
		for (HashMap.Entry<String, ConVar> entry : vars.entrySet()) {
			ConVar var = entry.getValue();
			commands += "def " + var.getName() + " " + var.getDefaultValue() + " " + var.getDescription() + ";\n" + "set " + var.getName() + " " + var.getString() + ";\n";
		}
		Game.getResourceManager().writeToFile("varsafe.cfg", commands);
	}
}
