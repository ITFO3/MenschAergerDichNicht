package controller;

import java.io.IOException;
import java.net.UnknownHostException;

import controller.client.network.Client;
import controller.server.network.ServerNetworkService;
import view.admin.network.ServerAdminPanel;

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
}
