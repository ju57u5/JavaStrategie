package tk.ju57u5v.engine.netcode;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import tk.ju57u5v.engine.Game;

public class Client {

	private Socket clientSocket;
	
	public Client(Game game, String ip, int port) {
		try {
			clientSocket = new Socket(ip, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String m) {
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			printWriter.println(m);
			printWriter.println("end");
			printWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
