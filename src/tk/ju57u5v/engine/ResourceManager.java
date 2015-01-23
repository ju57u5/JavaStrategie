package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * Managed die Resourcen der Engine.
 * 
 * @author Justus
 *
 */
public class ResourceManager {

	/**
	 * Texturen in einer HashMap
	 */
	private Map<String, BufferedImage> textures = new HashMap<String, BufferedImage>();

	/**
	 * Animationen in einer HashMap
	 */
	private HashMap<String, Animation> animations = new HashMap<String, Animation>();

	/**
	 * Pfad zu Appdata
	 */
	private File appdata = new File((System.getenv("APPDATA")));

	/**
	 * BasisPfad des Spiels
	 */
	private File gamePath = new File(appdata, "/JavaStrategie");

	/**
	 * TexturenPfad des Spiels
	 */
	private File texturePath = new File(gamePath, "/textures");

	/**
	 * Config Dateien Pfad des Spiels
	 */
	private File cfgPath = new File(gamePath, "/cfg");

	/**
	 * Config des Spiels
	 */
	private File config = new File(gamePath, "/cfg/config.cfg");

	/**
	 * Verknüpfung zur Hauptklasse
	 */
	private Game game;

	// Methoden
	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public ResourceManager(Game game) {
		this.game = game;
		checkFolders();
	}

	/**
	 * Gibt eine auf den Query geladene Resource als BufferedImage zurück.
	 * 
	 * @param pQuery
	 *            Query der Textur
	 * @return
	 */
	public BufferedImage getResource(String pQuery) {
		return textures.get(pQuery);
	}

	/**
	 * Lädt ein Image aus dem "texture" Ordner auf den Query String.
	 * 
	 * @param pResourceName
	 *            Name der Textur
	 * @param pQuery
	 *            Query der Textur
	 */
	public void loadImage(String pResourceName, String pQuery) {
		File imagePath = new File(texturePath, "/" + pResourceName);
		try {
			textures.put(pQuery, ImageIO.read(imagePath));
			convertToGoodFormat(pQuery);
		} catch (IOException exeption) {
		}
	}

	/**
	 * Gibt die Resource des Querys skaliert auf die akive Kamera zurück.
	 * 
	 * @param pQuery
	 *            Query der Textur
	 * @return
	 */
	public BufferedImage getScaledResource(String pQuery) {
		return game.kamera.scaleResource(textures.get(pQuery));
	}

	/**
	 * Gibt die Breite einer Textur zurück
	 * 
	 * @param pQuery
	 *            Query der Textur
	 * @return
	 */
	public int getResourceWidth(String pQuery) {
		return this.getResource(pQuery).getWidth();
	}

	/**
	 * Gibt die Höhe einer Textur zurück
	 * 
	 * @param pQuery
	 *            Query der Textur
	 * @return
	 */
	public int getResourceHeight(String pQuery) {
		return this.getResource(pQuery).getHeight();
	}

	/**
	 * Setzt die Dimensionen eines Entitys anhand seiner Textur
	 * 
	 * @param pQuery
	 *            Query der Textur
	 * @param e
	 *            Entity dessen Dimensionen gesetzt werden
	 */
	public void setDimensionsFromResource(String pQuery, Entity e) {
		e.setDimensions(getResourceWidth(pQuery), getResourceHeight(pQuery));
	}

	/**
	 * Setzt die Dimensionen eines GameObjects anhand seiner Textur
	 * 
	 * @param pQuery
	 *            Query der Textur
	 * @param e
	 *            GameObject dessen Dimensionen gesetzt werden
	 */
	public void setDimensionsFromResource(String pQuery, GameObject e) {
		e.setDimensions(getResourceWidth(pQuery), getResourceHeight(pQuery));
	}

