package controller.server;

import java.util.ArrayList;
import java.util.List;

import view.SpielfeldCanvas;
import model.Figur;
import model.Spieler;
import model.Spielfeld;
import model.Wuerfel;
import controller.NetworkService;
import controller.server.network.ClientHandler;
import controller.server.network.ServerNetworkService;
import controller.server.regeln.Regel;
import controller.server.regeln.SechsRegel;
import controller.server.regeln.Zustand;

public class ServerController {

	private static ServerController instanz;


	private ServerController() {
		instanz = this;
	}


/**
 * [1] Aktuell fehlt noch der Beginn des Spiels. Hierzu sollte von der Netzwerkseite aus die Liste an Spielern an den ServerController an die Methode "starteSpiel(ArrayList<Spieler> spieler)" �bergeben werden.


[2] Nachdem die M�glichkeiten an den entsprechenden Spieler geschickt wurden, sollte diese Methode pausieren und auf die Eingabe des Spielers warten. (Fehlt noch)


Klickt man eine m�gliche Figur auf der Oberfl�che an, sucht die SpielfeldCanvas die passende Figur im Spielfeld und ruft im ClientNetworkService die Methode "sendeFigur(f)" auf. 
[3] Nun muss auf der Serverseite die Daten empfangen werden und die Methode "bewegeFigur(f)" aufgerufen werden
 

In der Methode "ServerController.bewegeFigur(f) wird dann der NetworkService mit der Methode "sendeFigurAnClients(figur)" aufgerufen. Die Figur sollte anschlie�end an alle Clients verteilt werden?! Bei den Clients in der Methode "aktualisiereSpielfeld(String ausgewaehlteFigurId, int neuePosition)" wird die Figur auf die neue Position gesetzt. Sollte an dieser Stelle eine Figur stehen, wird diese auf das entsprechende Homefield gesetzt.
Zudem wurde eine �berpr�fung des Spielendes hoffentlich richtig implementiert?!?


[4] Es m�ssen noch die Regeln implementiert werden (ServerController.ueberpruefeMoeglichkeiten)


[5] Der Gewinner (in der Methode ServerController.starteSpiel) sollte noch auf der Oberfl�che angezeigt werden


([6] Es wird auf der Oberfl�che die W�rfelanzahl angezeigt, dies k�nnte versch�nert werden)


1,3 Christian
4 Dominik
5(,6) Max
2 Evtl. Christian, da du schon sowas �hnliches bei dem NetworkService verbaut hast?


  */
	
	
	
	

	public void starteSpiel(ArrayList<Spieler> spieler) {
		Spielfeld.getInstanz().setSpieler(spieler);

		int beginner = Wuerfel.ermittleBeginner(spieler.size());
		
		Spieler sieger = null;

		while (sieger == null) {

			int wurfAnzahl = Wuerfel.wuerfel();
			Spielfeld.getInstanz().setWurfAnzahl(wurfAnzahl);
			ArrayList<Figur> moeglichkeiten = ueberpruefeMoeglichkeiten(
					spieler.get(beginner), wurfAnzahl);
			sendeMoeglichkeitenAnClienten(spieler.get(beginner++),
					moeglichkeiten, wurfAnzahl);
			
			//TODO warte auf die Eingabe vom Spieler, welche Figur bewegt werden soll

			if (beginner == spieler.size()) {
				beginner -= spieler.size();
			}
			sieger = ueberpruefeSpielende();
		}
		
		//TODO Den Gewinner auf der Oberfl�che anzeigen
	}

	public ArrayList<Figur> ueberpruefeMoeglichkeiten(Spieler spieler,
			int anzahl) {

		ArrayList<Figur> figuren = spieler.getFiguren();
		ArrayList<Figur> bewegbareFiguren = new ArrayList<Figur>();
		int wurfAnzahl = Spielfeld.getInstanz().getWurfAnzahl();
		
		//TODO Die Regeln m�ssen an dieser Stelle �berpr�ft werden
		
//		for (Figur figur : figuren) {
//
//			bewegbareFiguren.add(figur);
//		}

		return bewegbareFiguren;
	}

	private void sendeMoeglichkeitenAnClienten(Spieler s,
			ArrayList<Figur> figuren, int wurfanzahl) {
		List<ClientHandler> clients = ServerNetworkService.getInstance()
				.getClients();
		ClientHandler client = null;

		for (ClientHandler c : clients) {
			if (c.getSpielerName().equals(s.getName())) {
				client = c;
			}
		}

		NetworkService.getInstance().sendeMoeglichkeitenAnClient(client,
				figuren, wurfanzahl);
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
		
		//�berpr�fe jeden Spieler
		for (int i = 0; i < spielerAnzahl; i++) {
			
			Spieler s = spieler.get(i);
			ArrayList<Figur> figuren = s.getFiguren();
			alleFigurenHabenDieEndpositionenErreicht = true;
			int figurenAnzahl = figuren.size();
			
			//�berpr�fe jede Figur bei dem aktuellen Spieler
			for (int j = 0; j < figurenAnzahl; j++) {
				
				Figur f = figuren.get(j);
				int[] zielFelder = f.getZielFelder();
				figurHatEndpositionErreicht = false;
				
				//�berpr�fe alle vier Zielfelder vom Spieler
				for (int k = 0; k < zielFelder.length; k++) {

					//Ist die aktuelle Figur im Zielfeld angelangt?
					if(f.getPosition() == zielFelder[k]) {
						//Ja, �berpr�fe die n�chste Figur
						figurHatEndpositionErreicht = true;
						break;
					}
				}
				//Eine der Figuren ist nicht in der Endposition, �berpr�fe den n�chsten Spieler
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

		// Die Regeln wurden einen Schritt vorhere �berpr�ft. Es wird nur
		// eine Figur �bergeben, die bewegt werden kann.
		// Jetzt muss nur noch die Figur gesetzt werden
		if (figur.getPosition() == figur.getHausFeld()) {

			figur.setPosition(figur.getStartFeld());
		} 
		else {
			
			Spielfeld.getInstanz().setWurfAnzahl(2);
			int wurfAnzahl = Spielfeld.getInstanz().getWurfAnzahl();
			int newPosition = figur.getPosition() + wurfAnzahl;
			//W�rde die Figur �ber das letzte Feld gelangen, so muss es in die Zielfelder gehen
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
