package controller.server;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.SpielfeldCanvas;
import controller.NetworkService;
import controller.server.network.ClientHandler;
import controller.server.network.ServerNetworkService;
import model.Figur;
import model.Spieler;
import model.Spielfeld;
import model.Wuerfel;

public class ServerController {

	
	private static ServerController instance;
	private static int spielerZaehler = 0;
	private static ServerController instanz;
	
	private int wurfAnzahl;
	private List<Spieler> spieler;
	
	public static ServerController getInstance() {
		if (instance == null) {
			instance = new ServerController();
		}
		
		return instance;
	}
	
	private ServerController() { }

	public void beendeSpiel() {
		spielerZaehler = 0;
	}
	
	public int gibAnzahl() {
		return wurfAnzahl;
	}

	public void starteSpiel() {
		
		int beginner = Wuerfel.ermittleBeginner(spieler.size());
		
		
		while(ueberpruefeSpielende() == null) {
			
			wurfAnzahl = Wuerfel.wuerfel();
			ArrayList<Figur> moeglichkeiten = ueberpruefeMoeglichkeiten(spieler.get(beginner), wurfAnzahl);
			sendeMoeglichkeitenAnClienten(spieler.get(beginner++), moeglichkeiten);
			
		
			if(beginner > spieler.size()) beginner -=  spieler.size();
		}
	}

	public void setSpieler(ArrayList<Spieler> spieler){
		this.spieler = spieler;
	}

	public void initialisiereSpieler(String name) 
		{
	//		int zufallsZahl = (int) (Math.random() * ((farben.size() * 1.0) - 1.0) + 1.0);
	//		Color c = farben.get(zufallsZahl);
	//		farben.remove(zufallsZahl);
	//
	//		int zielfeld = zielFelder.get(spielerZaehler);
	//		
	//		int[] startfelder = startFelder.get(spielerZaehler);
	//		
	//		spieler.add(new Spieler(name, c, zielfeld, startfelder, ip));
	//		spielerZaehler++;
		}

	public ArrayList<Figur> ueberpruefeMoeglichkeiten(Spieler spieler, int anzahl) {
	
		ArrayList<Figur> figuren = spieler.getFiguren();
		ArrayList<Figur> bewegbareFiguren = new ArrayList<Figur>();
		
		for (Figur figur : figuren) {
			
			//TODO: ueberpruefe Figur
			bewegbareFiguren.add(figur);
		} 
		
		return bewegbareFiguren;
	}

	private void sendeMoeglichkeitenAnClienten(Spieler s, ArrayList<Figur> figuren, int wurfanzahl) {
		List<ClientHandler> clients = ServerNetworkService.getInstance().getClients();
		ClientHandler client = null;
		
		for (ClientHandler c : clients) {
			if (c.getSpielerName().equals(s.getName())) {
				client = c;
			}
		}
		
		NetworkService.getInstance().sendeMoeglichkeitenAnClient(client, figuren, wurfanzahl);
	}

	/**
	 * Ueberprueft ob das Spielende erreicht wurde Aeussere Schleife:
	 * Ueberprueft jeden Spieler Innere Schleife: Ueberprueft ob alle Figuren
	 * vom aktuellen Spieler im Ziel sind
	 * 
	 * @return true, Wenn die Figuren eines Spielers alle im Ziel sind
	 */
	public Spieler ueberpruefeSpielende() {
//		boolean ende = true;
//		boolean gewonnen = true;
//		
//		
//		for (int i = 0; i < spieler.size(); i++) {
//			Spieler s = spieler.get(i);
//			ArrayList<Figur> figuren = s.getFiguren();
//			gewonnen = true;
//			
//			for (int j = 0; j < figuren.size(); j++) {
//				if(figuren.get(j).getPosition() < f.getEndFeld()) {
//					gewonnen = false;
//					break;
//				}
////				if (figuren.get(j).getPosition() <= zielFelder.get(i)) {
////					ende = false;
////					break;
////				}
//			}
//			
//			if (gewonnen) return s;
//		}
		return null;
	}

	/**
	 * Sucht zunaechst die ausgewaehlte Figur aus und bewegt sie um die
	 * angegebene Anzahl vor
	 * 
	 * @param figur
	 * @param anzahl
	 */
	public void bewegeFigur(Figur figur) {
	
	
		//Die Regeln wurden einen Schritt vorhere �berpr�ft. Es wird nur eine Figur �bergeben, die bewegt werden kann.
		//Jetzt muss nur noch die Figur gesetzt werden
		if(figur.getPosition() == figur.getHausFeld()) {
			
			figur.setPosition(figur.getStartFeld());
		}
		else {
			
			int newPosition = figur.getPosition() + wurfAnzahl;
			figur.setPosition(newPosition);
			
			Figur betroffenePositionFigur = testePosition(newPosition);
			if(betroffenePositionFigur != null) {
				betroffenePositionFigur.setPosition(betroffenePositionFigur.getHausFeld());
			}
		}	
	}

	private Figur testePosition(int position) {
		
		Figur returnObject = null;;
		
		for (int i = 0; i < spieler.size(); i++) {

			for (int j = 0; j < 4; j++) {

				if (spieler.get(i).getFiguren().get(j).getPosition() == position) {
					returnObject = spieler.get(i).getFiguren().get(j);
					break;
				}
			}
			if(returnObject != null) break;
		}
		
		return returnObject;
	}
	
	private void sendeMoeglichkeitenAnClienten(Spieler s, ArrayList<Figur> figuren) {
		// TODO
	}

	public void starteSpiel(List<Spieler> spieler) {
		this.spieler = spieler;
		
		int beginner = Wuerfel.ermittleBeginner(spieler.size()) -1;
		
		while(ueberpruefeSpielende() == null) {
			int wurfAnzahl = Wuerfel.wuerfel();
			ArrayList<Figur> moeglichkeiten = ueberpruefeMoeglichkeiten(spieler.get(beginner), wurfAnzahl);
			sendeMoeglichkeitenAnClienten(spieler.get(beginner++), moeglichkeiten, wurfAnzahl);
			
			if (beginner < spieler.size())
				beginner++;
			else
				beginner = 0;
		}
	}

	/**
	 * Gibt die aktuelle Instanz des Spielfeldes zurueck
	 * 
	 * @return
	 */
	public static ServerController getInstanz() {
		if (instanz == null) {
			instanz = new ServerController();
		}

		return instanz;
	}
}
