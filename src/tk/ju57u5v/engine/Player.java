package tk.ju57u5v.engine;

public class Player {
	/**
	 * ID des Spielers
	 */
	int playerEntityID = 0;

	// Methoden
	/**
	 * Gibt die Spieler-ID zurück
	 * 
	 * @return
	 */
	public int getPlayerEntityID() {
		return playerEntityID;
	}

	/**
	 * Setzt die Spieler-ID
	 * 
	 * @param playerEntityID
	 */
	public void setPlayerEntityID(int playerEntityID) {
		this.playerEntityID = playerEntityID;
	}

}
