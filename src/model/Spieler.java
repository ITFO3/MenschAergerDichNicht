package model;

import java.awt.Color;
import java.util.ArrayList;

public class Spieler 
{
	private int[] zielFelderSpielerEins = new int[] { 41, 42, 43, 44 };
	private int[] zielFelderSpielerZwei = new int[] { 45, 46, 47, 48 };
	private int[] zielFelderSpielerDrei = new int[] { 49, 50, 51, 52 };
	private int[] zielFelderSpielerVier = new int[] { 53, 54, 55, 56 };
	
	private static int spielerZahl = 0;
	
	private String name;
	private Color farbe;
	private Figur[] figuren;

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
	public Spieler(String name) {
		spielerZahl++;
		
		int hausFeld = 0;
		int startFeld = 0;
		int endFeld = 0;
		int[] zielFelder = new int[4];
		
		switch (spielerZahl) {
			case 1:
				hausFeld = -16;
				startFeld = 1;
				endFeld = 40;
				this.farbe = Color.RED;
				zielFelder = zielFelderSpielerEins;
				break;
			case 2:
				hausFeld = -4;
				startFeld = 11;
				endFeld = 10;
				this.farbe = Color.BLUE;
				zielFelder = zielFelderSpielerZwei;
				break;
			case 3:
				hausFeld = -8;
				startFeld = 21;
				endFeld = 20;
				this.farbe = Color.GREEN;
				zielFelder = zielFelderSpielerDrei;
				break;
			case 4:
				hausFeld = -12;
				startFeld = 31;
				endFeld = 30;
				this.farbe = Color.ORANGE;
				zielFelder = zielFelderSpielerVier;
				break;
		}
		
		this.name = name;
		this.figuren = new Figur[4];
		Spielfeld model = Spielfeld.getInstanz();
		Figur f;
		
		for (int i = 0; i < figuren.length; i++) {
			f = new Figur(name, this.farbe, hausFeld + i, startFeld, endFeld, zielFelder);
			f.addObserver(model);
			figuren[i] = f;
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