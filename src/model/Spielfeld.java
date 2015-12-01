package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import view.SpielfeldCanvas;

/**
 * Das Spielfeld, auf dem sich die Figuren befinden
 * @author Dominik Bittner
 */
public class Spielfeld extends Observable implements Observer {
	/**
	 * Die Instanz des Spielfeldes
	 */
	private static Spielfeld instanz;
	
	ArrayList<Integer> zielFelder = new ArrayList<Integer>();
	ArrayList<int[]> homeFelder = new ArrayList<int[]>();
	
	ArrayList<Figur> moeglichkeiten = new ArrayList<Figur>();

	public ArrayList<Figur> getMoeglichkeiten() {
		return moeglichkeiten;
	}


	public void setMoeglichkeiten(ArrayList<Figur> moeglichkeiten) {
		this.moeglichkeiten = moeglichkeiten;
	}


	/**
	 * HashMap mit den Spielfiguren
	 * Key: Position auf dem Feld
	 * Value: Spielfigur
	 */
	private HashMap<Integer, Figur> spielfiguren;
	
	/**
	 * Erstellt ein neues Spielfeld
	 */
	private Spielfeld() {
		spielfiguren = new HashMap<Integer, Figur>();
		initialisiereHomefelder();
		initialisiereZielfelder();
	}
	
	
	private void initialisiereHomefelder() {
		
		homeFelder.add(new int[]{-1,-2,-3,-4});
		homeFelder.add(new int[]{-5,-6,-7,-8});
		homeFelder.add(new int[]{-9,-10,-11,-12});
		homeFelder.add(new int[]{-13,-14,-15,-16});
	}
	
	private void initialisiereZielfelder() {

		//Wie Homefelder aufbauen und einen besseren Namen finden
		zielFelder.add(40);
		zielFelder.add(10);
		zielFelder.add(20);
		zielFelder.add(30);
	}
	
	public int[] gibHomefelder(){
		
		int[] homefeld = homeFelder.get(0);
		homeFelder.remove(0);
		return homefeld;
	}
	
	public int gibZielfeld(){
		
		int zielfeld = zielFelder.get(0);
		zielFelder.remove(0);
		return zielfeld;
	}
	
	public Figur sucheFigur(int position) {
		return spielfiguren.get(position);
	}
	
	/**
	 * Gibt die aktuelle Instanz des Spielfeldes zurueck
	 * @return
	 */
	public static Spielfeld getInstanz() {
		if (instanz == null) {
			instanz = new Spielfeld();
		}
		
		return instanz;
	}
	
	/**
	 * Gibt die HashMap mit den Spielfiguren zurueck
	 * @return
	 */
	public HashMap<Integer, Figur> getSpielfiguren() {
		return this.spielfiguren;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		Figur figur = (Figur) arg;
		
		int altePosition = -100;
		// Alte Position ermitteln
		for (int i : spielfiguren.keySet()) {
			if (spielfiguren.get(i).getId() == figur.getId()) {
				altePosition = i;
				break;
			}
		}
		
		// Nur eine Figur entfernen, wenn diese auch schon auf dem Spielfeld ist 
		if (altePosition != -100) {
			// Alte Position entfernen
			spielfiguren.remove(altePosition);
		}
		
		// Neue Position besetzen
		spielfiguren.put(figur.getPosition(), figur);
		
		// View benachrichtigen
		notifyObservers(this);
	}

	

}
