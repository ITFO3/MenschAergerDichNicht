package GUI;

import java.awt.Color;

import MenschAergerDichNicht.Figur;
import MenschAergerDichNicht.Spielfeld;

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
		//Figur figur = new Figur("Test", Color.red, 40);
		//figur.addObserver(model);
		
		//Figur figur2 = new Figur("Test2", Color.blue, 10);
		//figur2.addObserver(model);
		
		// Positionen verschieben
//		int j = 10;
//		for (int i = 0; i <= 40; i++) {
//			try {
//				figur.setPosition(i);
//				figur2.setPosition(j);
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
	}

}
