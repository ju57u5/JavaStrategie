package tk.ju57u5v.engine;

public class Animation {
	
	/**
	 * Die Bildquerys aller Bilder der Animation
	 */
	private String[] querys;
	
	/**
	 * Die L�nge an Ticks, die ein Bild angezeigt wird
	 */
	private int duration = 1;
	
	//Methoden
	/**
	 * Constructor
	 * @param querys
	 * @param duration
	 */
	public Animation (String[] querys, int duration) {
		this.querys = querys;
		this.duration = duration;
	}

	/**
	 * Gibt das aktuelle Bild der Animation zur�ck
	 * @param tick
	 * @return
	 */
	public String getcurrentPicture(long tick) {
		int timesChanged =(int) tick / duration;
		return querys[timesChanged % querys.length];
	}
	
}
