package tk.ju57u5v.engine.gui;

import java.awt.Graphics2D;
import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.components.Rect;
import tk.ju57u5v.engine.components.Renderable;

public class GuiElement extends Rect implements Renderable {

	/**
	 * Konstruktor
	 * 
	 * @param game
	 */
	public GuiElement() {
		initialise();
	}

	/**
	 * Intitialisiert das GuiElement
	 */
	protected void initialise() {
		Game.getRenderer().registerGuiElement(this);
	}

	/**
	 * Rendert das GuiElement. Sollte überschrieben werden.
	 * 
	 * @param g
	 */
	public void render(Graphics2D g) {

	}

	/**
	 * Wird beim Klick auf das GuiElement aufgerufen. Positionen gelten relativ
	 * zum Element und nicht den Bildschirm.
	 * 
	 * @param x
	 *            x Position auf dem GuiElement
	 * @param y
	 *            y Position auf dem GuiElement
	 */
	public void onClick(int x, int y) {

	}
}
