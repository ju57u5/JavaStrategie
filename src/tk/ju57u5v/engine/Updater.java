package tk.ju57u5v.engine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Updater extends JFrame {

	private int downloadedBytes = 0;
	private JProgressBar progressBar;
	private String basePath;
	private ArrayList<String> downloadUrls = new ArrayList<String>();
	private ArrayList<String> downloadPaths = new ArrayList<String>();

	class WindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().dispose();
			System.exit(0);
		}
	}

	public Updater(String basePath) {
		this.basePath = basePath;
		setTitle("Updating");
		setSize(200, 100);
		 setLocationRelativeTo(null); 
	}

	public void download(String fileURL, String destinationDirectory, boolean processBarEnabled) throws IOException {
		// File name that is being downloaded
		String downloadedFileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
		
		File folder = new File (basePath + "/" + destinationDirectory + "/");
		if (!folder.isDirectory()) folder.mkdirs();
		
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

	public void registerUpdatableFile(String downloadUrl, String downloadPath) {
		downloadUrls.add(downloadUrl);
		downloadPaths.add(downloadPath);
	}

	public void updateFiles() {
		progressBar = new JProgressBar(0, getUpdateSize());
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		this.add(progressBar);
		setVisible(true);
		
		for (int c = 0; c < downloadUrls.size(); c++) {
			try {
				download(downloadUrls.get(c), downloadPaths.get(c), true);
				System.out.println("Download of "+downloadUrls.get(c)+" done!");
			} catch (IOException e) {
				System.out.println("Download of "+downloadUrls.get(c)+" failed!");
			} 
		}
		dispose();
	}

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

	private int getUpdateSize() {
		int size=0;
		for (int c = 0; c < downloadUrls.size(); c++) {
			try {
				size += getFileSize(downloadUrls.get(c));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return size;
	}

}
