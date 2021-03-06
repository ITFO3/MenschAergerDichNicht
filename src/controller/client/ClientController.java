package controller.client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.SpielfeldCanvas;
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
        Spielfeld model = Spielfeld.getInstance();

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
    public void aktualisiereSpielfeld(String ausgewaehlteFigurId, int neuePosition)
    {
        Figur ausgewaehltFigur = sucheFigur(ausgewaehlteFigurId);
        ausgewaehltFigur.setPosition(neuePosition);
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

        Spielfeld spielFeld = Spielfeld.getInstance();
        spielFeld.setMoeglichkeiten(figuren);
        spielFeld.setWurfAnzahl(wurfAnzahl);
    }

    /**
     * Sucht eine Figur auf dem Spielfeld anhand der ID
     * @param id
     * @return
     */
    private Figur sucheFigur(String id) {
        for(Figur f : Spielfeld.getInstance().getSpielfiguren().values()) {
            if(f.getId().equals(id))
                return f;
        }

        return null;
    }
}
