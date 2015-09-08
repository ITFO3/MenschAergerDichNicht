package MenschAergerDichNicht;

import java.awt.Color;

/**
 * Die Spielfigur
 * @author Julian Brands
 */
public class Figur {

	private Color eigeneFarbe;
	private String id;
	
	static private int tempCounter = 1;
	
	
	public Figur(Color c) {
		
		eigeneFarbe = c;
		id = c + "" + tempCounter++;
	}
	
	public Color getColor() {
		return this.eigeneFarbe;
	}
	
	public String getId() {
		return this.id;
	}
	
}
