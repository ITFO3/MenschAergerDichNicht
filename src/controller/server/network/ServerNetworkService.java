package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Stellt die Grundlegenden Funktionalitaeten zum erstellen eines Servers bereit.
 * 
 * @author ChrisWun
 * 
 */
public class ServerNetworkService {
	private static ServerNetworkService instance;

	ServerSocket serverSocket;

	List<ClientHandler> clients;

	public ServerNetworkService() {
		clients = new ArrayList<ClientHandler>();
	}

	public void startServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		ClientAcceptor clientAcceptor = new ClientAcceptor(serverSocket,
				clients);
		clientAcceptor.start();
	}

	public static ServerNetworkService getInstance() {
		if (ServerNetworkService.instance == null) {
			ServerNetworkService.instance = new ServerNetworkService();
		}
		return ServerNetworkService.instance;
	}
}
