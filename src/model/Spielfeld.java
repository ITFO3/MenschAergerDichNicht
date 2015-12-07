package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Das Spielfeld, auf dem sich die Figuren befinden
 * @author Dominik Bittner
 */
public class Spielfeld extends Observable implements Observer {
    /**
     * Die Instanz des Spielfeldes
     */
    private static Spielfeld instance;

    private ArrayList<Spieler> spieler = new ArrayList<Spieler>();

    private ArrayList<Integer> zielFelder = new ArrayList<Integer>();
    private ArrayList<int[]> homeFelder = new ArrayList<int[]>();

    private ArrayList<Figur> moeglichkeiten = new ArrayList<Figur>();
    int wurfAnzahl = 0;

    /**
     * HashMap mit den Spielfiguren
     * Key: Position auf dem Feld
     * Value: Spielfigur
     */
    private HashMap<Integer, Figur> spielfiguren;

    /**
     * Gibt die aktuelle Instanz des Spielfeldes zurueck
     * @return
     */
    public static Spielfeld getInstance() {
        if (instance == null) {
            instance = new Spielfeld();
        }

        return instance;
    }

    /**
     * Erstellt ein neues Spielfeld
     */
    private Spielfeld() {
        spielfiguren = new HashMap<Integer, Figur>();
    }

    public Figur sucheFigur(int position) {
        Figur f = spielfiguren.get(position);

        return f;
    }


    public ArrayList<Figur> getMoeglichkeiten() {
        return moeglichkeiten;
    }


    public ArrayList<Spieler> getSpieler() {
        return spieler;
    }


    public void setSpieler(ArrayList<Spieler> spieler) {
        this.spieler = spieler;
    }


    public void setMoeglichkeiten(ArrayList<Figur> moeglichkeiten) {
        this.moeglichkeiten = moeglichkeiten;
    }


    public int getWurfAnzahl() {
        return wurfAnzahl;
    }


    public void setWurfAnzahl(int wurfAnzahl) {
        this.wurfAnzahl = wurfAnzahl;
    }


    /**
     * Gibt die HashMap mit den Spielfiguren zurueck
     * @return
     */
    public HashMap<Integer, Figur> getSpielfiguren() {
        return this.spielfiguren;
    }

    /**
     * Sucht zunaechst die ausgewaehlte Figur aus und bewegt sie um die
     * angegebene Anzahl vor
     *
     * @param figur
     * @param anzahl
     */
    public int bewegeFigur(Figur figur, int wurfAnzahl) {
        // Die Regeln wurden einen Schritt vorhere überprüft. Es wird nur
        // eine Figur übergeben, die bewegt werden kann.
        // Jetzt muss nur noch die Figur gesetzt werden
        if (figur.getPosition() == figur.getHausFeld())
        {
            return figur.getStartFeld();
        }
        else
        {
            int newPosition = figur.getPosition() + wurfAnzahl;
            // Würde die Figur über das letzte Feld gelangen,
            // so muss es in die Zielfelder gehen
            if(newPosition > figur.getEndFeld())
            {
                if(newPosition - figur.getEndFeld() > 4)
                {
                    // Hier wird ein Fehler erzeugt, weil man diese Figur nicht
                    // auswählen konnte und der Fehelr normalerweise nicht auftritt
                    newPosition = 100;
                }

                if(figur.getPosition() > figur.getEndFeld())
                {
                    newPosition = figur.getPosition() + wurfAnzahl;
                }
                else
                {
                    newPosition = figur.getZielFelder()[newPosition - figur.getEndFeld() - 1];
                }
            }

            return newPosition;

            //NetworkService.getInstance().sendeFigurAnClients(figur);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        Figur figur = (Figur) arg;

        int altePosition = -100;
        // Alte Position ermitteln
        for (int i : spielfiguren.keySet()) {
            if (spielfiguren.get(i).getId() == figur.getId()) {
                altePosition = i;
                break;
            }
        }

        // Nur eine Figur entfernen, wenn diese auch schon auf dem Spielfeld ist
        if (altePosition != -100) {
            // Alte Position entfernen
            spielfiguren.remove(altePosition);
        }

        // Neue Position besetzen
        spielfiguren.put(figur.getPosition(), figur);

        // View benachrichtigen
        notifyObservers(this);
    }


}
