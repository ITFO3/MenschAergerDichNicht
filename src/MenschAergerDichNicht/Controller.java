package MenschAergerDichNicht;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

public class Controller {

	private Spieler[] spieler = new Spieler[4];
	ArrayList<Color> farben = new ArrayList<Color>();
	int[] zielFelder = new int[4];
	
	public Controller(String name) {
		initialisiereFarben();
		initialisiereStartfelder();
		initialisiereZielfelder();
		initialisiereSpieler(name);
		//view.setzteAufsSpielfeld(eigeneSpielfiguren, this);
	}
	
	
	//Verschachteltes Array
	private void initialisiereStartfelder() {
		// TODO Auto-generated method stub
		
	}

	private void initialisiereFarben() {
		farben.add(Color.red);
		farben.add(Color.green);
		farben.add(Color.black);
		farben.add(Color.blue);
		farben.add(Color.CYAN);
		farben.add(Color.MAGENTA);
		farben.add(Color.PINK);
		farben.add(Color.YELLOW);
	}
	
	private void initialisiereZielfelder() {
		
		zielFelder[0] = 40;
		zielFelder[1] = 10;
		zielFelder[2] = 20;
		zielFelder[3] = 30;
	}
	
	

	public void initialisiereSpieler(String name){
		
			int zufallsZahl = (int)(Math.random() * ((farben.size() * 1.0) - 1.0) + 1.0);
			Color c = farben.get(zufallsZahl);
			farben.remove(zufallsZahl);
			
			spieler[0] = new Spieler(name, c, zielFelder[0]);						
	}
	
	public void ueberpruefeMoeglichkeiten(Spieler spieler, int anzahl) {
		
	}
	
	public void sendeMoeglichkeitenAnClienten(ArrayList<Figur> figuren) {
		
	}
	
	public void platziereSpielerAufStartfeld(Spieler spieler) {
		
	}
	
	
	/**
	 * Überprüft ob das Spielende erreicht wurde
	 * Äußere Schleife: Überprüft jeden Spieler
	 * Innere Schleife: Überprüft ob alle Figuren vom aktuellen Spieler im Ziel sind
	 * @return true, Wenn die Figuren eines Spielers alle im Ziel sind
	 */
	public boolean ueberpruefeSpielende() {
		
		boolean ende = true;
		
		for (int i = 0; i < spieler.length; i++) {
			
			Spieler s = spieler[i];
			ArrayList<Figur> figuren = s.getFiguren();
			
			for (int j = 0; j < figuren.size(); j++) {
				if(figuren.get(j).getPosition() <= zielFelder[i])
					ende = false;
			}
			
			if(ende) return true;
		}
		
		return false;
	}
	
	
	/**
	 * Sucht zunächst die ausgewählte Figur aus und bewegt sie um die angegebene Anzahl vor
	 * @param figur
	 * @param anzahl
	 */
	public void bewegeFigur(Figur figur, int anzahl) {
	
		for (int i = 0; i < spieler.length; i++) {
			
			for (int j = 0; j < 4; j++) { //Jeder Spieler hat genau 4 Spielfiguren
				
				if(spieler[i].getFiguren().get(j) == figur) {
					
					
					Figur f = spieler[i].getFiguren().get(j);
					int newPosition = f.getPosition() + anzahl;
					f.setPosition(newPosition);
					
					
					for (int i2 = 0; i2 < spieler.length; i2++) {
						
						for (int j2 = 0; j2 < spieler.length; j2++) {
							
							if(spieler[i2].getFiguren().get(j2).getPosition() == newPosition) {
								spieler[i2].getFiguren().get(j2).setPosition(0); //statt 0 das Startfeld
							}
								
						}
					}
				}
				
			}
		}
	}
	
	public void starteSpiel() {
		
	}
	
	public void erstelleSpiel(int anzahl, String name) {
		
	}
	
	public boolean treteSpielBei(String ip, String name) {
		return false;
	}
	
}
