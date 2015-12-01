package controller.client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.SpielfeldView;
import model.Figur;
import model.Spieler;
import model.Spielfeld;

public class ClientController {

	private static ClientController instance;
	
	public static ClientController getInstance() {
		if (instance == null) {
			instance = new ClientController();
		}
		
		return instance;
	}
	
	public void start(String spielerName) {
		// Erstellt ein neues Spielfeld Model
		Spielfeld model = Spielfeld.getInstanz();
		
		// Erstellt das Hauptfenster
		SpielfeldView view = new SpielfeldView(model, 250, 150);
		view.setVisible(true);
	}
	
	/**
	 * 
	 * @param ausgewaehlteFigurId	
	 * @param geschlageneFigurId 	optional
	 * @param neuePosition
	 */
	public void aktualisiereSpielfeld(String ausgewaehlteFigurId, String geschlageneFigurId, int neuePosition) 
	{
		Figur ausgewaehltFigur = sucheFigur(ausgewaehlteFigurId);
		
		Figur geschlageneFigur = null;
		if(geschlageneFigurId != null) {
			geschlageneFigur = sucheFigur(geschlageneFigurId);
		}
		
		ausgewaehltFigur.setPosition(neuePosition);
		if(geschlageneFigur != null) {
			geschlageneFigur.setPosition(geschlageneFigur.getHausFeld());
		}
	}
	
	/**
	 * 
	 * @param moeglichkeiten
	 * @param wurfAnzahl
	 */
	public void zeigeMoeglichkeiten(List<String> moeglichkeiten, int wurfAnzahl) {
		ArrayList<Figur> figuren = new ArrayList<Figur>(4);
		
		for(String figurId : moeglichkeiten) {	
			figuren.add(sucheFigur(figurId));
		}

		Spielfeld.getInstanz().setMoeglichkeiten(figuren);
	}
	
	/**
	 * Sucht eine Figur auf dem Spielfeld
	 * @param id
	 * @return
	 */
	private Figur sucheFigur(String id) {
		for(Figur f : Spielfeld.getInstanz().getSpielfiguren().values()) {
			if(f.getId().equals(id)) 
				return f;
		}
		
		return null;
	}
	
}
