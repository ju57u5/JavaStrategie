package tk.ju57u5v.game;

import java.awt.event.KeyEvent;

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
		if (game.isKeyPressed(KeyEvent.VK_LEFT)) game.getKamera().setX(game.getKamera().getX()-1);
		if (game.isKeyPressed(KeyEvent.VK_RIGHT)) game.getKamera().setX(game.getKamera().getX()+1);
		if (game.isKeyPressed(KeyEvent.VK_UP)) game.getKamera().setY(game.getKamera().getY()-1);
		if (game.isKeyPressed(KeyEvent.VK_DOWN)) game.getKamera().setY(game.getKamera().getY()+1);
		
	}
}
