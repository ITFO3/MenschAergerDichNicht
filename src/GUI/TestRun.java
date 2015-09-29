package GUI;

import java.awt.Color;

import MenschAergerDichNicht.Figur;
import MenschAergerDichNicht.Spielfeld;

public class TestRun {

	public static void main(String[] args) {
		Spielfeld model = Spielfeld.getInstanz();
		
		SpielfeldView view = new SpielfeldView(model, 500, 500);
		view.show();
		
		Figur figur = new Figur("Test", Color.red);
		figur.addObserver(model);
		
		for (int i = -16; i <= 40; i++) {
			try {
				figur.setPosition(i);
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

}
