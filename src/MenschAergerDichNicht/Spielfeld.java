package MenschAergerDichNicht;

import java.util.ArrayList;
import java.util.HashMap;

public class Spielfeld {
	/**
	 * Die Instanz des Spielfeldes
	 */
	private static Spielfeld instanz;
	
	private ArrayList<Spieler> spieler;
	/**
	 * HashMap mit den Spielfiguren
	 * Key: Position auf dem Feld
	 * Value: Spielfigur
	 */
	private HashMap<Integer, Figur> spielfiguren;
	
	/**
	 * Erstellt ein neues Spielfeld
	 */
	private Spielfeld() {
		spieler = new ArrayList<Spieler>();
		spielfiguren = new HashMap<Integer, Figur>();
	}
	
	/**
	 * Gibt die aktuelle Instanz des Spielfeldes zurueck
	 * @return
	 */
	public static Spielfeld getInstanz() {
		if (instanz == null) {
			instanz = new Spielfeld();
		}
		
		return instanz;
	}
	
	/**
	 * Gibt die HashMap mit den Spielfiguren zurueck
	 * @return
	 */
	public HashMap<Integer, Figur> getSpielfiguren() {
		return this.spielfiguren;
	}
	
	/**
	 * Setzt oder bewegt eine Spielfigur auf dem Spielfeld
	 * 
	 * @param figur		Die Spielfigur
	 * @param position	Die neue Position der Spielfigur
	 * @return False, falls die angegebene Position schon besetzt ist. Ansonsten True
	 */
	public boolean setSpielfigur(Figur figur, int position) {
		// Ueberpruefen, ob die gewaehlte Position frei ist
		if (spielfiguren.get(position) == null) {
			// Gegebenenfalls die vorherige Position loeschen
			if (spielfiguren.get(figur.getPosition()) != null) {
				spielfiguren.remove(figur.getPosition());
			}
			
			// Figur auf neue Position setzen
			spielfiguren.put(position, figur);
			return true;
		}
		
		return false;
	}
}
