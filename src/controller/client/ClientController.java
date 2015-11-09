package controller.client;

import java.util.ArrayList;
import java.util.List;

import model.Figur;
import model.Spieler;
import model.Spielfeld;
import controller.server.ServerController;

public class ClientController {

	
	private List<Spieler> spieler;
	private Spielfeld spielfeld;
	//muss noch initialisiert werden
	private ServerController server;
	
	void aktualisiereSpielfeld(String ausgewaehlteFigurId, String geschlageneFigurId, int neuePosition) {
		
		Figur ausgewaehltFigur = sucheFigur(ausgewaehlteFigurId);
		Figur geschlageneFigur = null;
		if(geschlageneFigurId != null) geschlageneFigur = sucheFigur(geschlageneFigurId);
		
		
		ausgewaehltFigur.setPosition(neuePosition);
		if(geschlageneFigur != null)
			geschlageneFigur.setPosition(geschlageneFigur.getHausFeld());	
	}
	
	void zeigeMoeglichkeiten(List<String> moeglichkeiten, int wurfAnzahl) {
		
		ArrayList<Figur> figuren = new ArrayList<Figur>(4);
		
		for(String figurId : moeglichkeiten) {
			
			figuren.add(sucheFigur(figurId));
		}
		
		
	}
	
	private Figur sucheFigur(String id) {
		for(Figur f : spielfeld.getSpielfiguren().values()) {
			if(f.getId().equals(id)) 
				return f;
		}
		
		return null;
	}
	
}
