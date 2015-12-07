package model;

/**
 * Der Wuerfel
 */
public class Wuerfel {

	/**
	 * @return Eine 'Zufallszahl' ziwschen 1 und 6
	 */
	public static int wuerfel() {
		return (int)(Math.random() * 6.0 + 1.0);
	}
	
	
	/**
	 * Ermittelt eine Zufallszahl zwischen 0 und spielerAnzahl - 1, 
	 * damit die Werte direkt für einen Listenzugriff genutzt werden können
	 * 
	 * @param spielerAnzahl
	 * @return Zufallszahl
	 */
	public static int ermittleBeginner(int spielerAnzahl) {
		return (int)(Math.random() * spielerAnzahl);
	}
	
}
