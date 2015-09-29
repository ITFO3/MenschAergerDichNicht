package MenschAergerDichNicht;

import java.awt.Color;
import java.util.ArrayList;

public class Controller {

	private Spieler[] spieler = new Spieler[4];
	ArrayList<Color> farben = new ArrayList<Color>();
	int[] zielFelder = new int[4];
	int[][] startFelder = new int[4][4];
	private static int spielerZaehler = 0;
	private static int spielerStartfeldZaehler = 0;

	public Controller(String name) {
		initialisiereFarben();
		initialisiereStartfelder();
		initialisiereZielfelder();
		initialisiereSpieler(name);
		// view.setzteAufsSpielfeld(eigeneSpielfiguren, this);
	}

	// Verschachteltes Array
	private void initialisiereStartfelder() {
		
		startFelder[0][0] = -1;
		startFelder[0][1] = -2;
		startFelder[0][2] = -3;
		startFelder[0][3] = -4;
		startFelder[0][0] = -5;
		startFelder[0][1] = -6;
		startFelder[0][2] = -7;
		startFelder[0][3] = -8;
		startFelder[0][0] = -9;
		startFelder[0][1] = -10;
		startFelder[0][2] = -11;
		startFelder[0][3] = -12;
		startFelder[0][0] = -13;
		startFelder[0][1] = -14;
		startFelder[0][2] = -15;
		startFelder[0][3] = -16;
	}

	private void initialisiereFarben() {
		farben.add(Color.red);
		farben.add(Color.green);
		farben.add(Color.black);
		farben.add(Color.blue);
		farben.add(Color.CYAN);
		farben.add(Color.MAGENTA);
		farben.add(Color.PINK);
		farben.add(Color.YELLOW);
	}

	private void initialisiereZielfelder() {

		zielFelder[0] = 40;
		zielFelder[1] = 10;
		zielFelder[2] = 20;
		zielFelder[3] = 30;
	}

	public void beendeSpiel() {
		
		spielerZaehler = 0;
	}
	
	public void initialisiereSpieler(String name) {

		int zufallsZahl = (int) (Math.random() * ((farben.size() * 1.0) - 1.0) + 1.0);
		Color c = farben.get(zufallsZahl);
		farben.remove(zufallsZahl);

		spieler[0] = new Spieler(name, c, zielFelder[spielerZaehler++], startFelder[spielerStartfeldZaehler++]);
	}

	public void ueberpruefeMoeglichkeiten(Spieler spieler, int anzahl) {

	}

	public void sendeMoeglichkeitenAnClienten(ArrayList<Figur> figuren) {

	}

	public void platziereSpielerAufStartfeld(Spieler spieler) {

	}

	/**
	 * Ueberprueft ob das Spielende erreicht wurde Aeussere Schleife:
	 * Ueberprueft jeden Spieler Innere Schleife: Ueberprueft ob alle Figuren
	 * vom aktuellen Spieler im Ziel sind
	 * 
	 * @return true, Wenn die Figuren eines Spielers alle im Ziel sind
	 */
	public boolean ueberpruefeSpielende() {
		boolean ende = true;
		
		for (int i = 0; i < spieler.length; i++) {
			Spieler s = spieler[i];
			ArrayList<Figur> figuren = s.getFiguren();

			for (int j = 0; j < figuren.size(); j++) {
				if (figuren.get(j).getPosition() <= zielFelder[i]) {
					ende = false;
					break;
				}
			}
			if (ende){
				return true;
			}
		}
		return false;
	}

	/**
	 * Sucht zunaechst die ausgewaehlte Figur aus und bewegt sie um die
	 * angegebene Anzahl vor
	 * 
	 * @param figur
	 * @param anzahl
	 */
	public void bewegeFigur(Figur figur, int anzahl) {

		
		
		int newPosition = figur.getPosition() + anzahl;
		figur.setPosition(newPosition);
		
		Figur betroffenePositionFigur = testePosition(newPosition);
		if(betroffenePositionFigur != null) {
			betroffenePositionFigur.setPosition(newPosition);
		}
		
		
	}

	
	private Figur testePosition(int position) {
		
		Figur returnObject = null;;
		
		for (int i = 0; i < spieler.length; i++) {

			for (int j = 0; j < 4; j++) {

				if (spieler[i].getFiguren().get(j).getPosition() == position) {
					returnObject = spieler[i].getFiguren().get(j);
				}
			}
		}
		
		return returnObject;
	}
	
	public void starteSpiel() {

	}

	public void erstelleSpiel(int anzahl, String name) {

	}

	public boolean treteSpielBei(String ip, String name) {
		return false;
	}

}
