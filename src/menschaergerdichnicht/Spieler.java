package menschaergerdichnicht;

import java.awt.Color;
import java.util.ArrayList;

public class Spieler {
	private Color farbe;
	private Figur[] figuren;
	private String name;
	private int startFeld;
	private ArrayList<Integer> freieHausfelder = new ArrayList<Integer>();
	private int ziel;
	private String ip;
	
	/**
	 * Erstellt einen neuen Spieler inklusive der Spielfiguren
	 * 
	 * @param name  Der Spielername
	 * @param c 	Die Farbe des Spielers
	 * @param ip 
	 */
	public Spieler(String name, Color c, int ziel, int[] start, String ip) {
		this.ip = ip;
		this.farbe = c;
		this.name = name;
		this.ziel = ziel;
		this.startFeld = ziel + 1 > 40 ? ziel + 1 - 40 : ziel + 1;  
		this.figuren = new Figur[4];
		
		for (int i = 0; i < figuren.length; i++) {
			
			figuren[i] = new Figur(name, c, this, start[i]);
		}
	}
	
	public int gibStartFeld() {
		
		return this.startFeld;
	}

	public String getIp(){
		return this.ip;
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



