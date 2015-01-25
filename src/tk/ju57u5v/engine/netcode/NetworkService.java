package tk.ju57u5v.engine.netcode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import tk.ju57u5v.engine.Game;

public class NetworkService implements Runnable {

	private ServerSocket serverSocket;
	private ExecutorService pool;
	private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
	private Game game;

	/**
	 * Constructor
	 */
	public NetworkService(ExecutorService pool, ServerSocket serverSocket, Game game) {
		this.serverSocket = serverSocket;
		this.pool = pool;
		this.game = game;
	}

	@Override
	public void run() {
		try {
			while (true) {
				/*
				 * Zunächst wird eine Client-Anforderung
				 * entgegengenommen(accept-Methode). Der ExecutorService pool
				 * liefert einen Thread, dessen run-Methode durch die
				 * run-Methode der Handler-Instanz realisiert wird. Dem Handler
				 * werden als Parameter übergeben: der ServerSocket und der
				 * Socket des anfordernden Clients.
				 */
				Socket cs = serverSocket.accept();
				ClientHandler client = new ClientHandler(this, cs, clients.size());
				pool.execute(client);
				clients.add(client);
			}
		} catch (IOException ex) {
			System.out.println("--- Interrupt NetworkService-run");
		} finally {
			System.out.println("--- Ende NetworkService(pool.shutdown)");
			pool.shutdown(); // keine Annahme von neuen Anforderungen
			try {
				// warte maximal 4 Sekunden auf Beendigung aller Anforderungen
				pool.awaitTermination(4L, TimeUnit.SECONDS);
				if (!serverSocket.isClosed()) {
					System.out.println("--- Ende NetworkService:ServerSocket close");
					serverSocket.close();
				}
			} catch (IOException e) {
			} catch (InterruptedException ei) {
			}
		}
	}
	
	public void processInput(String m, int id) {
		game.getConsole().log("Nachricht: "+m);
		if (m.trim().startsWith("disconnect")) {
			clients.remove(id);
		}
	}

}
