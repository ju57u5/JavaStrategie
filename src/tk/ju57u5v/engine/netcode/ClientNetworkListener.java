package tk.ju57u5v.engine.netcode;

import tk.ju57u5v.engine.netcode.Packet.Packet0LoginRequest;
import tk.ju57u5v.engine.netcode.Packet.Packet1LoginAnswer;
import tk.ju57u5v.engine.netcode.Packet.Packet4Message;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientNetworkListener extends Listener{

	private Client client;
	
	public void init(Client client) {
		this.client=client;
	}
	
	@Override
	public void received(Connection c, Object o) {
		if (o instanceof Packet1LoginAnswer) {
			boolean answer = ((Packet1LoginAnswer) o).accepted;
			if (answer) {
				
			} else {
				c.close();
			}
		} else if (o instanceof Packet4Message) {
			Packet4Message packet = (Packet4Message) o;
			String message = packet.message;
			System.out.println(message);
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
}
