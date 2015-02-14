package tk.ju57u5v.engine.netcode;

public class Packet {

	public static class Packet0LoginRequest { }
	public static class Packet1LoginAnswer { boolean accepted = false; }
	public static class Packet2PingRequest { }
	public static class Packet3PingAnswer { }
	public static class Packet4Message { String message;}
}
