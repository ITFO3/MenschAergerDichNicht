package MenschAergerDichNicht;

import java.util.ArrayList;
import java.util.HashMap;

public class Spielfeld {

	private ArrayList<Spieler> spieler;
	/**
	 * HashMap mit den Spielfiguren
	 * Key: Position auf dem Feld
	 * Value: Spielfigur
	 */
	private HashMap<Integer, Figur> spielfiguren;
	
	public Spielfeld() {
		spieler = new ArrayList<Spieler>();
		spielfiguren = new HashMap<Integer, Figur>();
	}
	
	/**
	 * Gibt die Hashmap mit den Spielfiguren zurueck
	 * @return
	 */
	public HashMap<Integer, Figur> getSpielfiguren() {
		return this.spielfiguren;
	}
	
	public void setzteAufsSpielfeld(HashMap<Integer, Figur> eigeneSpielfiguren, Spieler spieler) {
		
	}
	
}
