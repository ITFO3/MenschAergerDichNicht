package MenschAergerDichNicht;

import java.awt.Color;

public class Spieler {

	private Color spielfigurenFarbe;
	private Figur[] eigeneSpielfiguren = new Figur[4];
	private String name;
	
	public Spieler(String name, Color c) {
		spielfigurenFarbe = c;
		this.name = name;
		for (int i = 0; i < eigeneSpielfiguren.length; i++) {
			eigeneSpielfiguren[i] = new Figur(name, c);
		}		
		
	}
	
	public int wuerfel() {
		return Wuerfel.wuerfel();
	}
	
}



