package view.admin.network;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Spieler;
import controller.NetworkService;
import controller.client.network.ClientNetworkService;
import controller.server.ServerController;
import controller.server.network.ServerNetworkService;

/**
 * JPanel zur Serveradministration
 * 
 * @author ChrisWun
 * 
 */
public class ServerAdminPanel extends JPanel {

	JTextField serverServerPortTextField;

	JTextField spielerNameTextField;

	NetworkService networkService = NetworkService.getInstance();

	ServerNetworkService serverService = ServerNetworkService.getInstance();

	JLabel connectionStatusLabel;

	JList<String> connectedSpielerList;

	JButton startGameButton;
	
	ClientNetworkService clientNetworkService = ClientNetworkService.getInstance();

	public static ServerAdminPanel instance;

	public ServerAdminPanel() {
		/*
		 * Port
		 */
		JPanel serverPortPanel = new JPanel();
		JLabel serverPortLabel = new JLabel("Port:");
		serverServerPortTextField = new JTextField(5);
		serverPortPanel.add(serverPortLabel);
		serverPortPanel.add(serverServerPortTextField);
		add(serverPortPanel);
		serverPortPanel.setBounds(150, 0, 100, 30);

		/*
		 * Spielername
		 */
		JPanel spielerNamePanel = new JPanel();
		JLabel spielerNameLabel = new JLabel("Spielername");
		spielerNameTextField = new JTextField(10);
		spielerNamePanel.add(spielerNameLabel);
		spielerNamePanel.add(spielerNameTextField);
		add(spielerNamePanel);
		spielerNamePanel.setBounds(150,40,200,30);

		/*
		 * Startbutton
		 */
		JButton startServerButton = new JButton("Start Server");
		startServerButton.addActionListener(new StartServerButtonListener());
		add(startServerButton);
		startServerButton.setBounds(125, 80, 150, 30);

		setLayout(null);

		instance = this;
	}

	private class StartServerButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			Integer port = Integer.valueOf(serverServerPortTextField.getText());
			boolean result = networkService.startServer(port,
					ServerAdminPanel.this);
			networkService.connectToServer("localhost", port);
			clientNetworkService.sendeSpielerName(spielerNameTextField.getText());
			createAndAddConnectionStatusLabel(result);
			
		}

		/**
		 * 
		 * Erstellt und fuegt ein Label zur Anzeige hinzu, ob die Verbindung zu
		 * einem Server erfolgreich oder nicht erfolgreich war.
		 */
		private void createAndAddConnectionStatusLabel(boolean result) {
			if (connectionStatusLabel == null) {
				connectionStatusLabel = new JLabel();
			}
			if (result) {
				connectionStatusLabel.setText("Server erfolgreich gestartet");
				connectionStatusLabel.setForeground(Color.GREEN);
			} else {
				connectionStatusLabel.setText("Serverstart nicht erfolgreich");
				connectionStatusLabel.setForeground(Color.RED);
			}
			connectionStatusLabel.setBounds(125, 120, 200, 20);
			connectionStatusLabel.setVisible(true);

			if (result) {
				startGameButton = new JButton("Spiel starten");
				startGameButton.addActionListener(new StartGameButtonListener());
				startGameButton.setBounds(125, 300, 150, 30);
				startGameButton.setVisible(true);
			}

			add(connectionStatusLabel);
			add(startGameButton);
			revalidate();
			repaint();
		}
	}

	private class StartGameButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ServerController.getInstance().starteSpiel(serverService.getConnectedPlayers());
		}

	}

	public void updateConnectedSpielerAndStartServerPanel(List<Spieler> spieler) {
		String[] spielernamen = new String[spieler.size()];

		int i = 0;

		for (Spieler tempSpieler : spieler) {
			spielernamen[i] = tempSpieler.getName();
			i++;
		}

		if (connectedSpielerList == null) {
			connectedSpielerList = new JList();
		}

		connectedSpielerList.setBounds(125, 160, 200, 80);
		JLabel connectedSpielerListLabel = new JLabel("Verbundene Spieler:");
		connectedSpielerListLabel.setBounds(125, 140, 200, 20);

		connectedSpielerList.setListData(spielernamen);
		connectedSpielerList.setBounds(125, 165, 200, 80);

		this.add(connectedSpielerListLabel);
		this.add(connectedSpielerList);
		connectedSpielerList.revalidate();
		connectedSpielerList.repaint();
		ServerAdminPanel.this.revalidate();
		ServerAdminPanel.this.repaint();
	}
}
