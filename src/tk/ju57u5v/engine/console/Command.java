package tk.ju57u5v.engine.console;

import tk.ju57u5v.engine.Game;

public interface Command {
	
	/**
	 * Methode, die beim ausf�hren des Kommandos ausgef�hrt wird
	 * @param game
	 * @param pCode
	 * @param parts
	 */
	public void call(Game game, String pCode, String[] parts) ;
}