	/**
	 * scale image
	 * 
	 * @param sbi
	 *            image to scale
	 * @param imageType
	 *            type of image
	 * @param dWidth
	 *            width of destination image
	 * @param dHeight
	 *            height of destination image
	 * @param fWidth
	 *            x-factor for transformation / scaling
	 * @param fHeight
	 *            y-factor for transformation / scaling
	 * @return scaled image
	 */
	protected static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
		BufferedImage dbi = null;
		if (sbi != null) {
			dbi = new BufferedImage(dWidth, dHeight, imageType);
			Graphics2D g = dbi.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
			g.drawRenderedImage(sbi, at);
		}
		return dbi;
	}

	/**
	 * Kovertiert die Resource in ein Renderfreundliches Format.
	 * 
	 * @param pQuery
	 *            Query der Textur
	 */
	private void convertToGoodFormat(String pQuery) {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		GraphicsConfiguration config = device.getDefaultConfiguration();

		// Schein auf Schulpcs zu Problemen zu kommen wenn es nicht eingehalten
		// wird
		if (env == null || device == null || config == null)
			return;

		if (getResource(pQuery).getColorModel().equals(config.getColorModel()))
			return;

		BufferedImage buffy = config.createCompatibleImage(getResource(pQuery).getWidth(), getResource(pQuery).getHeight(), getResource(pQuery).getTransparency());

		Graphics2D g = (Graphics2D) buffy.getGraphics();
		g.drawImage(getResource(pQuery), 0, 0, null);
		g.dispose();
		textures.put(pQuery, buffy);

	}

	/**
	 * Lädt eine Textdatei aus dem Configordner und gibt den Inhalt als String
	 * zurück
	 * 
	 * @param pFileName
	 *            Name der Config-Datei
	 * @return
	 */
	public String getFile(String pFileName) {
		String fileContent = "";
		Scanner scanner;
		try {
			File cfgFile = new File(cfgPath, pFileName);
			scanner = new Scanner(cfgFile);
			while (scanner.hasNext()) {
				fileContent += (scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {

		}

		return fileContent;
	}

	/**
	 * Lädt eine Textdatei aus einem Inputstream und gibt den Inhalt als String
	 * zurück
	 * 
	 * @param stream
	 *            Stream der Datei
	 * @return
	 */
	public String getFile(InputStream stream) {
		String fileContent = "";
		Scanner scanner;

		scanner = new Scanner(stream);
		while (scanner.hasNext()) {
			fileContent += (scanner.nextLine());
		}
		scanner.close();

		return fileContent;
	}

	/**
	 * Überprüft ob <code>gamePath</code>, <code>texturePath</code>,
	 * <code>cfgPath</code> existieren
	 */
	private void checkFolders() {
		if (!gamePath.isDirectory())
			gamePath.mkdirs();
		if (!texturePath.isDirectory())
			texturePath.mkdirs();
		if (!cfgPath.isDirectory())
			cfgPath.mkdirs();
	}

	/**
	 * Überprüft ob die Config existiert und downloaded sie gegebenenfalls
	 */
	public void checkConfig() {
		if (!config.isFile()) {
			try {
				game.updater.download("http://ju57u5v.tk/JavaStrategie/cfg/config.cfg", "cfg", false);
			} catch (IOException e) {
				System.out.println("Failed to download initial config. Quiting");
				System.exit(1);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gibt den GamePfad zurück
	 * 
	 * @return
	 */
	public String getBasePath() {
		return gamePath.getAbsolutePath();
	}

	/**
	 * Gibt den ConfigPfad zurück
	 * 
	 * @return
	 */
	public String getCfgPath() {
		return cfgPath.getAbsolutePath();
	}

	/**
	 * Speichert eine Animation global
	 * 
	 * @param query
	 *            Query der Animation
	 * @param animation
	 *            Animation die gespeichert wird
	 */
	public void saveAnimation(String query, Animation animation) {
		animations.put(query, animation);
	}

	/**
	 * Gibt eine Animation zurück
	 * 
	 * @param query
	 *            Query der Animation
	 * @return
	 */
	public Animation getAnimation(String query) {
		return animations.get(query);
	}

	/**
	 * Erstellt eine Animation und Speichert sie
	 * 
	 * @param animationQuery
	 *            Query der Animation
	 * @param querys
	 *            Querys der Texturen der Animation
	 * @param duration
	 *            Ticks, die ein Bild der Animation angezeigt wird
	 */
	public void createAnimation(String animationQuery, String[] querys, int duration) {
		animations.put(animationQuery, new Animation(querys, duration));
	}

	/**
	 * Schreibt den String s in eine Datei im Config Ordner mit dem Namen
	 * filename
	 * 
	 * @param filename
	 *            Name der Datei
	 * @param s
	 *            Inhalt der in die Datei geschrieben wird
	 */
	public void writeToFile(String filename, String s) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(game.getResourceManager().getCfgPath() + "/" + filename, "UTF-8");
			writer.println(s);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
