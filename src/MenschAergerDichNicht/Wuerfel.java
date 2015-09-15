package MenschAergerDichNicht;

/**
 * Der Wuerfel
 */
public class Wuerfel {

	/**
	 * @return Eine 'Zufallszahl' ziwschen 1 und 6
	 */
	public static int wuerfel() {
		return (int)(Math.random() * (7.0 - 1.0) + 1.0);
	}
	
}
