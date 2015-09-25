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
		figur.setPosition(-16);
		figur.addObserver(model);
		figur.setPosition(-15);
	}

}
