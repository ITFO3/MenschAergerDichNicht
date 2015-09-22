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
	
	/**
	 * Setzt eine neue Spielfigur auf das Spielfeld
	 * 
	 * @param figur
	 * @param position
	 * @return False, falls die angegebene Position schon besetzt ist. Ansonsten True
	 */
	public boolean setSpielfigur(Figur figur, int position) {
		if (spielfiguren.get(position) == null) {
			spielfiguren.put(position, figur);
			return true;
		}
		
		return false;
	}
}
