package controller.client.network;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import model.Figur;
import model.Spieler;
import model.Spielfeld;
import controller.NetworkService;
import controller.client.ClientController;
import controller.server.network.DataObjectEnum;
import controller.server.network.ServerNetworkService;

/**
 * @author ChrisWun
 */
public class ClientNetworkService {

    private static ClientNetworkService clientNetworkService;

    public static ClientNetworkService getInstance() {
        if (clientNetworkService == null) {
            clientNetworkService = new ClientNetworkService();
        }

        return clientNetworkService;
    }

    private ClientController clientController;
    private NetworkService networkService;

    private ClientNetworkService() {
        this.networkService = NetworkService.getInstance();
        this.clientController = clientController.getInstance();
    }

    public void sendeDaten(String data) {
        Client client = networkService.getClient();
        PrintStream os = client.getOutputStream();
        os.println(data);
        System.out.println("Client an Server: " + data);
        os.flush();
    }

    public void sendeFigur(Figur figur) {
        String nachricht = "FIGURGEANDERT=" + figur.getId() + "," + figur.getPosition();
        sendeDaten(nachricht);
    }

    public void sendeSpielerName(String text) {
        String nachricht = DataObjectEnum.SPIELERNAME.toString() + "=" + text + ";";
        sendeDaten(nachricht);
    }

    // FIGURGEAENDERT=Test:1,2;FIGURGEAENDERT=Test:2,4
    public void empfangeDaten(String data) {
        String[] seperated = data.split(";");

        for (String seperat : seperated) {
            String[] value = seperat.split("=");
            String aenderungsTyp = value[0];

            if (aenderungsTyp.equals(DataObjectEnum.FIGURGEAENDERT.toString())) {
                figurGeaendert(value[1]);
            } else if (aenderungsTyp.equals(DataObjectEnum.MOEGLICHKEITEN.toString())) {
                zeigeMoeglichkeiten(value[1]);
            } else if (aenderungsTyp.equals(DataObjectEnum.SPIELERNAME.toString())) {
                empfangeSpieler(value[1]);
            } else if (aenderungsTyp.equals(DataObjectEnum.STARTGAME.toString())) {
                starteSpiel(value[1]);
            }
        }
    }

    private void figurGeaendert(String daten) {
        // Bsp: Test:3,19

        String[] seperated = daten.split(",");
        String id = seperated[0];
        Integer neuePosition = Integer.valueOf(seperated[1]);
        clientController.aktualisiereSpielfeld(id, neuePosition);
    }

    private void zeigeMoeglichkeiten(String daten) {
        // {Test:3,Test:4},2

        String[] seperated = daten.split("}");
        String figuren = seperated[0].substring(1);
        int wuerfelAnzahl = Integer.valueOf(seperated[1].substring(1));

        List<String> figurenListe = new ArrayList<String>();

        String[] figurenArray = figuren.split(",");

        for (String figur : figurenArray) {
            figurenListe.add(figur);
        }

        clientController.zeigeMoeglichkeiten(figurenListe, wuerfelAnzahl);
    }

    private void empfangeSpieler(String daten) {
        Spieler spieler = ServerNetworkService.getInstance().createNewSpieler(daten);

        for (Figur figur : spieler.getFiguren()) {
            figur.addObserver(Spielfeld.getInstance());
            figur.setPosition(figur.getPosition());
        }
    }

    private void starteSpiel(String string) {
        Client client = networkService.getClient();
        clientController.start(client.getSpielerName());
    }

}
