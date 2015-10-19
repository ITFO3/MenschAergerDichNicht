package GUI.admin.network;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import network.NetworkService;

/**
 * JPanel zur Serveradministration
 * 
 * @author ChrisWun
 * 
 */
public class ServerAdminPanel extends JPanel {

	JTextField serverServerPortTextField;

	NetworkService networkService = NetworkService.getInstance();

	JLabel connectionStatusLabel;

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
		 * Startbutton
		 */
		JButton startServerButton = new JButton("Start Server");
		startServerButton.addActionListener(new StartServerButtonListener());
		add(startServerButton);
		startServerButton.setBounds(125, 40, 150, 30);

		setLayout(null);
	}

	private class StartServerButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean result = networkService.startServer(Integer
					.valueOf(serverServerPortTextField.getText()));
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
			connectionStatusLabel.setBounds(125, 80, 200, 20);
			connectionStatusLabel.setVisible(true);
			add(connectionStatusLabel);
			revalidate();
			repaint();
		}
	}
}
