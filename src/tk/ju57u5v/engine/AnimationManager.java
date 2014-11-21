package tk.ju57u5v.engine;

import java.util.HashMap;


public class AnimationManager {

	private long tick;
	private HashMap <String, Animation> animations = new HashMap<String, Animation>();
	private String currentAnimation = "";
	
	public void createAnimation (String animationQuery, String[] querys, int duration) {
		animations.put(animationQuery, new Animation(querys, duration));
	}
	
	public void selectAnimation(String animationQuery) {
		currentAnimation=animationQuery;
	}
	
	public String getcurrentPicture() {
		if (animations.get(currentAnimation) != null) {
			return animations.get(currentAnimation).getcurrentPicture(tick);
		}
		return "";
	}
	
	public void setTick (long tick) {
		this.tick = tick;
	}
}
