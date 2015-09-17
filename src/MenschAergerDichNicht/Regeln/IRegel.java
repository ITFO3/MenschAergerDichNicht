package MenschAergerDichNicht.Regeln;

/**
 * Interface fuer die Regeln
 */
public interface IRegel {
	/**
	 * Ueberprueft, ob diese Regel zutrifft
	 * 
	 * @return True, falls die Regel zutrifft. Ansonsten false
	 */
	boolean check ();
	
	/**
	 * Setzt die naechste Regel, die Kette
	 * 
	 * @param regel 	Die naechste Regel, die danach angewendet werden soll
	 * @param zustand 	Der Zustand des aktuellen Spiels
	 */
	void setNextRegel(IRegel regel, Zustand zustand);
	
	/**
	 * Fuehrt die aktuelle Regel aus
	 */
	void execute();
}
