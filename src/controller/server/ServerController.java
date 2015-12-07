package controller.server;

import java.util.ArrayList;
import java.util.List;

import model.Figur;
import model.Spieler;
import model.Spielfeld;
import model.Wuerfel;
import controller.NetworkService;
import controller.server.network.ClientHandler;
import controller.server.network.ServerNetworkService;

public class ServerController {

	private static ServerController instanz;

	private boolean _figurGewaehlt;

	private ServerController() {
		instanz = this;
	}

	/**
	 * 
	 */
	public void starteSpiel(ArrayList<Spieler> spieler) {
		Spielfeld.getInstanz().setSpieler(spieler);
		sendeSpielerAnClients();

		int beginner = Wuerfel.ermittleBeginner(spieler.size());
		
		Spieler sieger = null;

		while (sieger == null) {
			_figurGewaehlt = false;
			int wurfAnzahl = Wuerfel.wuerfel();
			Spielfeld.getInstanz().setWurfAnzahl(wurfAnzahl);
			
			ArrayList<Figur> moeglichkeiten = ueberpruefeMoeglichkeiten(
					spieler.get(beginner), wurfAnzahl);
			
			sendeMoeglichkeitenAnClient(spieler.get(beginner++),
					moeglichkeiten, wurfAnzahl);
			
			while (!_figurGewaehlt) {
				// Warten, bis eine Figur ausgewaehlt wurde
			}
			
			//TODO warte auf die Eingabe vom Spieler, welche Figur bewegt werden soll

			if (beginner == spieler.size()) {
				beginner -= spieler.size();
			}
			
			sieger = ueberpruefeSpielende();
		}
		
		//TODO Den Gewinner auf der Oberfläche anzeigen
	}

	public ArrayList<Figur> ueberpruefeMoeglichkeiten(Spieler spieler,
			int anzahl) {

		ArrayList<Figur> figuren = spieler.getFiguren();
		ArrayList<Figur> bewegbareFiguren = new ArrayList<Figur>();
		int wurfAnzahl = Spielfeld.getInstanz().getWurfAnzahl();
		
		//TODO Die Regeln müssen an dieser Stelle überprüft werden
		
		for (Figur figur : figuren) {
			bewegbareFiguren.add(figur);
		}

		return bewegbareFiguren;
	}

	private void sendeMoeglichkeitenAnClient(Spieler s, ArrayList<Figur> figuren, int wurfanzahl) {
		List<ClientHandler> clients = ServerNetworkService.getInstance().getClients();
		ClientHandler client = null;

		for (ClientHandler c : clients) {
			if (c.getSpielerName().equals(s.getName())) {
				client = c;
			}
		}

		NetworkService.getInstance().sendeMoeglichkeitenAnClient(client,
				figuren, wurfanzahl);
	}
	
	private void sendeSpielerAnClients() {
		for (Spieler spieler : Spielfeld.getInstanz().getSpieler()) {
			NetworkService.getInstance().sendeSpielerAnClients(spieler);
		}
	}

	/**
	 * Ueberprueft ob das Spielende erreicht wurde Aeussere Schleife:
	 * Ueberprueft jeden Spieler Innere Schleife: Ueberprueft ob alle Figuren
	 * vom aktuellen Spieler im Ziel sind
	 * 
	 * @return true, Wenn die Figuren eines Spielers alle im Ziel sind
	 */
	public Spieler ueberpruefeSpielende() {

		ArrayList<Spieler> spieler = Spielfeld.getInstanz().getSpieler();
	
		boolean figurHatEndpositionErreicht = false;
		boolean alleFigurenHabenDieEndpositionenErreicht = true;
		int spielerAnzahl = spieler.size();
		
		//Überprüfe jeden Spieler
		for (int i = 0; i < spielerAnzahl; i++) {
			
			Spieler s = spieler.get(i);
			ArrayList<Figur> figuren = s.getFiguren();
			alleFigurenHabenDieEndpositionenErreicht = true;
			int figurenAnzahl = figuren.size();
			
			//Überprüfe jede Figur bei dem aktuellen Spieler
			for (int j = 0; j < figurenAnzahl; j++) {
				
				Figur f = figuren.get(j);
				int[] zielFelder = f.getZielFelder();
				figurHatEndpositionErreicht = false;
				
				//Überprüfe alle vier Zielfelder vom Spieler
				for (int k = 0; k < zielFelder.length; k++) {

					//Ist die aktuelle Figur im Zielfeld angelangt?
					if(f.getPosition() == zielFelder[k]) {
						//Ja, überprüfe die nächste Figur
						figurHatEndpositionErreicht = true;
						break;
					}
				}
				//Eine der Figuren ist nicht in der Endposition, überprüfe den nächsten Spieler
				if(!figurHatEndpositionErreicht) {
					alleFigurenHabenDieEndpositionenErreicht = false;
					break;
				}
			}
			
			
			//Alle Figuren sind im Ziel, gebe den Sieger aus
			if(alleFigurenHabenDieEndpositionenErreicht) {
				return s;
			}
		}
	
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

		// Die Regeln wurden einen Schritt vorhere überprüft. Es wird nur
		// eine Figur übergeben, die bewegt werden kann.
		// Jetzt muss nur noch die Figur gesetzt werden
		if (figur.getPosition() == figur.getHausFeld()) {

			figur.setPosition(figur.getStartFeld());
		} 
		else {
			
			Spielfeld.getInstanz().setWurfAnzahl(2);
			int wurfAnzahl = Spielfeld.getInstanz().getWurfAnzahl();
			int newPosition = figur.getPosition() + wurfAnzahl;
			//Würde die Figur über das letzte Feld gelangen, so muss es in die Zielfelder gehen
			if(newPosition > figur.getEndFeld()) {
				if(newPosition - figur.getEndFeld() > 4) { //Hier wird ein Fehler erzeugt, weil man diese Figur nicht ausw�hlen konnte und der Fehelr normalerwei�e nicht auftritt
					newPosition = 100;
				}
				if(figur.getPosition() > figur.getEndFeld()) {
					newPosition = figur.getPosition() + wurfAnzahl;
				}
				else {
					newPosition = figur.getZielFelder()[newPosition - figur.getEndFeld() - 1];
				}
			}
			figur.setPosition(newPosition);

			Figur betroffenePositionFigur = testePosition(newPosition);
			if (betroffenePositionFigur != null) {
				betroffenePositionFigur.setPosition(betroffenePositionFigur
						.getHausFeld());
			}
			
			NetworkService.getInstance().sendeFigurAnClients(figur);
		}
	}

	private Figur testePosition(int position) {

		Figur returnObject = null;
		ArrayList<Spieler> spieler = Spielfeld.getInstanz().getSpieler();

		for (int i = 0; i < spieler.size(); i++) {

			for (int j = 0; j < 4; j++) {

				if (spieler.get(i).getFiguren().get(j).getPosition() == position) {
					returnObject = spieler.get(i).getFiguren().get(j);
					break;
				}
			}
			if (returnObject != null)
				break;
		}

		return returnObject;
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
