package controller.client.network;

import java.io.PrintStream;

import model.Figur;
import controller.NetworkService;
import controller.client.ClientController;
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

	// FIGURGEAENDERT=1,2;FIGUR
	
	public void empfangeDaten(String data) {
		String[] seperated = data.split(";");
		
		for (String seperat : seperated) {
			String[] value = seperat.split("=");
			String aenderungsTyp = value[0];
			
			if (aenderungsTyp.equals("FIGURGEAENDERT")) {
				figurGeaendert(value[1]);
			} else if (aenderungsTyp.equals("ZUGMOEGLICHKEITEN")) {
				// ClientController zeigeMoeglichkeiten
			}
		}
	}

	public void sendeSpielerName(String text) {
		Client client = networkService.getClient();
		PrintStream os = client.getOutputStream();
		os.println(DataObjectEnum.SPIELERNAME.toString() + "=" + text + ";");
		os.flush();
	}

	private void figurGeaendert(String daten) {
		// Bsp: Test:3,19
		
		String[] seperated = daten.split(",");
		String id = seperated[0];
		Integer neuePosition = Integer.valueOf(seperated[1]);
		
		ClientController.getInstance().aktualisiereSpielfeld(id, null, neuePosition);
	}
	
}
