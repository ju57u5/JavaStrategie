package tk.ju57u5v.game;

import tk.ju57u5v.engine.Game;

public class GameRunner extends tk.ju57u5v.engine.GameRunner {

	public GameRunner(Game game) {
		super(game);
	}
	
	@Override
	public void work() {
		doKeys();
	}
	
	private void doKeys() {
		if (game.getBindHandler().bindActive("+left")) game.getKamera().setX(game.getKamera().getX()+5);
		if (game.getBindHandler().bindActive("+right")) game.getKamera().setX(game.getKamera().getX()-5);
		if (game.getBindHandler().bindActive("+up")) game.getKamera().setY(game.getKamera().getY()+5);
		if (game.getBindHandler().bindActive("+down")) game.getKamera().setY(game.getKamera().getY()-5);
		
	}
}
