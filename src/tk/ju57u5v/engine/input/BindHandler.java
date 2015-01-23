package tk.ju57u5v.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BindHandler implements KeyListener {

	/**
	 * Listener der Tastaturbindigs
	 */
	ArrayList<BindListener> listeners = new ArrayList<BindListener>();

	/**
	 * Array, das angibt, ob ein Key gerückt wurde (Index gibt den Keycode an)
	 */
	protected boolean[] pressedKeys = new boolean[1000];

	/**
	 * Array mit BindStrings (der Index gibt den Keycode an)
	 */
	protected String[] binds = new String[1000];

	// TODO Mit Hashmap ersetzen

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		pressedKeys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressedKeys[e.getKeyCode()] = false;
	}

	/**
	 * Prüft ob ein Bind aktiv ist
	 * 
	 * @param bind
	 * @return
	 */
	public boolean bindActive(String bind) {
		for (int c = 0; c < binds.length; c++) {
			if (binds[c] != null) {
				if (binds[c].equals(bind)) {
					if (pressedKeys[c])
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * Binded einen KeyCode zu einem String
	 * 
	 * @param keycode
	 * @param bind
	 */
	public void bind(int keycode, String bind) {
		binds[keycode] = bind;
	}

	/**
	 * Unbinded einen Keycode
	 * 
	 * @param keycode
	 */
	public void unbind(int keycode) {
		binds[keycode] = null;
	}

	/**
	 * Setzt alle Bindings auf deaktiviert
	 */
	public void unactiveAll() {
		for (int c = 0; c < pressedKeys.length; c++) {
			pressedKeys[c] = false;
		}
	}
}
