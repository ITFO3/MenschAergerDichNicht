package MenschAergerDichNicht;

import java.awt.Color;

public class Spieler {

	private Color spielfigurenFarbe;
	private Figur[] eigeneSpielfiguren = new Figur[4];
	
	public Spieler(Color c) {
		spielfigurenFarbe = c;
		for (int i = 0; i < eigeneSpielfiguren.length; i++) {
			eigeneSpielfiguren[i] = new Figur(c);
		}
		
		
		
	}
	
	public int wuerfel() {
		return Wuerfel.wuerfel();
	}
	
	public static void main(String[] args) {
		new Spieler(Color.red);
	}
}



