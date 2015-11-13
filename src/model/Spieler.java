package model;

import java.awt.Color;
import java.util.ArrayList;

public class Spieler 
{
	private String name;
	private Color farbe;
	private Figur[] figuren;
	private int[] zielFelder;

	/**
	 * Erstellt einen neuen Spieler inklusive der Spielfiguren
	 * 
	 * @param name  		Der Spielername
	 * @param c 			Die Farbe des Spielers
	 * @param hausFeld
	 * @param startFeld
	 * @param endFeld
	 * @param zielFelder
	 */
	public Spieler(String name, Color c, int hausFeld, int startFeld, int endFeld, int[] zielFelder) {
		this.farbe = c;
		this.name = name;
		this.figuren = new Figur[4];
		this.zielFelder = zielFelder;
		
		for (int i = 0; i < figuren.length; i++) {
			figuren[i] = new Figur(name, c, hausFeld, startFeld, endFeld);
		}
	}

	/**
	 * Gibt die Spielerfarbe zurueck
	 * 
	 * @return Color
	 */
	public Color getFarbe() {
		return farbe;
	}

	/**
	 *  Gibt den Spielernamen zurueck
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gibt die Figuren des Spielers als ArrayList zurueck
	 * @return ArrayList<Figur>
	 */
	public ArrayList<Figur> getFiguren() {
		ArrayList<Figur> figuren = new ArrayList<Figur>();
		
		for (int i = 0; i < this.figuren.length; i++) {
			figuren.add(this.figuren[i]);
		}
		
		return figuren;
	}
}
