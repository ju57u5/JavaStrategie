package tk.ju57u5v.engine;

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
	
	public BufferedImage getResource(String pQuery) {
		return textures.get(pQuery);
	}
	
	public void loadImage(String pResourceName, String pQuery) {
		File imagePath = new File(texturePath,"/"+pResourceName);
		try {
			textures.put(pQuery, ImageIO.read(imagePath));
		} catch(IOException exeption) {}
	}
}
