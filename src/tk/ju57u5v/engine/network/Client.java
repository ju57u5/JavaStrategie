package tk.ju57u5v.engine.network;

import java.io.IOException;
import com.esotericsoftware.kryo.Kryo;
import tk.ju57u5v.engine.network.Packet.*;

public class Client {

	private com.esotericsoftware.kryonet.Client client;

	public Client() {
		client = new com.esotericsoftware.kryonet.Client();
		registerPackets();
		ClientNetworkListener networkListener = new ClientNetworkListener();
		networkListener.init(client);
		client.addListener(networkListener);
		
	}

	private void registerPackets() {
		Kryo kyro = client.getKryo();
		kyro.register(Packet0LoginRequest.class);
		kyro.register(Packet1LoginAnswer.class);
		kyro.register(Packet2PingRequest.class);
		kyro.register(Packet3PingAnswer.class);
		kyro.register(Packet4CreateNewEnity.class);
	}

	public void connect(String ip, int port) {
		client.start();
		try {
			client.connect(5000, ip, port);
		} catch (IOException e) {
			client.stop();
			e.printStackTrace();
		}
	}

	public void requestEntityCreate(Class<?> entityClass) {
		Packet4CreateNewEnity packet = new Packet4CreateNewEnity();
		packet.className = entityClass.getName();
		client.sendTCP(packet);
	}
}
