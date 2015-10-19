package network.client;

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

	public Client(String host, int port) throws UnknownHostException,
			IOException {
		clientSocket = new Socket(host, port);
		os = new PrintStream(clientSocket.getOutputStream());
		is = new DataInputStream(clientSocket.getInputStream());
	}

	@Override
	public void run() {
		while (true) {
			try {
				is.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
