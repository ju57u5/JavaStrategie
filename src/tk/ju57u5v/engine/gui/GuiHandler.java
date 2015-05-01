package tk.ju57u5v.engine.gui;

import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.TwoDMath;
import tk.ju57u5v.engine.components.Vec2;

public class GuiHandler{

	/**
	 * Konstruktor
	 */
	public GuiHandler() {
	}

	public void update() {
		if (Game.getBindHandler().isMouseButtonDown(1)) {
			Vec2 mousePosition = Game.getBindHandler().getMousePosition();
			for (int c = 0; c < Game.getRenderer().getGuiElements().size(); c++) {
				GuiElement g = Game.getRenderer().getGuiElements().get(c);
				if (TwoDMath.isInRect((int) mousePosition.x, (int) mousePosition.y, g.getX(), g.getY(), g.getWidth(), g.getHeight()))
					g.onClick((int) mousePosition.x-g.getX(),(int) mousePosition.y-g.getY());
			}
		}
	}
}
