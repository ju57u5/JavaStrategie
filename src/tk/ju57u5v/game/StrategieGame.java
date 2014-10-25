package tk.ju57u5v.game;

import tk.ju57u5v.engine.Game;



public class StrategieGame extends Game {
	
	public StrategieGame() {
		gameRunner= new GameRunner(this);
		Dorfzentrum dorfzentrum = new Dorfzentrum(this);
		dorfzentrum.setPosition(100, 100);
	}
	
	public static void main(String [ ] args)
	{
		new StrategieGame();
	}

	
}
