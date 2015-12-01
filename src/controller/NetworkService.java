package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;

import model.Figur;
import view.admin.network.ServerAdminPanel;
import controller.client.network.Client;
import controller.server.network.ClientHandler;
import controller.server.network.DataObjectEnum;
import controller.server.network.ServerNetworkService;

/**
 * Stellt die Elementaren Funktionen zum starten eines Servers, sowie zum
 * Verbinden zu einem Server bereit.
 * 
 * @author ChrisWun
 * 
 */
public class NetworkService {
	Client client;
	
	ServerAdminPanel adminPanel;

	public Client getClient() {
		return client;
	}

	ServerNetworkService serverService = ServerNetworkService.getInstance();

	public enum NetworkMode {
		CLIENT, SERVER;
	}

	private static NetworkService instance;

	public static NetworkService getInstance() {
		if (NetworkService.instance == null) {
			NetworkService.instance = new NetworkService();
		}
		return NetworkService.instance;
	}

	private NetworkMode networkMode;

	public boolean connectToServer(String host, int port) {
		try {
			client = new Client(host, port);
			client.start();
			networkMode = NetworkMode.CLIENT;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public NetworkMode getNetworkMode() {
		return networkMode;
	}

	public void setNetworkMode(NetworkMode networkMode) {
		this.networkMode = networkMode;
	}

	public boolean startServer(int port, ServerAdminPanel serverAdminPanel) {
		adminPanel = serverAdminPanel;
		try {
			serverService.startServer(port);
			networkMode = NetworkMode.SERVER;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean sendeFigurenAnClients(Figur figur) {
		String data = DataObjectEnum.FIGURGEAENDERT.toString() + "="
				+ figur.getId() + "," + figur.getPosition();
		
		for (ClientHandler client : serverService.getClients()) {
			client.sendeDaten(data);
		}
		
		return false;
	}
}
