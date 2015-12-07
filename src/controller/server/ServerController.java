package controller.server;

import java.util.ArrayList;
import java.util.List;

import model.Figur;
import model.Spieler;
import model.Spielfeld;
import model.Wuerfel;
import controller.NetworkService;
import controller.server.network.ClientHandler;
import controller.server.network.ServerNetworkService;

public class ServerController {

    private static ServerController instance;

    /**
     * Gibt die aktuelle Instanz des Spielfeldes zurueck
     *
     * @return Die aktuelle Instanz
     */
    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }

        return instance;
    }


    private boolean _figurGewaehlt;
    private ServerNetworkService serverNetworkService = ServerNetworkService.getInstance();

    private ServerController() {
        // Keine normale Initialisierung ermoeglichen
    }

    /**
     * Startet das Spiel
     *
     * @param spieler   Eine Liste von Spielern, die an diesem Spiel teilnehmen
     */
    public void starteSpiel(List<Spieler> spieler) {
        Spielfeld.getInstance().setSpieler(spieler);
        sendeSpielerAnClients(spieler);
        ServerNetworkService.getInstance().sendeStarteSpielSignalAnAlleClients();

        int beginner = Wuerfel.ermittleBeginner(spieler.size());

        Spieler sieger = null;

        while (sieger == null) {
        	Spieler aktuellerSpieler = spieler.get(beginner++);
        	
            int wurfAnzahl = Wuerfel.wuerfel();
            Spielfeld.getInstance().setWurfAnzahl(wurfAnzahl);

            ArrayList<Figur> moeglichkeiten = ueberpruefeMoeglichkeiten(
            		aktuellerSpieler, wurfAnzahl);

            sendeMoeglichkeitenAnClient(aktuellerSpieler,
                    moeglichkeiten, wurfAnzahl);

            while (aktuellerSpieler.getState() != Spieler.State.VOID) {
            	// Ne schleife zum warte.
            	try {
					Thread.sleep(500l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }

            //TODO warte auf die Eingabe vom Spieler, welche Figur bewegt werden soll

            if (beginner == spieler.size()) {
                beginner -= spieler.size();
            }

            sieger = ueberpruefeSpielende();
        }

        //TODO Den Gewinner auf der Oberfläche anzeigen
    }

    /**
     * Ueberprueft die Moeglichkeiten fuer den uebergebenen Spieler
     *
     * @param spieler 	Der Spieler, der an der Reihe ist
     * @param wurfzahl	Die gewuerfelte Zahl
     */
    public ArrayList<Figur> ueberpruefeMoeglichkeiten(Spieler spieler, int wurfzahl) {
        ArrayList<Figur> bewegbareFiguren = new ArrayList<Figur>();
        ArrayList<Figur> figuren = spieler.getFiguren();
        int neuePosition;

        // Es wurde eine 6 gewuerfelt
        if (wurfzahl == 6) {
            Figur figurAufStartfeld = Spielfeld.getInstance().sucheFigur(figuren.get(0).getStartFeld());
            // Wenn keine oder eine gegnerische Figur auf dem Startfeld steht
            if (figurAufStartfeld == null ||
                figurAufStartfeld.getStartFeld() != figuren.get(0).getStartFeld()) {
                for (Figur f : figuren) {
                    if (f.getHausFeld() == f.getPosition()) {
                        bewegbareFiguren.add(f);
                    }
                }
            }
        }

        for (Figur f : figuren) {
            neuePosition = Spielfeld.getInstance().bewegeFigur(f, wurfzahl);

            if (neuePosition > f.getZielFelder()[3]) {
                // Falls man ueber das Zielfeld springen wuerde
                continue;
            }

            Figur andereFigur = Spielfeld.getInstance().sucheFigur(neuePosition);

            // Wenn eine Figur von dem Spieler auf dem Zielfeld steht geht es nicht
            if (andereFigur != null && andereFigur.getStartFeld() == f.getStartFeld()) {
                continue;
            }

            // Alle anderen Figuren sind moeglich
            if (!bewegbareFiguren.contains(f)) {
                bewegbareFiguren.add(f);
            }
        }

        return bewegbareFiguren;
    }

    private void sendeMoeglichkeitenAnClient(Spieler s, ArrayList<Figur> figuren, int wurfanzahl) {
        List<ClientHandler> clients = ServerNetworkService.getInstance().getClients();
        ClientHandler client = null;

        for (ClientHandler c : clients) {
            if (c.getSpielerName().equals(s.getName())) {
                client = c;
            }
        }

        NetworkService.getInstance().sendeMoeglichkeitenAnClient(client,
                figuren, wurfanzahl);
        s.setState(Spieler.State.WATING_FOR_SELECTION);
    }

    private void sendeSpielerAnClients(List<Spieler> spieler) {
        for (Spieler s : spieler) {
            NetworkService.getInstance().sendeSpielerAnClients(s);
        }
    }

    /**
     * Ueberprueft ob das Spielende erreicht wurde Aeussere Schleife:
     * Ueberprueft jeden Spieler Innere Schleife: Ueberprueft ob alle Figuren
     * vom aktuellen Spieler im Ziel sind
     *
     * @return true, Wenn die Figuren eines Spielers alle im Ziel sind
     */
    public Spieler ueberpruefeSpielende() {

        ArrayList<Spieler> spieler = (ArrayList<Spieler>) Spielfeld.getInstance().getSpieler();

        boolean figurHatEndpositionErreicht = false;
        boolean alleFigurenHabenDieEndpositionenErreicht = true;
        int spielerAnzahl = spieler.size();

        //Überprüfe jeden Spieler
        for (int i = 0; i < spielerAnzahl; i++) {

            Spieler s = spieler.get(i);
            ArrayList<Figur> figuren = s.getFiguren();
            alleFigurenHabenDieEndpositionenErreicht = true;
            int figurenAnzahl = figuren.size();

            //Überprüfe jede Figur bei dem aktuellen Spieler
            for (int j = 0; j < figurenAnzahl; j++) {

                Figur f = figuren.get(j);
                int[] zielFelder = f.getZielFelder();
                figurHatEndpositionErreicht = false;

                //Überprüfe alle vier Zielfelder vom Spieler
                for (int k = 0; k < zielFelder.length; k++) {

                    //Ist die aktuelle Figur im Zielfeld angelangt?
                    if(f.getPosition() == zielFelder[k]) {
                        //Ja, überprüfe die nächste Figur
                        figurHatEndpositionErreicht = true;
                        break;
                    }
                }
                //Eine der Figuren ist nicht in der Endposition, überprüfe den nächsten Spieler
                if(!figurHatEndpositionErreicht) {
                    alleFigurenHabenDieEndpositionenErreicht = false;
                    break;
                }
            }


            //Alle Figuren sind im Ziel, gebe den Sieger aus
            if(alleFigurenHabenDieEndpositionenErreicht) {
                return s;
            }
        }

        return null;
    }

    public void bewegeFigur(Figur figur, int neuePosition)
    {
        Figur betroffenePositionFigur = testePosition(neuePosition);
        if (betroffenePositionFigur != null) {
            betroffenePositionFigur.setPosition(betroffenePositionFigur
                    .getHausFeld());

            NetworkService.getInstance().sendeFigurAnClients(betroffenePositionFigur);
        }

        figur.setPosition(neuePosition);
        NetworkService.getInstance().sendeFigurAnClients(figur);

        _figurGewaehlt = true;
    }

    private Figur testePosition(int position) {
        return Spielfeld.getInstance().sucheFigur(position);
    }
}
