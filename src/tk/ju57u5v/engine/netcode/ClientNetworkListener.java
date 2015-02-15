package tk.ju57u5v.engine.netcode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import tk.ju57u5v.engine.Entity;
import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.GameObject;
import tk.ju57u5v.engine.netcode.Packet.*;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientNetworkListener extends Listener {

	private Client client;
	private Game game;
	ArrayList<Player> players = new ArrayList<Player>();

	public void init(Client client, Game game) {
		this.client = client;
		this.game = game;
	}

	@Override
	public void received(Connection c, Object o) {
		if (o instanceof Packet1LoginAnswer) {
			handleLoginAnswer(c, (Packet1LoginAnswer) o);
		} else if (o instanceof Packet4CreateNewEnity) {
			handleNewEntity((Packet4CreateNewEnity) o);
		} else if (o instanceof Packet5CreateNewGameObject) {
			handleNewGameObject((Packet5CreateNewGameObject) o);
		} else if (o instanceof Packet6SetPosition) {

		} else if (o instanceof Packet7MoveEntity) {

		} else if (o instanceof Packet8Disconnect) {
			handleDisconnect((Packet8Disconnect) o);
		} else if (o instanceof Packet9Kick) {
			//Nothing
		} else if (o instanceof Packet10NewPlayer) {
			handleNewPlayer((Packet10NewPlayer) o);
		}
	}

	@Override
	public void connected(Connection arg0) {
		System.out.println("You Connected");
		client.sendTCP(new Packet0LoginRequest());
	}

	@Override
	public void disconnected(Connection arg0) {
		System.out.println("You Disconnected");
	}

	private Object createClass(String className) {
		try {
			Class<?> newClass = Class.forName(className);
			Constructor<?> constructor = newClass.getDeclaredConstructor(Game.class);
			return constructor.newInstance(game);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Player getPlayer(int id) {
		for (int c=0;c<players.size();c++) {
			if (players.get(c).getPlayerID()==id) {
				return players.get(c);
			}
		}
		return null;
	}
	
	private void handleNewPlayer(Packet10NewPlayer packet) {
		Player player = new Player();
		player.setPlayerID(packet.playerid);
		player.setName(packet.name);
		players.add(player);
	}

	private void handleNewEntity(Packet4CreateNewEnity packet) {
		Entity entity = (Entity) createClass(packet.className);
		getPlayer(packet.ownerID).getEntities().add(entity);
	}

	private void handleNewGameObject(Packet5CreateNewGameObject packet) {
		GameObject gameObject = (GameObject) createClass(packet.className);
		getPlayer(packet.ownerID).getGameObjects().add(gameObject);
	}

	private void handleDisconnect(Packet8Disconnect packet) {
		players.remove(packet.playerID);
	}
	
	private void handleLoginAnswer(Connection c, Packet1LoginAnswer packet) {
		if (packet.accepted) {
			Player player = new Player();
			player.setPlayerID(packet.playerid);
			players.add(player);
		} else {
			c.close();
		}
	}
}
