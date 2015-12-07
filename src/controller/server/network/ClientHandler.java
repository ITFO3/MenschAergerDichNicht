package controller.server.network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Ein Thread der fuer jeden Client erstellt wird. Dieser Thread wartet auf Daten
 * die vom Client an den Server gesendet werden.
 * 
 * @author ChrisWun
 * 
 */
public class ClientHandler extends Thread {

	private BufferedReader is;
	private PrintStream os;
	private Socket clientSocket;
	private ServerNetworkService serverService;
	private String spielerName;
	
	public String getSpielerName() {
		return this.spielerName;
	}
	
	public void setSpielerName(String name) {
		this.spielerName = name;
	}

	public ClientHandler(Socket clientSocket) {
		try {
			this.clientSocket = clientSocket;
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		serverService = ServerNetworkService.getInstance();
		start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				String input = is.readLine();
				serverService.processInputData(this, input);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public PrintStream getOutputStream() {
		return os;
	}

	public void sendeDaten(String data) {
		System.out.println("Sende an Clients: " + data);
		
		this.os.println(data);
		this.os.flush();
	}

}
