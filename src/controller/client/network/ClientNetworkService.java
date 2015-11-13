package controller.client.network;

import java.io.PrintStream;

import model.Figur;
import controller.NetworkService;
import controller.server.network.DataObjectEnum;

/**
 * @author ChrisWun
 */
public class ClientNetworkService {

	private NetworkService networkService;

	private static ClientNetworkService clientNetworkService;

	public ClientNetworkService() {
		this.networkService = NetworkService.getInstance();
	}

	public void sendeFigur(Figur figur) {
//		Client client = networkService.getClient();
//		PrintStream os = client.getOutputStream();
//		os.print("FIGURGEANDERT;" + figur.getId() + ";");
	}

	public static ClientNetworkService getInstance() {
		if (clientNetworkService == null) {
			clientNetworkService = new ClientNetworkService();
		}
		return clientNetworkService;
	}

	public void empfangeDaten(String data) {
		String[] seperated = data.split(";");
		String aenderungsTyp = seperated[0];

		if (aenderungsTyp.equals("FIGURGEANDERT")) {
			// ClientController aktualisiereSpielfeld
		} else if (aenderungsTyp.equals("ZUGMOEGLICHKEITEN")) {
			// ClientController zeigeMoeglichkeiten
		}
	}

	public void sendeSpielerName(String text) {
		Client client = networkService.getClient();
		PrintStream os = client.getOutputStream();
		os.println(DataObjectEnum.SPIELERNAME.toString() + "=" + text + ";");
		os.flush();
	}

}
