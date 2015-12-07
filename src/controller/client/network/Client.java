package controller.client.network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Ein Thread der Clientseitig laeuft, und auf Daten wartet welche vom Server an
 * den Client versendet werden.
 * 
 * @author ChrisWun
 * 
 */
public class Client extends Thread implements Runnable {

	private ClientNetworkService clientNetworkService;
	
	private Socket clientSocket;
	
	private BufferedReader is;
	
	private PrintStream os;

	private String spielerName;

	public Client(String host, int port) throws UnknownHostException, IOException {
		clientNetworkService = ClientNetworkService.getInstance();
		clientSocket = new Socket(host, port);
		os = new PrintStream(clientSocket.getOutputStream());
		is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	public PrintStream getOutputStream() {
		return os;
	}

	public String getSpielerName() {
		return spielerName;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String data = is.readLine();
				clientNetworkService.empfangeDaten(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setSpielerName(String name) {
		this.spielerName = name;
	}

}
