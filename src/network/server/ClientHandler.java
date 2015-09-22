package network.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler extends Thread{

	private DataInputStream is;
	private PrintStream os;
	private Socket clientSocket;

	public ClientHandler(Socket clientSocket) {
		try {
			this.clientSocket = clientSocket;
			is = new DataInputStream(clientSocket.getInputStream());
			os = new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true){
			try {
				is.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}