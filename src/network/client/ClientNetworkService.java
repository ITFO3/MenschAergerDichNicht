package network.client;

import java.io.PrintStream;

import network.NetworkService;
import menschaergerdichnicht.Figur;

public class ClientNetworkService {
	
	private NetworkService networkService;
	
	private static ClientNetworkService clientNetworkService;
	
	public ClientNetworkService() {
		this.networkService = NetworkService.getInstance();
	}
	
	public void sendeFigur(Figur figur){
		Client client = networkService.getClient();
		PrintStream os = client.getOutputStream();
		os.print("FIGURGEANDERT;"+figur.getId() + ";");
	}
	
	public static ClientNetworkService getInstance(){
		if(clientNetworkService == null){
			clientNetworkService = new ClientNetworkService();
		}
		return clientNetworkService;
	}
	

}
