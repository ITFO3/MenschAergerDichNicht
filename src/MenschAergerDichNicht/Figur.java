package MenschAergerDichNicht;

import java.awt.Color;
import java.util.Observable;

/**
 * Die Spielfigur
 * @author Julian Brands
 */
public class Figur extends Observable {

	private Color eigeneFarbe;
	private String id;
	private int position;
	private int hausFeld;
	private Spieler s;

	
	static private int tempCounter = 1;
	
	
	
	public Figur(String name, Color c, Spieler s, int hausFeld) {
		
		position = 0;
		eigeneFarbe = c;
		id = name + ":" + tempCounter++;
		this.s = s;
		this.hausFeld = hausFeld;
		this.setPosition(hausFeld);
	}
	
	public int getHausFeld() {
		return hausFeld;
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
	
	public int getStartFeld() {
		
		return s.gibStartFeld();
	}
	
	public void setPosition(int newPosition) {
		setChanged();
		position = newPosition;
		notifyObservers(this);
	}
}
