package controller.server.network;

import java.awt.Color;
import java.io.PrintStream;

import model.Figur;
import model.Spielfeld;

import org.junit.Test;

import controller.NetworkService;
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

		// Erstellen von neuen Figuren
		Figur figur = new Figur("Test", Color.RED, -1, 0, 39);
		figur.addObserver(model);
		figur.setPosition(-1);
		
		for (int i = 0; i < 40; i++) {
			figur.setPosition(i);
//			ServerController.getInstanz().bewegeFigur(figur, 1);
		}
		
//		networkService.startServer(8181, null);
//		Thread.sleep(1000);
//		networkService.connectToServer("localhost", 8181);
//		Thread.sleep(1000);
//		ClientHandler client = serverNetworkService.clients.get(0);
//		PrintStream os = client.getOutputStream();

		// Positionen verschieben
		int j = 10;
		for (int i = 0; i <= 40; i++) {
			try {
				figur.setPosition(i);
				networkService.sendeFigurenAnClients(figur);
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
