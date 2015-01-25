package tk.ju57u5v.engine.netcode;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tk.ju57u5v.engine.Game;

public class Server {

	private ExecutorService pool = Executors.newCachedThreadPool();
	private ServerSocket serverSocket;
	
	
	public Server (Game game) {
		try {
			serverSocket = new ServerSocket(27015);
			new Thread(new NetworkService(pool, serverSocket, game)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
