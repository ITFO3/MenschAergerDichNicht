package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ServerService {
	private static ServerService instance;

	ServerSocket serverSocket;

	List<ClientHandler> clients;

	public ServerService() {
		clients = new ArrayList<ClientHandler>();
	}

	public void startServer() throws IOException {
		serverSocket = new ServerSocket(8080);
		ClientAcceptor clientAcceptor = new ClientAcceptor(serverSocket, clients);
		clientAcceptor.run();
	}

	public static ServerService getInstance() {
		if (ServerService.instance == null) {
			ServerService.instance = new ServerService();
		}
		return ServerService.instance;
	}
}
