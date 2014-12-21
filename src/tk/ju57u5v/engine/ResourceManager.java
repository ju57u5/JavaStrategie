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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * Managed die Resourcen der Engine.
 * @author Justus
 *
 */
public class ResourceManager {
	
	private Map<String,BufferedImage> textures = new HashMap<String,BufferedImage>();
	private HashMap <String, Animation> animations = new HashMap<String, Animation>();
	
	private File appdata = new File((System.getenv("APPDATA")));
	private File gamePath = new File(appdata,"/JavaStrategie");
	private File texturePath = new File(gamePath, "/textures");
	private File cfgPath = new File(gamePath, "/cfg");
	private File config = new File(gamePath, "/cfg");	
	private Game game;
	
	public ResourceManager(Game game) {
		this.game = game;
		checkFolders();
	}
	
	
	/**
	 * Gibt eine auf den Query geladene Resource als BufferedImage zurück.
	 * @param pQuery
	 * @return
	 */
	public BufferedImage getResource(String pQuery) {
		return textures.get(pQuery);
	}
	
	/**
	 * Lädt ein Image aus dem "texture" Ordner auf den Query String. 
	 * @param pResourceName
	 * @param pQuery
	 */
	public void loadImage(String pResourceName, String pQuery) {
		File imagePath = new File(texturePath,"/"+pResourceName);
		try {
			textures.put(pQuery, ImageIO.read(imagePath));
		} catch(IOException exeption) {}
		//convertToGoodFormat(pQuery);
	}
	
	/**
	 * Gibt die Resource des Querys skaliert auf die akive Kamera zurück.
	 * @param pQuery
	 * @return
	 */
	public BufferedImage getScaledResource(String pQuery) {
		return game.kamera.scaleResource(textures.get(pQuery));
	}
	
	public int getResourceWidth(String pQuery) {
		return this.getResource(pQuery).getWidth();
	}
	
	public int getResourceHeight(String pQuery) {
		return this.getResource(pQuery).getHeight();
	}
	
	public void setDimensionsFromResource(String pQuery, Entity e) {
		e.setDimensions(getResourceWidth(pQuery), getResourceHeight(pQuery));
	}
	
	/**
	 * scale image
	 * 
	 * @param sbi image to scale
	 * @param imageType type of image
	 * @param dWidth width of destination image
	 * @param dHeight height of destination image
	 * @param fWidth x-factor for transformation / scaling
	 * @param fHeight y-factor for transformation / scaling
	 * @return scaled image
	 */
	protected static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
		BufferedImage dbi = null;
		if(sbi != null) {
			dbi = new BufferedImage(dWidth, dHeight, imageType);
			Graphics2D g = dbi.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
			g.drawRenderedImage(sbi, at);
		}
		return dbi;
	}
	
	/**
	 * Kovertiert die Resource in ein Renderfreundliches Format.
	 * @param pQuery
	 */
	private void convertToGoodFormat(String pQuery) {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		GraphicsConfiguration config = device.getDefaultConfiguration();
		BufferedImage buffy = config.createCompatibleImage(getResource(pQuery).getWidth(), getResource(pQuery).getWidth(), Transparency.TRANSLUCENT);
		
		Graphics g = buffy.getGraphics();
		g.drawImage(getResource(pQuery),0,0,null);
		g.dispose();
		textures.put(pQuery, buffy);
	
	}
	
	public String getFile(String pFileName) {
		String fileContent="";
		Scanner scanner;
		try {
			File cfgFile = new File(cfgPath,pFileName);
			scanner = new Scanner (cfgFile);
			while (scanner.hasNext ()) {
				fileContent += (scanner.nextLine ());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			
		}
		
		return fileContent;
	}
	
	private void checkFolders() {
		if (!gamePath.isDirectory()) gamePath.mkdirs(); 
		if (!texturePath.isDirectory()) texturePath.mkdirs();
		if (!cfgPath.isDirectory()) cfgPath.mkdirs();
	}
	
	public void checkConfig() {
		if (!config.isFile()) {
			/*try {
				game.updater.download("http://ju57u5v.tk/JavaStrategie/cfg/config.cfg","cfg",false);
			} catch (IOException e) {
				System.out.println("Failed to download initial config. Quiting");
				System.exit(1);
				e.printStackTrace();
			}*/
		}
	}
	
	public String getBasePath() {
		return gamePath.getAbsolutePath();
	}
	
	public void saveAnimation(String query, Animation animation) {
		animations.put(query, animation);
	}
	
	public Animation getAnimation(String query) {
		return animations.get(query);
	}
	
	public void createAnimation (String animationQuery, String[] querys, int duration) {
		animations.put(animationQuery, new Animation(querys, duration));
	}
}
