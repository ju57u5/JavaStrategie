package tk.ju57u5v.engine;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import tk.ju57u5v.engine.animation.Animation;
import tk.ju57u5v.engine.components.Entity;
import tk.ju57u5v.engine.components.GameObject;
import tk.ju57u5v.engine.graphics.Sprite;
import tk.ju57u5v.engine.gui.GuiElement;

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
	private Map<String, Sprite> sprites = new HashMap<String, Sprite>();

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
	private File gamePath;

	/**
	 * TexturenPfad des Spiels
	 */
	private File texturePath;

	/**
	 * Config Dateien Pfad des Spiels
	 */
	private File cfgPath;

	/**
	 * Config des Spiels
	 */
	private File config;

	// Methoden
	/**
	 * Constructor
	 * 
	 * @param game
	 *            Hauptklasse des Spiels
	 */
	public ResourceManager() {
	}

	public void setupFiles() {
		gamePath = new File(appdata, "/" + Game.name);
		texturePath = new File(gamePath, "/textures");
		cfgPath = new File(gamePath, "/cfg");
		config = new File(gamePath, "/cfg/config.cfg");
		checkFolders();
	}

	/**
	 * Gibt eine auf den Query geladene Resource als BufferedImage zur�ck.
	 * 
	 * @param pQuery
	 *            Query der Textur
	 * @return
	 */
	public Sprite getSprite(String pQuery) {
		return sprites.get(pQuery);
	}

	/**
	 * L�dt ein Image aus dem "texture" Ordner auf den Query String.
	 * 
	 * @param imageName
	 *            Name der Textur
	 * @param query
	 *            Query der Textur
	 */
	public void loadImage(String imageName, String query) {
		File imagePath = new File(texturePath, "/" + imageName);
		//Ordner vor Classpath
		if (imagePath.isFile()) {
			try {
				sprites.put(query, new Sprite(ImageIO.read(imagePath)));
				convertToGoodFormat(query);
			} catch (IOException exeption) {
				exeption.printStackTrace();
				Game.console.log("");
			}
		} else {
			System.out.println(imageName);
			URL url = Game.game.getClass().getResource("/textures/"+imageName);
			loadImageFromURL(url, query);
			convertToGoodFormat(query);
		}
	}

	/**
	 * L�dt eine Image von einer Url
	 * 
	 * @param url
	 *            URL des Images
	 * @param query
	 *            Query der Textur
	 */
	public void loadImageFromURL(URL url, String query) {
		try {
			sprites.put(query, new Sprite(ImageIO.read(url)));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Game.console.log("URL is not valid!");
		} catch (IOException e) {
			e.printStackTrace();
			Game.console.log("Couldn't download Image!");
		}
	}

	/**
	 * Gibt die Breite einer Textur zur�ck
	 * 
	 * @param pQuery
	 *            Query der Textur
	 * @return
	 */
	public int getResourceWidth(String pQuery) {
		return this.getSprite(pQuery).getWidth();
	}

	/**
	 * Gibt die H�he einer Textur zur�ck
	 * 
	 * @param pQuery
	 *            Query der Textur
	 * @return
	 */
	public int getResourceHeight(String pQuery) {
		return this.getSprite(pQuery).getHeight();
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
	 * Setzt die Dimensionen eines GuiElements anhand seiner Textur
	 * 
	 * @param pQuery
	 *            Query der Textur
	 * @param e
	 *            GuiElement dessen Dimensionen gesetzt werden
	 */
	public void setDimensionsFromResource(String pQuery, GuiElement e) {
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

		// Scheint auf Schulpcs zu Problemen zu kommen wenn es nicht eingehalten
		// wird
		if (env == null || device == null || config == null)
			return;

		if (getSprite(pQuery).getImage().getColorModel().equals(config.getColorModel()))
			return;

		BufferedImage buffy = config.createCompatibleImage(getSprite(pQuery).getWidth(), getSprite(pQuery).getHeight(), Transparency.TRANSLUCENT);

		Graphics2D g = (Graphics2D) buffy.getGraphics();
		g.drawImage(getSprite(pQuery).getImage(), 0, 0, null);
		g.dispose();
		sprites.put(pQuery, new Sprite(buffy));
	}

	/**
	 * L�dt eine Textdatei aus dem Configordner und gibt den Inhalt als String
	 * zur�ck
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
	 * L�dt eine Textdatei aus einem Inputstream und gibt den Inhalt als String
	 * zur�ck
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
	 * �berpr�ft ob <code>gamePath</code>, <code>texturePath</code>,
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
	 * �berpr�ft ob die Config existiert und downloaded sie gegebenenfalls
	 */
	public void checkConfig() {
		if (!config.isFile()) {
			try {
				Game.updater.download("http://ju57u5v.tk/JavaStrategie/cfg/config.cfg", "cfg", false, null);
			} catch (IOException e) {
				System.out.println("Failed to download initial config. Quiting");
				System.exit(1);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gibt den GamePfad zur�ck
	 * 
	 * @return
	 */
	public String getBasePath() {
		return gamePath.getAbsolutePath();
	}

	/**
	 * Gibt den ConfigPfad zur�ck
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
	 * Gibt eine Animation zur�ck
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
			writer = new PrintWriter(Game.getResourceManager().getCfgPath() + "/" + filename, "UTF-8");
			writer.println(s);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
