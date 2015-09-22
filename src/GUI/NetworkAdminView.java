package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import network.NetworkService;

public class NetworkAdminView extends JFrame {

	JRadioButton clientButton;

	JRadioButton serverButton;
	
	JPanel clientAdminPanel;
	
	JPanel serverAdminPanel;
	
	NetworkService networkService = NetworkService.getInstance();
	
	public static void main(String[] args) {
		new NetworkAdminView();
	}

	public NetworkAdminView() {
		JPanel networkModePanel = createNetworkModePanel();

		setLayout(new BorderLayout());
		add(networkModePanel, BorderLayout.NORTH);
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	private JPanel createNetworkModePanel() {
		ActionListener networkModeActionListener = new NetworkModeListener();
		clientButton = new JRadioButton("Client");
		clientButton.addActionListener(networkModeActionListener);
		serverButton = new JRadioButton("Server");
		serverButton.addActionListener(networkModeActionListener);
		ButtonGroup networkModeGroup = new ButtonGroup();
		networkModeGroup.add(clientButton);
		networkModeGroup.add(serverButton);

		JPanel networkModeButtonPanel = new JPanel();
		networkModeButtonPanel.add(clientButton);
		networkModeButtonPanel.add(serverButton);

		return networkModeButtonPanel;
	}
	
	private JPanel createServerAdminPanel(){
		JPanel serverAdminPanel = new JPanel();
		
		JPanel serverPortPanel = new JPanel();
		JLabel serverPortLabel = new JLabel("Port:");
		JTextField serverPortTextField = new JTextField(5);
		serverPortPanel.add(serverPortLabel);
		serverPortPanel.add(serverPortTextField);
		serverAdminPanel.add(serverPortPanel);
		
		JButton startServerButton = new JButton("Start Server");
		serverAdminPanel.add(startServerButton);
		
		serverAdminPanel.setLayout(new BoxLayout(serverAdminPanel,
				BoxLayout.Y_AXIS));
		this.serverAdminPanel = serverAdminPanel;
		return serverAdminPanel;
	}

	private JPanel createClientAdminPanel() {
		JPanel serverIpPanel = new JPanel();
		JLabel serverIpLabel = new JLabel("Server IP:");
		JTextField serverIpTextfield = new JTextField(20);
		serverIpPanel.add(serverIpLabel);
		serverIpPanel.add(serverIpTextfield);

		JPanel serverPortPanel = new JPanel();
		JLabel serverPortLabel = new JLabel("Server Port");
		JTextField serverPortTextField = new JTextField(5);
		serverPortPanel.add(serverPortLabel);
		serverPortPanel.add(serverPortTextField);

		JButton connectButton = new JButton("Connect");

		JPanel clientAdminPanel = new JPanel();
		clientAdminPanel.setSize(400, 200);
		clientAdminPanel.setLayout(new BoxLayout(clientAdminPanel,
				BoxLayout.Y_AXIS));
		clientAdminPanel.add(serverIpPanel);
		clientAdminPanel.add(serverPortPanel);
		clientAdminPanel.add(connectButton);
		
		this.clientAdminPanel = clientAdminPanel;
		return clientAdminPanel;
	}

	private void showClientAdmin() {
		if(serverAdminPanel != null){
			serverAdminPanel.setVisible(false);
		}
		JPanel clientAdminPanel = createClientAdminPanel();
		add(clientAdminPanel, BorderLayout.CENTER);
		validate();
		repaint();
	}

	private void showServerAdmin() {
		if(clientAdminPanel != null){
			clientAdminPanel.setVisible(false);
		}
		JPanel serverAdminPanel = createServerAdminPanel();
		add(serverAdminPanel, BorderLayout.CENTER);
		validate();
		repaint();
	}

	private class NetworkModeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (clientButton.isSelected()) {
				showClientAdmin();
			}
			if (serverButton.isSelected()) {
				showServerAdmin();
			}

		}

	}

}
