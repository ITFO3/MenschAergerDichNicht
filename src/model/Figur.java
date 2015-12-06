package model;

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
	private int startFeld;
	private int endFeld;
	private int[] zielFelder;

	static private int tempCounter = 1;
	
	public Figur(String name, Color c, int hausFeld, int startFeld, int endFeld, int[] zielFelder) {
		eigeneFarbe = c;
		id = name + ":" + tempCounter++;
		
		this.hausFeld = hausFeld;
		this.startFeld = startFeld;
		this.endFeld = endFeld;
		this.zielFelder = zielFelder;
		
		// Startet im Haus
		this.setPosition(hausFeld);
	}
	
	public int[] getZielFelder() {
		return zielFelder;
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
	
	public int getStartFeld() 
	{
		return this.startFeld;
	}
	
	public int getEndFeld() {
		return this.endFeld;
	}
	
	public void setPosition(int newPosition) {
		setChanged();
		position = newPosition;
		notifyObservers(this);
	}
}
