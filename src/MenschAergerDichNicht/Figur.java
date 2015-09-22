package MenschAergerDichNicht;

import java.awt.Color;

/**
 * Die Spielfigur
 * @author Julian Brands
 */
public class Figur {

	private Color eigeneFarbe;
	private String id;
	private int position;
	
	static private int tempCounter = 1;
	
	
	
	public Figur(String name, Color c) {
		
		position = 0;
		eigeneFarbe = c;
		id = name + ":" + tempCounter++;
	}
	
	public Color getColor() {
		return this.eigeneFarbe;
	}
	
	public String getId() {
		return this.id;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int newPosition) {
		position = newPosition;
	}
}
