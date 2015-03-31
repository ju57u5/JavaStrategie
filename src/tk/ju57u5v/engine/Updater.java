package tk.ju57u5v.engine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Updater extends JFrame {

	/**
	 * Anzahl der gedownloadeten Bytes des Updates
	 */
	private int downloadedBytes = 0;

	/**
	 * ProgessBar des Updaters
	 */
	private JProgressBar progressBar;

	/**
	 * Basis Pfad des Spiels
	 */
	private String basePath;

	/**
	 * Download Urls des Updates
	 */
	private ArrayList<String> downloadUrls = new ArrayList<String>();

	/**
	 * Pfade der Update Downloads
	 */
	private ArrayList<String> downloadPaths = new ArrayList<String>();

	/**
	 * Update Window-Listener
	 * 
	 * @author Justus
	 *
	 */
	class WindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().dispose();
			System.exit(0);
		}
	}

	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 * @param basePath
	 *            BasisPfad des Spiels
	 */
	public Updater() {
		this.basePath = Game.resourceManager.getBasePath();
		setTitle("Updating");
		setSize(200, 100);
		setLocationRelativeTo(null);
	}

	/**
	 * Downloaded eine Datei in den Basispfad
	 * 
	 * @param fileURL
	 *            Url der Datei
	 * @param destinationDirectory
	 *            Destiantion der Datei
	 * @param processBarEnabled
	 *            Wird die Prozessbar geupdated
	 * @throws IOException
	 *             Fall die Datei nicht gespeichert werden kann
	 */
	public void download(String fileURL, String destinationDirectory, boolean processBarEnabled) throws IOException {
		// File name that is being downloaded
		String downloadedFileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);

		File folder = new File(basePath + "/" + destinationDirectory + "/");
		if (!folder.isDirectory())
			folder.mkdirs();

		// Open connection to the file
		URL url = new URL(fileURL);
		InputStream is = url.openStream();

		// Stream to the destionation file
		FileOutputStream fos = new FileOutputStream(basePath + "/" + destinationDirectory + "/" + downloadedFileName);

		// Read bytes from URL to the local file
		byte[] buffer = new byte[4096];
		int bytesRead = 0;
		while ((bytesRead = is.read(buffer)) != -1) {
			downloadedBytes += bytesRead;
			if (processBarEnabled) {
				this.progressBar.setValue(downloadedBytes);
			}
			fos.write(buffer, 0, bytesRead);
		}

		// Close destination stream
		fos.close();
		// Close URL stream
		is.close();
	}

	/**
	 * Registriert eine Datei als Update
	 * 
	 * @param downloadUrl
	 *            Url von der die Datei gedownloaded wird
	 * @param downloadPath
	 *            Pfad zu der die Datei gedownloaded wird
	 */
	public void registerUpdatableFile(String downloadUrl, String downloadPath) {
		downloadUrls.add(downloadUrl);
		downloadPaths.add(downloadPath);
	}

	/**
	 * Führt das Update durch
	 * 
	 * @param forceUpdate
	 *            Wird das Update erzwungen
	 */
	public void updateFiles(boolean forceUpdate) {
		if (!Game.console.isConVar("currentVersion") || Game.console.getConVar("version").getInt() > Game.console.getConVar("currentVersion").getInt() || forceUpdate) {
			progressBar = new JProgressBar(0, getUpdateSize());
			progressBar.setValue(0);
			progressBar.setStringPainted(true);
			this.add(progressBar);
			setVisible(true);

			for (int c = 0; c < downloadUrls.size(); c++) {
				try {
					download(downloadUrls.get(c), downloadPaths.get(c), true);
					Game.console.log("Download of " + downloadUrls.get(c) + " done!");
				} catch (IOException e) {
					Game.console.log("Download of " + downloadUrls.get(c) + " failed!");
				}
			}
			if (!Game.console.isConVar("currentVersion"))
				Game.console.def("currentVersion");
			Game.console.getConVar("currentVersion").setValue(Game.console.getConVar("version").getString());
			Game.getConsole().getConVarManager().safeVars();
			
			try {
				if (false)
					Game.game.startUpdatedJar();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.dispose();
	}

	/**
	 * Fragt die Dateigröße vom Webserver ab
	 * 
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	private int getFileSize(String urlString) throws IOException {
		int size;
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		size = conn.getContentLength();
		if (size < 0) {
			return 0;
		} else {
			conn.getInputStream().close();
			return size;
		}
	}

	/**
	 * Ermittelt die Gesamtgröße des Updates
	 * 
	 * @return
	 */
	private int getUpdateSize() {
		int size = 0;
		for (int c = 0; c < downloadUrls.size(); c++) {
			try {
				size += getFileSize(downloadUrls.get(c));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return size;
	}
	
	public void copyUpdate () {
		File update = new File(basePath,"/update/Game.jar");
		File game = new File (basePath,"/Game.jar");
		game.delete();
		try {
			Files.copy(update.toPath(), game.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
