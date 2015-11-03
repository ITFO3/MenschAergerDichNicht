package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stellt die Grundlegenden Funktionalitaeten zum erstellen eines Servers bereit.
 * 
 * @author ChrisWun
 * 
 */
public class ServerNetworkService {
	public enum ServerStatus{
		NOT_RUNNING, RUNNING;
	}

	private static ServerNetworkService instance;

	public static ServerNetworkService getInstance() {
		if (ServerNetworkService.instance == null) {
			ServerNetworkService.instance = new ServerNetworkService();
		}
		return ServerNetworkService.instance;
	}
	
	List<ClientHandler> clients;
	
	ServerSocket serverSocket;

	ServerStatus serverStatus;

	public ServerNetworkService() {
		clients = new ArrayList<ClientHandler>();
		serverStatus = ServerStatus.NOT_RUNNING;
	}

	public ServerStatus getServerStatus() {
		return serverStatus;
	}

	public void setServerStatus(ServerStatus serverStatus) {
		this.serverStatus = serverStatus;
	}

	public void startServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		ClientAcceptor clientAcceptor = new ClientAcceptor(serverSocket,
				clients);
		clientAcceptor.start();
		serverStatus = ServerStatus.RUNNING;
	}

	public void processInputData(String input) {
		String keyValuePairs[] = input.split(";");
		Map<String, String> orderedKeyValues = new HashMap<String, String>();
		
		for(String keyValuePair : keyValuePairs){
			String keyValuePairSeperated[] = keyValuePair.split("=");
			orderedKeyValues.put(keyValuePairSeperated[0], keyValuePairSeperated[1]);
		}
		
		processInput(orderedKeyValues);
	}

	private void processInput(Map<String, String> orderedKeyValues) {
		
	}
}
