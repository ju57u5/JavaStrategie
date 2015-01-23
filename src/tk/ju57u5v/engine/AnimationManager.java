package tk.ju57u5v.engine;

import java.util.HashMap;

public class AnimationManager {

	/**
	 * Der aktuelle Tick des Games
	 */
	private long tick;

	/**
	 * Alle Animationen in einer Hashmap, aufrufbar per QueryString
	 */
	private HashMap<String, Animation> animations = new HashMap<String, Animation>();

	/**
	 * Query des aktiven Animtationselements
	 */
	private String currentAnimation = "";

	// Methoden
	/**
	 * Erstellt eine Animation und f�gt sie dem Manager hinzu
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
	 * Setzt den Query, der auf die aktuelle Animation verwei�t
	 * 
	 * @param animationQuery
	 *            Query der Animation
	 */
	public void selectAnimation(String animationQuery) {
		currentAnimation = animationQuery;
	}

	/**
	 * Gibt das aktuelle Bild der Animation zur�ck
	 * 
	 * @return
	 */
	public String getcurrentPicture() {
		if (animations.get(currentAnimation) != null) {
			return animations.get(currentAnimation).getcurrentPicture(tick);
		}
		return "";
	}

	/**
	 * Setzt den Tick des Spiels im Manager
	 * 
	 * @param tick
	 *            Tick des Spiels
	 */
	public void setTick(long tick) {
		this.tick = tick;
	}

	/**
	 * F�gt dem Manager eine Animation hinzu
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
