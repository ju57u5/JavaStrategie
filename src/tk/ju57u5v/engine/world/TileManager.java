package tk.ju57u5v.engine.world;

import java.util.Random;

import tk.ju57u5v.engine.Game;

public class TileManager {
	
	private Tile[][] tiles;
	private Game game;
	
	public TileManager (Game game, int width, int height) {
		this.game=game;
		tiles = new Tile[width][height];
	}
	
	public void placeTile(Tile t, int width, int height) {
		tiles[width][height] = t;
		t.setPosition(width*Tile.TILEWIDTH, height*Tile.TILEHEIGHT);
	}
	
	public int getTileWidth() {
		return tiles.length;
	}
	
	public int getTileHeight() {
		return tiles[1].length;
	}
	
	public Tile tileAtPosition(int x, int y) {
		return tiles[(int) x/Tile.TILEWIDTH][(int) y/Tile.TILEHEIGHT];
	}
	
	public void generateWorld () {
		long seed = Math.round(Math.random() * 100 * Math.random() * 10); // Seed
		seed=50;
		PerlinGenerator generator = new PerlinGenerator();
		generator.setSeed(seed);
		float[][] heights = generator.genSmoothNoise(generator.genWhiteNoise(getTileWidth(), getTileHeight()), 5);
		
		for (int c = 0; c < getTileWidth(); c += 1) {
			for (int i = 0; i < getTileHeight(); i += 1) {
				if (Math.abs(heights[c][i])<0.3) {
					placeTile(new Tile(game, 3), c, i);
				} else {
					placeTile(new Tile(game, 0), c, i);
				}
			}
		}
	}
}
