package tk.ju57u5v.engine.netcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private Socket client;
	private NetworkService service;
	private int id;

	public ClientHandler(NetworkService service, Socket client, int id) {
		this.client = client;
		this.service = service;
		this.id=id;
	}

	@Override
	public void run() {
		while (true) {
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
				if (input.ready()) {
					String m;
					while(!(m = input.readLine()).contains("end") || m != null) {
						service.processInput(m, id);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void send(String m) {
		try {
			PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
			out.print(m);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
