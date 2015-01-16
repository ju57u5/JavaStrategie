package tk.ju57u5v.engine.console;

import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tk.ju57u5v.engine.Game;

public class Console extends JPanel implements KeyListener, WindowListener {
	private JFrame frame = new JFrame();
	private TextArea consoleOutput;
	private TextField consoleInput;
	private Game game;
	ArrayList<String> history = new ArrayList<String>();
	int historyPointer = -1;

	public Console(Game game) {
		this.game = game;
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
			String command = consoleInput.getText();
			history.add(command);
			historyPointer = history.size();
			consoleInput.setText("");
			
			String[] commands = command.split(";");
			for (String com : commands) {
				game.getCodeManager().processCode(com);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			frame.setVisible(false);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (!(historyPointer == 0)) {
				historyPointer--;
			}
			consoleInput.setText(history.get(historyPointer));
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if ((historyPointer == history.size()-1)) {
				consoleInput.setText("");
			} else {
				historyPointer++;
				consoleInput.setText(history.get(historyPointer));
			}
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
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		//	consoleInput.requestFocus();
	}
}
