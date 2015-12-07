package model;

import java.awt.Color;
import java.util.ArrayList;

public class Spieler {
	
	public enum State{
		VOID,
		WATING_FOR_SELECTION;
	}
	
    public static final int[] zielFelderSpielerEins = new int[] { 41, 42, 43, 44 };
    public static final int[] zielFelderSpielerZwei = new int[] { 45, 46, 47, 48 };
    public static final int[] zielFelderSpielerDrei = new int[] { 49, 50, 51, 52 };
    public static final int[] zielFelderSpielerVier = new int[] { 53, 54, 55, 56 };

    private String name;
    private Color farbe;
    private Figur[] figuren;
    private int[] zielFelder;

    private State state;
    
    public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

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
    public Spieler(String name, Color farbe, int[] zielFelder, int hausFeld, int startFeld, int endFeld) {
        this.name = name;
        this.farbe = farbe;
        this.zielFelder = zielFelder;
        this.state = State.VOID;

        this.figuren = new Figur[4];

        Spielfeld model = Spielfeld.getInstance();
        for (int i = 0; i < figuren.length; i++) {
            figuren[i] = new Figur(name, this.farbe, hausFeld + i, startFeld, endFeld, zielFelder);
            figuren[i].addObserver(model);
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

    public int[] getZielFelder() {
        return this.zielFelder;
    }
}
