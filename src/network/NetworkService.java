package network;

import com.sun.servicetag.Installer;

public class NetworkService {
	
	private static NetworkService instance;
	
	public enum networkMode {
		CLIENT,
		SERVER;
	}
	
	public void startServer() {

	}
	
	public void connectToServer() {

	}
	
	public static NetworkService getInstance() {
		if(NetworkService.instance == null){
			NetworkService.instance = new NetworkService();
		}
		return NetworkService.instance;
	}
}
