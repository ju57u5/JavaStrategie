package tk.ju57u5v.engine.netcode;

import tk.ju57u5v.engine.netcode.Packet.Packet0LoginRequest;
import tk.ju57u5v.engine.netcode.Packet.Packet1LoginAnswer;
import tk.ju57u5v.engine.netcode.Packet.Packet4Message;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkListener extends Listener{

	@Override
	public void received(Connection c, Object o) {
		if (o instanceof Packet0LoginRequest) {
			Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
			loginAnswer.accepted=true;
			c.sendTCP(loginAnswer);
		} else if (o instanceof Packet4Message) {
			Packet4Message packet = (Packet4Message) o;
			String message = packet.message;
			System.out.println(message);
		}
	}
	
	@Override
	public void connected(Connection arg0) {
		System.out.println("Connecting");
	}
	
	@Override
	public void disconnected(Connection arg0) {
		System.out.println("Disconnecting");
	}
}
