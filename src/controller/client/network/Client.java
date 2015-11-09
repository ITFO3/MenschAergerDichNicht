package controller.client.network;

import java.io.DataInputStream;
import java.io.IOException;
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

	private static Socket clientSocket;
	private static PrintStream os;
	private static DataInputStream is;
	
	private ClientNetworkService clientNetworkService;

	public Client(String host, int port) throws UnknownHostException,
			IOException {
		clientNetworkService = ClientNetworkService.getInstance();
		clientSocket = new Socket(host, port);
		os = new PrintStream(clientSocket.getOutputStream());
		is = new DataInputStream(clientSocket.getInputStream());
	}

	@Override
	public void run() {
		while (true) {
			try {
				String data = is.readUTF();
				clientNetworkService.empfangeDaten(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public PrintStream getOutputStream() {
		return os;
	}

}
