package tk.ju57u5v.engine;

import java.util.HashMap;

public class AnimationManager {

	/**
	 * Alle Animationen in einer Hashmap, aufrufbar per QueryString
	 */
	private HashMap<String, Animation> animations = new HashMap<String, Animation>();

	/**
	 * Query des aktiven Animtationselements
	 */
	private String currentAnimation = "";
	
	/**
	 * Verknüpfung zur Hauptklasse des Spiels
	 */
	private Game game;

	// Methoden
	/**
	 * Constructor
	 * @param game Object des Spiels
	 */
	public AnimationManager (Game game) {
		this.game=game;
	}
	/**
	 * Erstellt eine Animation und fügt sie dem Manager hinzu
	 * 
	 * @param animationQuery
	 *            Query der Animation
	 * @param querys
	 *            Bildquerys im ResourcenManager
	 * @param duration
	 *            Dauer eines Bildes der Animation
	 */
	public void createAnimation(String animationQuery, String[] querys, int duration) {
		animations.put(animationQuery, new Animation(querys, duration));
	}

	/**
	 * Setzt den Query, der auf die aktuelle Animation verweißt
	 * 
	 * @param animationQuery
	 *            Query der Animation
	 */
	public void selectAnimation(String animationQuery) {
		currentAnimation = animationQuery;
	}

	/**
	 * Gibt das aktuelle Bild der Animation zurück
	 * 
	 * @return
	 */
	public String getcurrentPicture() {
		if (animations.get(currentAnimation) != null) {
			return animations.get(currentAnimation).getcurrentPicture(game.gameRunner.ticks);
		}
		return "";
	}

	/**
	 * Fügt dem Manager eine Animation hinzu
	 * 
	 * @param animationQuery
	 *            Query der Animation
	 * @param animation
	 *            Animation
	 */
	public void putAnimationString(String animationQuery, Animation animation) {
		animations.put(animationQuery, animation);
	}
}
