package tk.ju57u5v.engine.netcode;

import java.io.IOException;
import java.util.Scanner;

import com.esotericsoftware.kryo.Kryo;

import tk.ju57u5v.engine.Game;
import tk.ju57u5v.engine.netcode.Packet.*;

public class Client{

	private com.esotericsoftware.kryonet.Client client;
	private Game game;
	
	public Client(Game game) {
		this.game = game;
		client = new com.esotericsoftware.kryonet.Client();
		registerPackets();
		ClientNetworkListener networkListener = new ClientNetworkListener();
		networkListener.init(client);
		client.addListener(networkListener);
		client.start();
		try {
			client.connect(5000, "127.0.0.1", 27015);
		} catch (IOException e) {
			client.stop();
			e.printStackTrace();
		}
	}
	
	private void registerPackets() {
		Kryo kyro = client.getKryo();
		kyro.register(Packet0LoginRequest.class);
		kyro.register(Packet1LoginAnswer.class);
		kyro.register(Packet2PingRequest.class);
		kyro.register(Packet3PingAnswer.class);
		kyro.register(Packet4Message.class);
	}
}
