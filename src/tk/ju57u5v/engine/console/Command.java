package tk.ju57u5v.engine.console;

import tk.ju57u5v.engine.Game;

public interface Command {
	public void call(Game game, String pCode, String[] parts) ;
}
