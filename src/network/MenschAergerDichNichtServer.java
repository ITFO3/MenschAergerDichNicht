package network;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MenschAergerDichNichtServer implements Runnable {

	// a unique ID for each connection
	private static int uniqueId;

	// an ArrayList to keep the list of the Client
	private ArrayList<ClientThread> clientThreads;

	// the port number to listen for connection
	private int port;

	// the boolean that will be turned of to stop the server
	private boolean keepGoing;

	public MenschAergerDichNichtServer(int port) {
		this.port = port;
		clientThreads = new ArrayList<ClientThread>();
	}

	@Override
	public void run() {
	}
}
