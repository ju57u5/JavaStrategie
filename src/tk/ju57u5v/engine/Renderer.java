package tk.ju57u5v.engine;

import java.util.ArrayList;

public class Renderer {
	
	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private boolean render = true;
	private boolean update = true;
	
	public void registerEntity (Entity pEntity) {
		entities.add(pEntity);
	}
	
	public void update () {
		if (render && update) {
			updateAndRenderEntities();
			renderGameObjects();
		} else if (update) {
			updateEntities();
		} else if (render) {
			renderEntities();
			renderGameObjects();
		}
	}
	
	private void updateAndRenderEntities () {
		for (Entity ent : entities) {
			ent.update();
			ent.render();
		}
	}
	
	private void updateEntities () {
		for (Entity ent : entities) {
			ent.update();
		}
	}
	
	private void renderEntities () {
		for (Entity ent : entities) {
			ent.render();
		}
	}
	
	private void renderGameObjects () {
		for (GameObject gameObject : gameObjects) {
			gameObject.render();
		}
	}
	
	public void doRender(boolean render) {
		this.render=render;
	}
	
	public void doUpdate(boolean update) {
		this.update=update;
	}
}
