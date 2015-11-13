package controller.server;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.Figur;
import model.Spieler;
import model.Spielfeld;
import model.Wuerfel;

public class ServerController {

	private ArrayList<Spieler> spieler = new ArrayList<Spieler>();

	private static int spielerZaehler = 0;
	
	private ServerController(List<Spieler> spieler) {
		
		Spielfeld model = Spielfeld.getInstanz();

		for(Spieler s : spieler) {
		}
		
	}

	public void beendeSpiel() {
		
		spielerZaehler = 0;
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
			
			//ueberpruefe Figur
			bewegbareFiguren.add(figur);
		} 
		
		return bewegbareFiguren;
	}

	private void sendeMoeglichkeitenAnClienten(Spieler s, ArrayList<Figur> figuren) {
		// TODO
	}

	/**
	 * Ueberprueft ob das Spielende erreicht wurde Aeussere Schleife:
	 * Ueberprueft jeden Spieler Innere Schleife: Ueberprueft ob alle Figuren
	 * vom aktuellen Spieler im Ziel sind
	 * 
	 * @return true, Wenn die Figuren eines Spielers alle im Ziel sind
	 */
	public Spieler ueberpruefeSpielende() {
		boolean ende = true;
		
		for (int i = 0; i < spieler.size(); i++) {
			Spieler s = spieler.get(i);
			ArrayList<Figur> figuren = s.getFiguren();

			for (int j = 0; j < figuren.size(); j++) {
//				if (figuren.get(j).getPosition() <= zielFelder.get(i)) {
//					ende = false;
//					break;
//				}
			}
			
			if (ende) return s;
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
	public void bewegeFigur(Figur figur, int anzahl) {

	
		//Die Regeln wurden einen Schritt vorhere �berpr�ft. Es wird nur eine Figur �bergeben, die bewegt werden kann.
		//Jetzt muss nur noch die Figur gesetzt werden
		if(figur.getPosition() == figur.getHausFeld()) {
			
			figur.setPosition(figur.getStartFeld());
		}
		else {
			
			int newPosition = figur.getPosition() + anzahl;
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
	
	public void starteSpiel() {
		
		int beginner = Wuerfel.ermittleBeginner(spieler.size());
		
		
		while(ueberpruefeSpielende() == null) {
			
			int wurfAnzahl = Wuerfel.wuerfel();
			ArrayList<Figur> moeglichkeiten = ueberpruefeMoeglichkeiten(spieler.get(beginner), wurfAnzahl);
			sendeMoeglichkeitenAnClienten(spieler.get(beginner++), moeglichkeiten);
			
			
			
			if(beginner > spieler.size()) beginner -=  spieler.size();
		}
		
		
	}

//	public void erstelleSpiel(int anzahl, String name) {
//		//Server braucht keine IP, Anzahl wie viele Spieler mitspielen duerfen noch einrichten
//		Controller c = new Controller(name, null);
//	}
//
//	public boolean treteSpielBei(String name, String ip) {
//		
//		if(spieler.size() < 4) initialisiereSpieler(name, ip);
//		else return false;
//		
//		return true;
//	}

}
