package controller.server.network;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.server.ServerController;
import model.Figur;
import model.Spieler;
import model.Spielfeld;
import view.admin.network.ServerAdminPanel;

/**
 * Stellt die Grundlegenden Funktionalitaeten zum erstellen eines Servers
 * bereit.
 * 
 * @author ChrisWun
 * 
 */
public class ServerNetworkService {

	ArrayList<Spieler> connectedPlayer = new ArrayList<Spieler>();

	public enum ServerStatus {
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
	
	public List<ClientHandler> getClients() {
		return this.clients;
	}
	
	public ArrayList<Spieler> getConnectedPlayers() {
		return this.connectedPlayer;
	}

	public void startServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		ClientAcceptor clientAcceptor = new ClientAcceptor(serverSocket,
				clients);
		clientAcceptor.start();
		serverStatus = ServerStatus.RUNNING;
	}

	public void processInputData(ClientHandler client, String input) {
		String keyValuePairs[] = input.split(";");
		Map<String, String> orderedKeyValues = new HashMap<String, String>();

		for (String keyValuePair : keyValuePairs) {
			String keyValuePairSeperated[] = keyValuePair.split("=");
			orderedKeyValues.put(keyValuePairSeperated[0],
					keyValuePairSeperated[1]);
		}

		System.out.println("Server empfaengt: " + orderedKeyValues.toString());
		
		processInput(client, orderedKeyValues);
	}

	private void processInput(ClientHandler client, Map<String, String> orderedKeyValues) {
		Set<String> keySet = orderedKeyValues.keySet();

		for (String key : keySet) {
			DataObjectEnum dataObject = DataObjectEnum.valueOf(key);
			switch (dataObject) {
			case SPIELERNAME:
				processSpielernameInput(client, orderedKeyValues.get(key));
				break;
			case FIGURGEAENDERT:
				processFigurGeaendertInput(client, orderedKeyValues.get(key));
				break;
			default:
				// Sollte nicht auftreten
				break;
			}
		}
	}

	private void processSpielernameInput(ClientHandler client, String spielerName) {
		Spieler spieler = new Spieler(spielerName);
		client.setSpielerName(spielerName);
		connectedPlayer.add(spieler);
		ServerAdminPanel.instance
				.updateConnectedSpielerAndStartServerPanel(connectedPlayer);
	}
	
	private void processFigurGeaendertInput(ClientHandler client, String figurId) {
		Figur figur = null;
		
		for(Figur f : Spielfeld.getInstanz().getSpielfiguren().values()) {
			if(f.getId().equals(figurId)) 
				figur = f;
		}
		
		ServerController.getInstanz().bewegeFigur(figur);
	}
}
