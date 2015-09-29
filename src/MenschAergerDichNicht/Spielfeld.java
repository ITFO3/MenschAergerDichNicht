package MenschAergerDichNicht;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Das Spielfeld, auf dem sich die Figuren befinden
 * @author Dominik Bittner
 */
public class Spielfeld extends Observable implements Observer {
	/**
	 * Die Instanz des Spielfeldes
	 */
	private static Spielfeld instanz;

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
	
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		Figur figur = (Figur) arg;
		
		int altePosition = 0;
		// Alte Position ermitteln
		for (int i : spielfiguren.keySet()) {
			if (spielfiguren.get(i).getId() == figur.getId()) {
				altePosition = i;
				break;
			}
		}
		
		// Nur eine Figur entfernen, wenn diese auch schon auf dem Spielfeld ist 
		if (altePosition != 0) {
			// Alte Position entfernen
			spielfiguren.remove(altePosition);
		}
		
		// Neue Position besetzen
		spielfiguren.put(figur.getPosition(), figur);
		
		// View benachrichtigen
		notifyObservers(this);
	}
}
