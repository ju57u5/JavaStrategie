package tk.ju57u5v.engine;

public class Animation {

	/**
	 * Die Bildquerys aller Bilder der Animation
	 */
	private String[] querys;

	/**
	 * Die Länge an Ticks, die ein Bild angezeigt wird
	 */
	private int duration = 1;

	// Methoden
	/**
	 * Constructor
	 * 
	 * @param querys
	 *            Querys der Bilder der Animation
	 * @param duration
	 *            Länge an Ticks, die ein Bild gezeigt wird
	 */
	public Animation(String[] querys, int duration) {
		this.querys = querys;
		this.duration = duration;
	}

	/**
	 * Gibt das aktuelle Bild der Animation zurück
	 * 
	 * @param tick
	 *            Tick des Spiels
	 * @return
	 */
	public String getcurrentPicture(long tick) {
		int timesChanged = (int) tick / duration;
		return querys[timesChanged % querys.length];
	}

}
