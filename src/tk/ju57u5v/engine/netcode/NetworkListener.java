package tk.ju57u5v.engine.netcode;

import java.util.ArrayList;

import tk.ju57u5v.engine.netcode.Packet.*;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class NetworkListener extends Listener {
	ArrayList<Connection> connections = new ArrayList<Connection>();

	@Override
	public void received(Connection c, Object o) {
		if (o instanceof Packet0LoginRequest) {
			handleLogin(c, o);
		} else if (o instanceof Packet4CreateNewEnity) {
			handleNewEntity(c, (Packet4CreateNewEnity) o);
		} else if (o instanceof Packet5CreateNewGameObject) {
			handleNewGameObject(c, (Packet5CreateNewGameObject) o);
		}
	}

	@Override
	public void connected(Connection arg0) {
		Log.info("[Server] Client connected");
		connections.add(arg0);
	}

	@Override
	public void disconnected(Connection arg0) {
		connections.remove(arg0);
	}

	private void handleLogin(Connection c, Object o) {
		Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
		loginAnswer.accepted = true;
		loginAnswer.playerid = c.getID();
		loginAnswer.playercount = connections.size();
		c.sendTCP(loginAnswer);

		Packet10NewPlayer newPlayer = new Packet10NewPlayer();
		newPlayer.name = ((Packet0LoginRequest) o).name;
		newPlayer.playerid = c.getID();
		for (Connection connection : connections) {
			if (connection != c)
				connection.sendTCP(loginAnswer);
		}
	}

	private void handleNewEntity(Connection c, Packet4CreateNewEnity packet) {
		packet.ownerID = c.getID();
		for (Connection connection : connections) {
			connection.sendTCP(packet);
		}
	}

	private void handleNewGameObject(Connection c, Packet5CreateNewGameObject packet) {
		packet.ownerID = c.getID();
		for (Connection connection : connections) {
			connection.sendTCP(packet);
		}
	}
}
