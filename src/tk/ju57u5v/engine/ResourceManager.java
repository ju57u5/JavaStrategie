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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ResourceManager {
	
	Map<String,BufferedImage> textures = new HashMap<String,BufferedImage>();
	File appdata = new File((System.getenv("APPDATA")));
	File gamePath = new File(appdata,"/JavaStrategie");
	File texturePath = new File(gamePath, "/textures");
	Game game;
	
	public ResourceManager(Game game) {
		this.game = game;
	}
	
	public BufferedImage getResource(String pQuery) {
		return textures.get(pQuery);
	}
	
	public void loadImage(String pResourceName, String pQuery) {
		File imagePath = new File(texturePath,"/"+pResourceName);
		try {
			textures.put(pQuery, ImageIO.read(imagePath));
		} catch(IOException exeption) {}
		convertToGoodFormat(pQuery);
	}
	
	public BufferedImage getScaledResource(String pQuery) {
		return game.kamera.scaleResource(textures.get(pQuery));
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
	public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
		BufferedImage dbi = null;
		if(sbi != null) {
			dbi = new BufferedImage(dWidth, dHeight, imageType);
			Graphics2D g = dbi.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
			g.drawRenderedImage(sbi, at);
		}
		return dbi;
	}
	
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
}
