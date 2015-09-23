package network;

import java.io.IOException;
import java.net.UnknownHostException;

import network.client.Client;

public class NetworkService {
	Client client;
	
	public enum NetworkMode {
		CLIENT,
		SERVER;
	}
	
	private static NetworkService instance;
	
	public static NetworkService getInstance() {
		if(NetworkService.instance == null){
			NetworkService.instance = new NetworkService();
		}
		return NetworkService.instance;
	}

	private NetworkMode networkMode;

	public void connectToServer(String host, int port) {
		try {
			networkMode = NetworkMode.CLIENT;
			client = new Client(host, port);
			client.run();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public NetworkMode getNetworkMode() {
		return networkMode;
	}
	
	public void setNetworkMode(NetworkMode networkMode) {
		this.networkMode = networkMode;
	}
	
	public void startServer() {
		networkMode = NetworkMode.SERVER;
	}
}
