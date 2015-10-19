package menschaergerdichnicht.regeln;

import menschaergerdichnicht.Figur;
import menschaergerdichnicht.Spielfeld;

/**
 * Zustandsklasse
 * Diese wird an die Regeln uebergeben, um den aktuellen Zustand des Spielfeldes wiederzugeben
 */
public class Zustand {
	private int wuerfelzahl;
	private Spielfeld spielfeld;
	private Figur figur;
	
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

	/**
	 * Setter fuer die aktuelle Spielfigur
	 * @param figur Die aktuelle Spielfigur
	 * @return
	 */
	public Zustand setFigur(Figur figur) {
		this.figur = figur;
		
		return this;
	}
	
	/**
	 * Getter fuer die aktuelle Spielfigur
	 * @return
	 */
	public Figur getFigur() {
		return figur;
	}
}
