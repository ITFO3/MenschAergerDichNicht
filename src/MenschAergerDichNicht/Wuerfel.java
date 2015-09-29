package MenschAergerDichNicht;

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
	
	public static int ermittleBeginner(int spielerAnzahl) {
		return (int)(Math.random() * spielerAnzahl + 1.0);
	}
	
	
}
