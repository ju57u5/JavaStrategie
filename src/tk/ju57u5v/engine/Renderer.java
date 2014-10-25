package tk.ju57u5v.engine;

import java.awt.Graphics;
import java.util.ArrayList;

public class Renderer {

	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	private boolean render = true;
	private boolean update = true;
	private Game game;

	public Renderer(Game game) {
		this.game = game;
	}

	public void registerEntity(Entity pEntity) {
		entities.add(pEntity);
		game.kamera.setRelativPostion(pEntity);
	}

	public void update(Graphics g) {
		if (render && update) {
			updateAndRenderEntities(g);
			renderGameObjects(g);
		} else if (update) {
			updateEntities();
		} else if (render) {
			renderEntities(g);
			renderGameObjects(g);
		}
	}

	private void updateAndRenderEntities(Graphics g) {
		for (Entity ent : entities) {
			ent.update();
			if (game.kamera.isRenderNeeded(ent)) {
				ent.render(g);
			}
		}
	}

	private void updateEntities() {
		for (Entity ent : entities) {
			ent.update();
		}
	}

	private void renderEntities(Graphics g) {
		for (Entity ent : entities) {
			if (game.kamera.isRenderNeeded(ent)) {
				ent.render(g);
			}
		}
	}

	private void renderGameObjects(Graphics g) {
		for (GameObject gameObject : gameObjects) {
			gameObject.render(g);
		}
	}

	public void doRender(boolean render) {
		this.render = render;
	}

	public void doUpdate(boolean update) {
		this.update = update;
	}
}
