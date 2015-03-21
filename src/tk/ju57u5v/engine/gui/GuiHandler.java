package tk.ju57u5v.engine.gui;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.TwoDMath;
import tk.ju57u5v.engine.input.MouseListener;

public class GuiHandler implements MouseListener {

	private Game game;

	/**
	 * Konstruktor
	 * 
	 * @param game
	 */
	public GuiHandler(Game game) {
		this.game = game;
		Game.getMouseHandler().addMouseListener(this);
	}

	@Override
	public void mousedrag(int startx, int starty, int endx, int endy, MouseEvent e) {

	}

	@Override
	public void mouseclick(int x, int y, MouseEvent e) {
		for (int c = 0; c < Game.getGameRunner().getRenderer().getGuiElements().size(); c++) {
			GuiElement g = Game.getGameRunner().getRenderer().getGuiElements().get(c);
			if (TwoDMath.isInRect(x, y, g.getX(), g.getY(), g.getWidth(), g.getHeight()))
				g.onClick(x-g.getX(), y-g.getY());
		}
	}
}
