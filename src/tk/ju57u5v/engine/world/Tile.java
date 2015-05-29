package tk.ju57u5v.engine.world;

import java.awt.Graphics2D;

import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.components.GameObject;
import static tk.ju57u5v.engine.Game.*;

public class Tile extends GameObject {

	public static int TILEWIDTH=30;
	public static int TILEHEIGHT=30;
	private boolean walkable=true;
	private int texture = 1;
	private String tileTexture="tile_grass";

	public Tile(int texture) {
		super();

		initialise();
		
		switch (texture) {
		case 0:
			tileTexture="tile_grass";
			break;
		case 1:
			tileTexture="tile_snow";
			break;
		case 2:
			tileTexture="tile_sand";
			break;
		case 3:
			tileTexture="tile_water";
			break;
		case 4:
			tileTexture="tile_stone";
			break;
		}
		Game.getResourceManager().setDimensionsFromResource(tileTexture, this);
	}

	@Override
	public void render(Graphics2D g) {
		getResourceManager().getResource(tileTexture).draw(g, getRelativIsoX(), getRelativIsoY());
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
}
