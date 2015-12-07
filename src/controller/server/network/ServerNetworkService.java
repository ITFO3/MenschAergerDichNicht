package controller.server.network;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.server.ServerController;
import model.Figur;
import model.Spieler;
import model.Spielfeld;
import view.admin.network.ServerAdminPanel;

/**
 * Stellt die Grundlegenden Funktionalitaeten zum erstellen eines Servers
 * bereit.
 *
 * @author ChrisWun
 *
 */
public class ServerNetworkService {

    public enum ServerStatus {
        NOT_RUNNING, RUNNING;
    }

    private static ServerNetworkService instance;

    public static ServerNetworkService getInstance() {
        if (ServerNetworkService.instance == null) {
            ServerNetworkService.instance = new ServerNetworkService();
        }
        return ServerNetworkService.instance;
    }

    List<ClientHandler> clients;
    List<Spieler> connectedPlayer = new ArrayList<Spieler>();
    ServerSocket serverSocket;
    ServerStatus serverStatus;

    private ServerNetworkService() {
        clients = new ArrayList<ClientHandler>();
        serverStatus = ServerStatus.NOT_RUNNING;
    }

    public List<ClientHandler> getClients() {
        return this.clients;
    }

    public List<Spieler> getConnectedPlayers() {
        return this.connectedPlayer;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public Spieler createNewSpieler(String spielerName) {
        Spieler result = null;

        int spielerZahl = connectedPlayer.size() + 1;

        int hausFeld = 0;
        int startFeld = 0;
        int endFeld = 0;

        switch (spielerZahl) {
            case 1:
                hausFeld = -4;
                startFeld = 1;
                endFeld = 40;
                result = new Spieler(spielerName, Color.RED, Spieler.zielFelderSpielerEins, hausFeld, startFeld, endFeld);
                break;
            case 2:
                hausFeld = -8;
                startFeld = 11;
                endFeld = 30;
                result = new Spieler(spielerName, Color.BLUE, Spieler.zielFelderSpielerZwei, hausFeld, startFeld, endFeld);
                break;
            case 3:
                hausFeld = -12;
                startFeld = 21;
                endFeld = 20;
                result = new Spieler(spielerName, Color.GREEN, Spieler.zielFelderSpielerDrei, hausFeld, startFeld, endFeld);
                break;
            case 4:
                hausFeld = -16;
                startFeld = 31;
                endFeld = 10;
                result = new Spieler(spielerName, Color.YELLOW, Spieler.zielFelderSpielerVier, hausFeld, startFeld,
                        endFeld);
                break;
            default:
                // Sollte nicht auftreten.
                break;
        }
        return result;
    }

    public void processInputData(ClientHandler client, String input) {
        String keyValuePairs[] = input.split(";");
        Map<String, String> orderedKeyValues = new HashMap<String, String>();

        for (String keyValuePair : keyValuePairs) {
            String keyValuePairSeperated[] = keyValuePair.split("=");
            orderedKeyValues.put(keyValuePairSeperated[0], keyValuePairSeperated[1]);
        }

        System.out.println("Server empfaengt: " + orderedKeyValues.toString());

        processInput(client, orderedKeyValues);
    }

    private void processInput(ClientHandler client, Map<String, String> orderedKeyValues) {
        Set<String> keySet = orderedKeyValues.keySet();

        for (String key : keySet) {
            DataObjectEnum dataObject = DataObjectEnum.valueOf(key);
            switch (dataObject) {
                case SPIELERNAME:
                    processSpielernameInput(client, orderedKeyValues.get(key));
                    break;
                case FIGURGEAENDERT:
                    processFigurGeaendertInput(client, orderedKeyValues.get(key));
                    break;
                default:
                    // Sollte nicht auftreten
                    break;
            }
        }
    }

    private void processSpielernameInput(ClientHandler client, String spielerName) {
        Spieler spieler = createNewSpieler(spielerName);
        client.setSpielerName(spielerName);
        connectedPlayer.add(spieler);
        ServerAdminPanel.getInstance()
                .updateConnectedSpielerAndStartServerPanel(connectedPlayer);
    }

    private void processFigurGeaendertInput(ClientHandler client, String input) {
        Figur figur = null;
        int neuePosition = 0;

        String[] seperated = input.split(",");
        String figurId = seperated[0];
        neuePosition = Integer.parseInt(seperated[1]);

        for(Figur f : Spielfeld.getInstance().getSpielfiguren().values()) {
            if(f.getId().equals(figurId))
                figur = f;
        }

        ServerController.getInstance().bewegeFigur(figur, neuePosition);
        List<Spieler> spielerList = Spielfeld.getInstance().getSpieler();
        for(Spieler spieler : spielerList){
        	if(spieler.getName().equals(client.getSpielerName())){
        		spieler.setState(Spieler.State.VOID);
        	}
        }
    }

    public void sendeStarteSpielSignalAnAlleClients() {
        String data = DataObjectEnum.STARTGAME.toString() + "=true";

        for (ClientHandler client : clients) {
            client.sendeDaten(data);
        }
    }

    public void startServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        ClientAcceptor clientAcceptor = new ClientAcceptor(serverSocket,
                clients);
        clientAcceptor.start();
        serverStatus = ServerStatus.RUNNING;
    }
}
