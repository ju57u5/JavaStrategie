package tk.ju57u5v.engine.console;

import tk.ju57u5v.engine.Game;

public interface Command {

	/**
	 * Methode, die beim ausf�hren des Kommandos ausgef�hrt wird
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 * @param pCode
	 *            Kommando als ganzer String
	 * @param parts
	 *            Kommando aufgeteil nach Leerzeichen
	 */
	public void call(Game game, String pCode, String[] parts);
}
