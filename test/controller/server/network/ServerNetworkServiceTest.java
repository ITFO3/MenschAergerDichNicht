package controller.server.network;

import java.awt.Color;
import java.io.PrintStream;
import model.Figur;
import model.Spielfeld;

import org.junit.Test;

import controller.NetworkService;
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

		Thread.sleep(1000);
		networkService.connectToServer("10.0.10.15", 8181);
		Thread.sleep(1000);
		
		while(true) {
			
		}
	}
}
