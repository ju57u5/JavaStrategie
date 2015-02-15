package tk.ju57u5v.engine.netcode;

import java.io.IOException;

import tk.ju57u5v.engine.netcode.Packet.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.minlog.Log;
import tk.ju57u5v.engine.Game;

public class Server {
	
	private com.esotericsoftware.kryonet.Server server;
	
	public Server (Game game) {
		//DEBUG
		Log.set(Log.LEVEL_DEBUG);
		
		server = new com.esotericsoftware.kryonet.Server();
		registerPackets();
		server.addListener(new NetworkListener());
		server.start();
		try {
			server.bind(27015);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void registerPackets() {
		Kryo kyro = server.getKryo();
		kyro.register(Packet0LoginRequest.class);
		kyro.register(Packet1LoginAnswer.class);
		kyro.register(Packet2PingRequest.class);
		kyro.register(Packet3PingAnswer.class);
		kyro.register(Packet4CreateNewEnity.class);
	}
}
