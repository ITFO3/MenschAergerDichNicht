package menschaergerdichnicht;

import java.util.ArrayList;
import java.util.List;

public class ClientController {

	private List<Spieler> spieler;
	private Spielfeld spielfeld;
	private Controller serverController;
	
	
	public ClientController() {
		
		serverController.gibName();
		serverController.gibFarbe();
		
		//Farbe vom Server erhalten
		//Figuren auf den Startblock setzen
	}
	
	
	
	public void aktualisiereSpielfeld(String figurId, int neuePosition) {
		
		sucheFigur(figurId).setPosition(neuePosition);		
	}
	
	private Figur sucheFigur(String figurId) {
		int gefunden = -1;
		
		for (int i = 0; i < spieler.size(); i++) {
			Spieler s = spieler.get(i);
			ArrayList<Figur> figuren = s.getFiguren();

			for (int j = 0; j < figuren.size(); j++) {
				if (figuren.get(j).getId().equals(figurId)) {
					gefunden = j;
					break;
				}
			}
			
			if (gefunden != -1) return figuren.get(gefunden);
		}
		return null;
	}
	
	public void zeigeMoeglichkeiten(List<Figur> moeglichkeiten) {
		
		
		for (Figur figur : moeglichkeiten) {
			
			//moeglichkeiten.highlight();
			//hiernach wartet das Programm auf die Auswahl vom Spieler
			//diese Antwort wird direkt zum ServerController geschickt
		}
	}
	
	
}
