package MenschAergerDichNicht.Regeln;

import MenschAergerDichNicht.Spielfeld;

/**
 * Zustandsklasse
 * Diese wird an die Regeln uebergeben, um den aktuellen Zustand des Spielfeldes wiederzugeben
 */
public class Zustand {
	private int wuerfelzahl;
	private Spielfeld spielfeld;
	
	/**
	 * Setter fuer die aktuell gewuerfelte Zahl
	 *  
	 * @param wuerfelzahl
	 * @return Das aktuelle Zustand-Objekt
	 */
	public Zustand setWuerfelzahl(int wuerfelzahl) {
		this.wuerfelzahl = wuerfelzahl;
		
		return this;
	}

	/**
	 * Getter fuer die aktuell gewuerfelte Zahl
	 * @return
	 */
	public int getWuerfelzahl() {
		return this.wuerfelzahl;
	}
	
	/**
	 * Setter fuer das Spielfeld
	 * 
	 * @param spielfeld
	 * @return Das aktuelle Zustand-Objekt
	 */
	public Zustand setSpielfeld(Spielfeld spielfeld) {
		this.spielfeld = spielfeld;
		
		return this;
	}
	
	/**
	 * Getter fuer das Spielfeld
	 * @return
	 */
	public Spielfeld getSpielfeld() {
		return this.spielfeld;
	}
}
