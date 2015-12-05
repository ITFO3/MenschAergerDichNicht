package model;

import java.awt.Color;
import java.util.ArrayList;

public class Spieler {

	public static int[] zielFelderSpielerDrei = new int[] { 49, 50, 51, 52 };
	
	public static int[] zielFelderSpielerEins = new int[] { 41, 42, 43, 44 };
	
	public static int[] zielFelderSpielerVier = new int[] { 53, 54, 55, 56 };

	public static int[] zielFelderSpielerZwei = new int[] { 45, 46, 47, 48 };

	private Color farbe;

	private Figur[] figuren;
	
	private String name;
	
	private int[] zielFelder;

	/**
	 * Erstellt einen neuen Spieler inklusive der Spielfiguren
	 * 
	 * @param name
	 *            Der Spielername
	 * @param c
	 *            Die Farbe des Spielers
	 * @param hausFeld
	 * @param startFeld
	 * @param endFeld
	 * @param zielFelder
	 */
	public Spieler(String name, Color farbe, int[] zielFelder, int hausFeld, int startFeld, int endFeld) {
		this.name = name;
		this.farbe = farbe;
		this.zielFelder = zielFelder;

		this.figuren = new Figur[4];

		for (int i = 0; i < figuren.length; i++) {
			figuren[i] = new Figur(name, this.farbe, hausFeld + i, startFeld, endFeld);
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
	 * Gibt die Figuren des Spielers als ArrayList zurueck
	 * 
	 * @return ArrayList<Figur>
	 */
	public ArrayList<Figur> getFiguren() {
		ArrayList<Figur> figuren = new ArrayList<Figur>();

		for (int i = 0; i < this.figuren.length; i++) {
			figuren.add(this.figuren[i]);
		}

		return figuren;
	}

	/**
	 * Gibt den Spielernamen zurueck
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	public int[] getZielfelder() {
		return this.zielFelder;
	}
}