package view;

import java.awt.Color;
import java.util.ArrayList;

import model.Figur;
import model.Spielfeld;
import controller.client.ClientController;
//import controller.client;

/**
 * Test-Start-Klasse fuer die GUI
 */
public class TestRun {

	public static void main(String[] args) {
		// Erstellt ein neues Spielfeld Model
		Spielfeld model = Spielfeld.getInstanz();
		
		// Erstellt das Hauptfenster
		SpielfeldView view = new SpielfeldView(model, 250, 150);
		view.setVisible(true);
		
		// Erstellen von neuen Figuren
		Figur figur = new Figur("Test", Color.RED, -1 , 0, 39);
		figur.addObserver(model);
		figur.setPosition(-1);
		
		String figurId = figur.getId();
		
		ClientController controller = new ClientController();
		
		//Figur figur2 = new Figur("Test2", Color.blue, 10);
		//figur2.addObserver(model);
		
		// Positionen verschieben
//		int j = 10;
//		for (int i = 0; i <= 40; i++) {
//			try {
//				controller.aktualisiereSpielfeld(figurId, null, i);
////				figur2.setPosition(j);
//				j++;
//				
//				if (j > 40) {
//					j = 1;
//				}
//				
//				Thread.sleep(1000);
//			} catch(InterruptedException ex) {
//				Thread.currentThread().interrupt();
//			}
//		}
		
		ArrayList<String> moeglichkeiten = new ArrayList<String>();
		moeglichkeiten.add(figurId);
		
		controller.zeigeMoeglichkeiten(moeglichkeiten, 4);
	}

}
