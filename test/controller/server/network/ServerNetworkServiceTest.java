package controller.server.network;

import java.awt.Color;
import java.io.PrintStream;

import model.Figur;
import model.Spieler;
import model.Spielfeld;

import org.junit.Test;

import controller.NetworkService;
import controller.client.network.ClientNetworkService;
import controller.server.ServerController;
import view.SpielfeldView;

public class ServerNetworkServiceTest {

	NetworkService networkService = NetworkService.getInstance();

	ServerNetworkService serverNetworkService = ServerNetworkService
			.getInstance();
	
	@Test
	public void testFigurGeaendert() throws Exception {
		// Erstellt ein neues Spielfeld Model
		Spielfeld model = Spielfeld.getInstanz();

		// Erstellt das Hauptfenster
		SpielfeldView view = new SpielfeldView(model, 250, 150);
		view.setVisible(true);
		
		serverNetworkService.startServer(8181);
		
		try {
			// 2 Sekunden auf den server warten
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		
		networkService.connectToServer("localhost", 8181);
		
		try {
			// 2 Sekunden auf den server warten
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		Spieler spieler = new Spieler("Test");
		
		for (Figur figur : spieler.getFiguren()) {
			figur.addObserver(model);
			figur.setPosition(figur.getPosition());
		}

		// Positionen verschieben
		int j = 10;
		for (int i = 0; i <= 40; i++) {
			try {								
				spieler.getFiguren().get(0).setPosition(i);
				networkService.sendeFigurenAnClients(spieler.getFiguren().get(0));
				j++;

				if (j > 40) {
					j = 1;
				}

				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
