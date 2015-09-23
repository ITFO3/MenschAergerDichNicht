package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

class ClientAcceptor extends Thread {

	ServerSocket serverSocket;

	List<ClientHandler> clients;

	public ClientAcceptor(ServerSocket serverSocket,
			List<ClientHandler> clients) {
		this.serverSocket = serverSocket;
		this.clients = clients;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();
				ClientHandler clientHandler = new ClientHandler(
						clientSocket);
				clients.add(clientHandler);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}