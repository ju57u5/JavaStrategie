package tk.ju57u5v.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import tk.ju57u5v.engine.components.Vec2;

public class BindHandler implements KeyListener,MouseListener,MouseMotionListener {

	/**
	 * Array, das angibt, ob ein Key gerückt wurde (Index gibt den Keycode an)
	 */
	private boolean[] keyboardKeys = new boolean[1000];

	/**
	 * Array mit BindStrings (der Index gibt den Keycode an)
	 */
	private String[] keyboardBinds = new String[1000];

	/**
	 * Gibt an ob ein Mousebutten gedrückt ist.
	 */
	private boolean[] mouseButtons = new boolean [10];

	/**
	 * Array mit BindStrings
	 */
	private String[] mouseBinds = new String[10];
	/**
	 * Postion der Maus
	 */
	private Vec2 mousePosition = new Vec2(0, 0);

	@Override
	public void keyPressed(KeyEvent e) {
		keyboardKeys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyboardKeys[e.getKeyCode()] = false;
	}

	/**
	 * Prüft ob ein Bind aktiv ist
	 * 
	 * @param bind
	 *            Query des Binds
	 * @return
	 */
	public boolean bindActive(String bind) {
		for (int c = 0; c < keyboardBinds.length; c++) {
			if (keyboardBinds[c] != null) {
				if (keyboardBinds[c].equals(bind)) {
					if (keyboardKeys[c])
						return true;
				}
			}
		}
		for (int c = 0; c < mouseBinds.length; c++) {
			if (mouseBinds[c] != null) {
				if (mouseBinds[c].equals(bind)) {
					if (mouseButtons[c])
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
	 *            Keycode des Binds
	 * @param bind
	 *            Query des Binds
	 */
	public void bind(int keycode, String bind) {
		keyboardBinds[keycode] = bind;
	}
	
	public void bindMouse(int mousebutton, String bind) {
		mouseBinds[mousebutton]=bind;
	}

	/**
	 * Unbinded einen Keycode
	 * 
	 * @param keycode
	 *            Keycode des Binds
	 */
	public void unbind(int keycode) {
		keyboardBinds[keycode] = null;
	}

	/**
	 * Setzt alle Bindings auf deaktiviert
	 */
	public void unactiveAll() {
		for (int c = 0; c < keyboardKeys.length; c++) {
			keyboardKeys[c] = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseButtons[e.getButton()]=true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseButtons[e.getButton()]=false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition.x=e.getX();
		mousePosition.y=e.getY();
	}

	public Vec2 getMousePosition() {
		return mousePosition;
	}
	
	public boolean isMouseButtonDown(int button) {
		return mouseButtons[button];
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mousePosition.x=e.getX();
		mousePosition.y=e.getY();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
