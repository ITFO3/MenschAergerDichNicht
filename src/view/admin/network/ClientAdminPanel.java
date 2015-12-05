package view.admin.network;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.NetworkService;
import controller.client.ClientController;
import controller.client.network.ClientNetworkService;

/**
 * JPanel zur Clientadministration
 * 
 * @author ChrisWun
 * 
 */
public class ClientAdminPanel extends JPanel {

	private class ConnectButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean result = networkService.connectToServer(
					clientServerIpTextfield.getText(),
					Integer.valueOf(clientServerPortTextField.getText()));
			ClientNetworkService.getInstance().sendeSpielerName(
					clientSpielernameTextField.getText());
			createAndAddConnectionStatusLabel(result);
			
			ClientController.getInstance().start(clientSpielernameTextField.getText());
		}

		/**
		 * Erstellt und fuegt ein Label zur Anzeige hinzu, ob der Server
		 * erfolgreich oder nicht erfolgreich gestartet wurde.
		 */
		private void createAndAddConnectionStatusLabel(boolean result) {
			if (connectionStatusLabel == null) {
				connectionStatusLabel = new JLabel();
			}
			if (result) {
				connectionStatusLabel
						.setText("Verbindung zum Server erfolgreich");
				connectionStatusLabel.setForeground(Color.GREEN);
			} else {
				connectionStatusLabel
						.setText("Verbindung zum Server fehlgeschlagen");
				connectionStatusLabel.setForeground(Color.RED);
			}
			connectionStatusLabel.setBounds(110, 130, 230, 20);
			connectionStatusLabel.setVisible(true);
			add(connectionStatusLabel);
			revalidate();
			repaint();
		}

	}

	private JTextField clientServerIpTextfield;

	private JTextField clientServerPortTextField;

	private JTextField clientSpielernameTextField;

	private JLabel connectionStatusLabel;

	private NetworkService networkService = NetworkService.getInstance();

	public ClientAdminPanel() {
		/*
		 * Server IP
		 */
		JPanel serverIpPanel = new JPanel();
		JLabel serverIpLabel = new JLabel("Server IP:");
		serverIpLabel.setPreferredSize(new Dimension(70, 30));
		clientServerIpTextfield = new JTextField(10);
		serverIpPanel.add(serverIpLabel);
		serverIpPanel.add(clientServerIpTextfield);
		serverIpPanel.setBounds(100, 0, 200, 30);

		/*
		 * Server Port
		 */
		JPanel serverPortPanel = new JPanel();
		JLabel serverPortLabel = new JLabel("Server Port:");
		serverPortLabel.setPreferredSize(new Dimension(70, 30));
		clientServerPortTextField = new JTextField(5);
		serverPortPanel.add(serverPortLabel);
		serverPortPanel.add(clientServerPortTextField);
		serverPortPanel.setBounds(72, 30, 200, 30);
		
		/*
		 * Spielername
		 */
		JPanel spielerNamePanel = new JPanel();
		JLabel spielerNameLabel = new JLabel("Spielername:");
		spielerNameLabel.setPreferredSize(new Dimension(70, 30));
		clientSpielernameTextField = new JTextField(10);
		spielerNamePanel.add(spielerNameLabel);
		spielerNamePanel.add(clientSpielernameTextField);
		spielerNamePanel.setBounds(72, 60, 200, 30);

		/*
		 * Connection Button
		 */
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(new ConnectButtonListener());

		connectButton.setBounds(130, 95, 155, 30);

		setSize(400, 100);
		setLayout(null);
		add(serverIpPanel);
		add(serverPortPanel);
		add(spielerNamePanel);
		add(connectButton);
	}
}
