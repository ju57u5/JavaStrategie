package tk.ju57u5v.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BindHandler implements KeyListener {
	ArrayList<BindListener> listeners = new ArrayList<BindListener>();
	protected boolean[] pressedKeys = new boolean[1000];
	protected String[] binds = new String[1000];

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		pressedKeys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressedKeys[e.getKeyCode()] = false;
	}

	public boolean bindActive(String bind) {
		for (int c = 0; c < binds.length; c++) {
			if (binds[c] != null) {
				if (binds[c].equals(bind)) {
					return pressedKeys[c];
				}
			}
		}
		return false;
	}

	public void bind(int keycode, String bind) {
		binds[keycode] = bind;
	}

}
