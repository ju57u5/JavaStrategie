package tk.ju57u5v.engine.netcode;


public class Packet {

	public static class Packet0LoginRequest {
		String name = "Player";
	}

	public static class Packet1LoginAnswer {
		boolean accepted = false;
		int playerid = 2;
		int playercount = 2;
	}

	public static class Packet2PingRequest {
	}

	public static class Packet3PingAnswer {
		int players=0;
		int maxPlayers=2;
		boolean joinable=false;
	}

	public static class Packet4CreateNewEnity {
		String className="tk.ju57u5v.engine.Entity";
		int ownerID=1;
	}
	
	public static class Packet5CreateNewGameObject {
		String className="tk.ju57u5v.engine.GameObject";
		int ownerID=1;
	}
	
	public static class Packet6SetPosition {
		int x;
		int y;
		int z;
		/**
		 * GameObject oder Entity
		 */
		String type="gameObject";
		int ownerId;
		int objectId;
	}
	
	public static class Packet7MoveEntity {
		long tick;
		int x;
		int y;
		int speed;
		int ownerId;
		int objectId;
	}
	
	public static class Packet8Disconnect {
		int playerID;
	}
	
	public static class Packet9Kick {
		String reason = "Server is shutting down.";
	}
	
	public static class Packet10NewPlayer {
		String name;
		int playerid;
	}
}
