package view;

import java.awt.Color;

import model.Spielfeld;
import model.Figur;
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
		
		//new ClientController();
		
		//Figur figur2 = new Figur("Test2", Color.blue, 10);
		//figur2.addObserver(model);
		
		// Positionen verschieben
		int j = 10;
		for (int i = 0; i <= 40; i++) {
			try {
				figur.setPosition(i);
//				figur2.setPosition(j);
				j++;
				
				if (j > 40) {
					j = 1;
				}
				
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

}
