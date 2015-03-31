package tk.ju57u5v.engine.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import tk.ju57u5v.engine.Game;

public class TileManager {

	private Tile[][] tiles;

	public TileManager(int width, int height) {
		tiles = new Tile[width][height];
	}

	public void placeTile(Tile t, int width, int height) {
		tiles[width][height] = t;
		t.setPosition(width * Tile.TILEWIDTH, height * Tile.TILEHEIGHT);
	}

	public int getTileWidth() {
		return tiles.length;
	}

	public int getTileHeight() {
		return tiles[1].length;
	}

	public Tile tileAtPosition(int x, int y) {
		return tiles[(int) x / Tile.TILEWIDTH][(int) y / Tile.TILEHEIGHT];
	}

	public BufferedImage generateWorld(long seed) {
		PerlinGenerator generator = new PerlinGenerator();
		generator.setSeed(seed);
		// Erste Methode
		float[][] heights = generator.genSmoothNoise(generator.genWhiteNoise(getTileWidth(), getTileHeight()), 5);
		// Zweite Methode
		heights = generator.GeneratePerlinNoise(generator.genWhiteNoise(getTileWidth(), getTileHeight()), 2, 0.001f);
		heights = generator.genSmoothNoise(heights, 5);

		BufferedImage map = new BufferedImage(getTileWidth() * 2, getTileHeight() * 2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = map.createGraphics();

		for (int c = 0; c < getTileWidth(); c += 1) {
			for (int i = 0; i < getTileHeight(); i += 1) {
				handleHeight(c, i, heights[c][i], g);
			}
		}
		Game.getKamera().setPosition(0, 0);
		return map;
	}
	
	public BufferedImage generateWorld2(long seed) {
		BufferedImage map = new BufferedImage(getTileWidth() * 2, getTileHeight() * 2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = map.createGraphics();

		double[][] heights = DiamondSquare.diamondSquare(1025, (double) seed);
		for (int c = 0; c < getTileWidth(); c += 1) {
			for (int i = 0; i < getTileHeight(); i += 1) {
				handleHeight(c, i, (float) heights[c][i], g);
			}
		}
		Game.getKamera().setPosition(0, 0);
		return map;
	}

	public void handleHeight(int x, int y, float height, Graphics2D g) {

	}

	public Tile[][] getTiles() {
		return tiles;
	}
}
