package tk.ju57u5v.engine.console;

import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tk.ju57u5v.engine.Game;

public class Console extends JPanel implements KeyListener, WindowListener {

	/**
	 * Verknüpfung zur Hauptklasse
	 */
	private Game game;

	/**
	 * Frame des Updaters
	 */
	private JFrame frame = new JFrame();

	/**
	 * Output TextArea der Console
	 */
	private TextArea consoleOutput;

	/**
	 * Input TextField der Console
	 */
	private TextField consoleInput;

	/**
	 * History der eingegebenen Commandos in einer ArrayList
	 */
	private ArrayList<String> history = new ArrayList<String>();

	/**
	 * Pointer der history
	 */
	private int historyPointer = -1;

	/**
	 * Manager der ConsolenVariablen
	 */
	private ConVarManager conVarManager;

	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public Console(Game game) {
		this.game = game;

		conVarManager = new ConVarManager(game);
		frame.setUndecorated(true);
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
		// Tab wechselt nicht den Focus
		consoleInput.setFocusTraversalKeysEnabled(false);

		consoleInput.addKeyListener(this);
		consoleOutput.addKeyListener(this);
		frame.addKeyListener(this);

		this.add(consoleOutput);
		this.add(consoleInput);
	}

	/**
	 * Logt einen Text in der Konsole
	 * 
	 * @param log
	 *            Text der geloggt wird
	 */
	public void log(String log) {
		consoleOutput.append("\n" + log);
	}

	/**
	 * Gibt das Fenster der Console zurück
	 * 
	 * @return Fenster der Console
	 */
	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Handelt die Consolen Tastatur Inputs
	 */
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
			e.consume();// Keine weitere Verarbeitung als Zeichen
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE && (!consoleInput.getSelectedText().equals(""))) {
			autoVervollstaendigung();
			;
		}
		if (e.getKeyCode() != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_CONTROL)
			vorschlag(e);
	}

	/**
	 * Autovervollständigt den Kommando im consoleInput
	 */
	private void autoVervollstaendigung() {
		consoleInput.select(consoleInput.getText().length(), consoleInput.getText().length());
		consoleInput.setCaretPosition(consoleInput.getText().length());
	}

	/**
	 * Durchsucht die Kommandos und Variablen nach einem Vorschlag und fügt ihn
	 * Makiert ein
	 * 
	 * @param e
	 *            KeyEvent der Eingabe
	 */
	private void vorschlag(KeyEvent e) {
		int eingabelaenge = consoleInput.getText().length();
		if (!consoleInput.getSelectedText().equals("")) {
			eingabelaenge = consoleInput.getSelectionStart();

			if (consoleInput.getText().charAt(consoleInput.getSelectionStart()) == e.getKeyChar()) {
				consoleInput.select(consoleInput.getSelectionStart() + 1, consoleInput.getText().length());
				e.consume();
			}

		}

		else if (eingabelaenge != 0) {
			boolean found = false;
			for (HashMap.Entry<String, Command> entry : Game.getCodeManager().getCommands().entrySet()) {
				if (entry.getKey().startsWith(consoleInput.getText() + e.getKeyChar()) && !entry.getKey().equals(consoleInput.getText() + e.getKeyChar())) {
					consoleInput.setText(entry.getKey());
					consoleInput.select(eingabelaenge + 1, consoleInput.getText().length());
					e.consume();
					found = true;
					break;
				}
			}
			if (!found) {
				for (HashMap.Entry<String, String> entry : Game.getConsole().getConVarManager().getVars().entrySet()) {
					if (entry.getKey().startsWith(consoleInput.getText() + e.getKeyChar()) && !entry.getKey().equals(consoleInput.getText() + e.getKeyChar())) {
						consoleInput.setText(entry.getKey());
						consoleInput.select(eingabelaenge + 1, consoleInput.getText().length());
						e.consume();
						found = true;
						break;
					}
				}
			}
		}
	}

	/**
	 * Setzt die History in consoleInput ein.
	 * 
	 * @param direction
	 *            "UP" oder "DOWN"
	 */
	private void history(String direction) {
		if (direction.equals("UP")) {
			if (!(historyPointer <= 0)) {
				historyPointer--;
				consoleInput.setText(history.get(historyPointer));
			}
		} else {
			if ((historyPointer == history.size() - 1)) {
				consoleInput.setText("");
			} else {
				historyPointer++;
				consoleInput.setText(history.get(historyPointer));
			}
		}
	}

	/**
	 * Führt einen Kommando aus consoleInput aus
	 */
	private void processCommand() {
		String command = consoleInput.getText();
		history.add(command);
		historyPointer = history.size();
		consoleInput.setText("");
		this.log("]" + command);

		String[] commands = command.split(";");
		for (String com : commands) {
			Game.getCodeManager().processCode(com);
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

	/**
	 * Gibt eine Convar als String zurück
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public String getString(String name) {
		return conVarManager.getString(name);
	}

	/**
	 * Gibt eine Convar als Int zurück
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public int getInt(String name) {
		return conVarManager.getInt(name);
	}

	/**
	 * Gibt eine Convar als Double zurück
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public double getDouble(String name) {
		return conVarManager.getDouble(name);
	}

	/**
	 * Gibt eine Convar als Boolean zurück
	 * 
	 * @param name
	 *            Name der Convar
	 * @return
	 */
	public boolean getBoolean(String name) {
		return conVarManager.getBoolean(name);
	}

	/**
	 * Setzt eine Convar
	 * 
	 * @param name
	 *            Name der Convar
	 * @param value
	 *            Wert der Convar
	 */
	public void set(String name, String value) {
		conVarManager.set(name, value);
	}

	/**
	 * Gibt den ConvarManager zurück
	 * 
	 * @return ConvarManager des Spiels
	 */
	public ConVarManager getConVarManager() {
		return conVarManager;
	}

	/**
	 * Definiert eine Variable
	 * 
	 * @param name
	 *            Name der Convar
	 * @param defaultValue
	 *            Standartwert der Variable
	 * @param description
	 *            Beschreibung der Convar
	 */
	public void def(String name, String defaultValue, String description) {
		conVarManager.def(name, defaultValue, description);
	}

	/**
	 * Loggt den Infotext einer Variable in der Console
	 * 
	 * @param name
	 *            Name der Convar
	 */
	public void logVarInfo(String name) {
		String value = getString(name);
		String defaultValue = conVarManager.getDefaultValue(name);
		String description = conVarManager.getDescription(name);
		Game.getConsole().log(name + " == '" + value + "' (def: " + defaultValue + ") - " + description);
	}
}
