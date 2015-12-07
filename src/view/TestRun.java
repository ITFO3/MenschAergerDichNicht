package view;

import java.awt.Color;
import java.util.ArrayList;

import controller.server.ServerController;
import model.Figur;
import model.Spieler;
import model.Spielfeld;

/**
 * Test-Start-Klasse fuer die GUI
 */
public class TestRun {

    public static void main(String[] args) {
        // Erstellt ein neues Spielfeld Model
        Spielfeld model = Spielfeld.getInstance();

        // Erstellt das Hauptfenster
        SpielfeldView view = new SpielfeldView(model, 250, 150);
        view.setVisible(true);

        /*
        // Erstellen von neuen Figuren
        Figur figur = new Figur("Test", Color.RED, -16 , 1, 40, new int[]{41,42,43,44});
        figur.addObserver(model);
        figur.setPosition(-16);
        Spielfeld.getInstanz().setWurfAnzahl(5);
        */

        Spieler s = new Spieler("TestSpieler", Color.RED, new int[] {41,42,43,44}, -1, 1, 40);
        ArrayList<Spieler> al = new ArrayList<Spieler>();
        al.add(s);
        model.setSpieler(al);

        ArrayList<Figur> figuren = s.getFiguren();
        figuren.get(0).setPosition(41);
        figuren.get(1).setPosition(42);
        figuren.get(2).setPosition(43);
        figuren.get(3).setPosition(40);

        Spieler sieger = ServerController.getInstance().ueberpruefeSpielende();
        if(sieger != null)
            System.out.println(sieger.getName());
        else
            System.out.println("Es gibt keinen Sieger");


        //Figur figur2 = new Figur("Test2", Color.blue, 10);
        //figur2.addObserver(model);

        // Positionen verschieben
//		int j = 10;
//		for (int i = 1; i <= 56; i++) {
//			try {
//				controller.aktualisiereSpielfeld(figurId, null, i);
////				figur2.setPosition(j);
//				j++;
//
//				if (j > 56) {
//					j = 1;
//				}
//
//				Thread.sleep(1000);
//			} catch(InterruptedException ex) {
//				Thread.currentThread().interrupt();
//			}
//		}

//		Figur figur2 = new Figur("Test2", Color.BLUE, -2 , 0, 39);
//		figur2.addObserver(model);
//		figur2.setPosition(-2);
//		Figur figur3 = new Figur("Test3", Color.YELLOW, -3 , 0, 39);
//		figur3.addObserver(model);
//		figur3.setPosition(-3);
//
//		ArrayList<String> moeglichkeiten = new ArrayList<String>();
//		moeglichkeiten.add(figurId);
//		moeglichkeiten.add(figur2.getId());
//		moeglichkeiten.add(figur3.getId());

//		controller.zeigeMoeglichkeiten(moeglichkeiten, 4);
    }

}
