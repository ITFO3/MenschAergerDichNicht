package controller.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;

import view.SpielfeldCanvas;
import model.Figur;
import model.Spieler;
import model.Spielfeld;
import controller.server.ServerController;

public class ClientController {

	
	private List<Spieler> spieler;
	//muss noch initialisiert werden
	private ServerController server;


	
	public void aktualisiereSpielfeld(String ausgewaehlteFigurId, String geschlageneFigurId, int neuePosition) {
		
		Figur ausgewaehltFigur = sucheFigur(ausgewaehlteFigurId);
		
		Figur geschlageneFigur = null;
		if(geschlageneFigurId != null) 
			geschlageneFigur = sucheFigur(geschlageneFigurId);
		
		
		ausgewaehltFigur.setPosition(neuePosition);
		if(geschlageneFigur != null)
			geschlageneFigur.setPosition(geschlageneFigur.getHausFeld());	
	}
	
	public void zeigeMoeglichkeiten(List<String> moeglichkeiten, int wurfAnzahl) {
		
		ArrayList<Figur> figuren = new ArrayList<Figur>(4);
		
		for(String figurId : moeglichkeiten) {
			
			figuren.add(sucheFigur(figurId));
		}
		
		Spielfeld.getInstanz().setMoeglichkeiten(figuren);
		
	}
	
	private Figur sucheFigur(String id) {
		for(Figur f : Spielfeld.getInstanz().getSpielfiguren().values()) {
			if(f.getId().equals(id)) 
				return f;
		}
		
		return null;
	}
	
}
