package tk.ju57u5v.engine.console;

import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tk.ju57u5v.engine.Game;

public class Console extends JPanel implements KeyListener, WindowListener {
	private JFrame frame = new JFrame();
	private TextArea consoleOutput;
	private TextField consoleInput;
	private Game game;
	private ArrayList<String> history = new ArrayList<String>();
	private int historyPointer = -1;
	private ConVarManager conVarManager;

	public Console(Game game) {
		this.game = game;
		
		conVarManager = new ConVarManager(game);
		
		frame.setTitle("Console"); // Fenstertitel setzen
		frame.setSize(900, 600);
		frame.addWindowListener(this);
		frame.setLocationRelativeTo(null);
		frame.setVisible(false);
		frame.setIgnoreRepaint(true);
		frame.add(this);
		setLocation(0, 0);
		this.setLayout(null);

		consoleOutput = new TextArea("", 900, 500, TextArea.SCROLLBARS_VERTICAL_ONLY);
		consoleOutput.setEditable(false);
		consoleOutput.setBounds(0, 0, 900, 525);

		consoleInput = new TextField(1);
		consoleInput.setBounds(0, 540, 900, 20);
		//Tab wechselt nicht den Focus
		consoleInput.setFocusTraversalKeysEnabled(false);

		consoleInput.addKeyListener(this);
		consoleOutput.addKeyListener(this);
		frame.addKeyListener(this);

		this.add(consoleOutput);
		this.add(consoleInput);
	}

	public void log(String log) {
		consoleOutput.append("\n" + log);
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			processCommand();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			frame.setVisible(false);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			history("UP");
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			history("DOWN");
		} else if (e.getKeyCode() == KeyEvent.VK_TAB) {
			autoVervollstaendigung();
			e.consume();//Keine weitere Verarbeitung als Zeichen
		}
	}
	
	private void autoVervollstaendigung() {
		boolean found = false;
		for (HashMap.Entry<String, Command> entry : game.getCodeManager().getCommands().entrySet()) {
			if (entry.getKey().startsWith(consoleInput.getText())) {
				consoleInput.setText(entry.getKey());
				found=true;
			}
		}
		if (!found) { 
			for (HashMap.Entry<String, String> entry : game.getConsole().getConVarManager().getVars().entrySet()) {
				if (entry.getKey().startsWith(consoleInput.getText())) {
					consoleInput.setText(entry.getKey());
					found=true;
				}
			}
		}
		if (found) consoleInput.setCaretPosition(consoleInput.getText().length());
	}
	
	private void history(String direction) {
		if (direction.equals("UP")) {
			if (!(historyPointer == 0)) {
				historyPointer--;
			}
			consoleInput.setText(history.get(historyPointer));
		} else {
			if ((historyPointer == history.size()-1)) {
				consoleInput.setText("");
			} else {
				historyPointer++;
				consoleInput.setText(history.get(historyPointer));
			}
		}
	}
	
	private void processCommand() {
		String command = consoleInput.getText();
		history.add(command);
		historyPointer = history.size();
		consoleInput.setText("");
		this.log("]"+command);
		
		String[] commands = command.split(";");
		for (String com : commands) {
			game.getCodeManager().processCode(com);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		consoleInput.requestFocus();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
	
	public String getString(String name) {
		return conVarManager.getString(name);
	}
	
	public int getInt(String name) {
		return conVarManager.getInt(name);
	}
	
	public double getDouble(String name) {
		return conVarManager.getDouble(name);
	}
	
	public boolean getBoolean(String name) {
		return conVarManager.getBoolean(name);
	}
	
	public void set (String name, String value) {
		conVarManager.set(name, value);
	}

	public ConVarManager getConVarManager() {
		return conVarManager;
	}
	
	public void def(String name, String defaultValue, String description) {
		conVarManager.def(name, defaultValue, description);
	}
	
	public void logVarInfo(String name) {
		String value = getString(name);
		String defaultValue = conVarManager.getDefaultValue(name);
		String description = conVarManager.getDescription(name);
		game.getConsole().log(name + " == '" + value + "' (def: "+defaultValue+") - "+description);
	}
}
