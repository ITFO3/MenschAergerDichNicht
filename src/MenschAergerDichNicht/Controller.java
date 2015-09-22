package MenschAergerDichNicht;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

public class Controller {

	ArrayList<Color> farben = new ArrayList<Color>();
	
	private ArrayList<Spieler> spieler = new ArrayList<Spieler>();
	
	public Controller() {
		initialisiereFarben();
		//view.setzteAufsSpielfeld(eigeneSpielfiguren, this);
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
	
	

	public void initialisiereSpieler(int anzahlSpieler){
		
		for (int i = 0; i < anzahlSpieler; i++) {
			
			int zufallsZahl = (int)(Math.random() * ((farben.size() * 1.0) - 1.0) + 1.0);
			Color c = farben.get(zufallsZahl);
			farben.remove(zufallsZahl);			
//			spieler.add(new Spieler(c));						
		}
	}
	
	public void ueberpruefeMoeglichkeiten(Spieler spieler, int anzahl) {
		
	}
	
	public void sendeMoeglichkeitenAnClienten(ArrayList<Figur> figuren) {
		
	}
	
	public void platziereSpielerAufStartfeld(Spieler spieler) {
		
	}
	
	public boolean ueberpruefeSpielende() {
		return false;
	}
	
	public void bewegeFigur(Figur figur, int anzahl) {
		
	}
	
	public void starteSpiel() {
		
	}
	
	public void erstelleSpiel(int anzahl, String name) {
		
	}
	
	public boolean treteSpielBei(String ip, String name) {
		return false;
	}
	
}
