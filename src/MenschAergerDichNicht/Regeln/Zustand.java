package MenschAergerDichNicht.Regeln;

/**
 * Zustandsklasse
 * Diese wird an die Regeln uebergeben, um den aktuellen Zustand des Spielfeldes wiederzugeben
 */
public class Zustand {
	private int wuerfelzahl;
	
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
	
	
}
