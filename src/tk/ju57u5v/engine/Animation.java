package tk.ju57u5v.engine;

public class Animation {
	
	private String[] querys;
	private int duration = 1;
	int currentAnimation = 0;
	
	public Animation (String[] querys, int duration) {
		this.querys = querys;
		this.duration = duration;
	}

	public String getcurrentPicture(long tick) {
		int timesChanged =(int) tick / duration;
		return querys[timesChanged % querys.length];
	}
	
}
