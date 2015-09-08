package MenschAergerDichNicht;

import java.util.ArrayList;
import java.util.HashMap;

public class Spielfeld {

	private ArrayList<Spieler> spieler;
	private HashMap<String, Figur> spielfiguren;
	
	public Spielfeld() {
		spieler = new ArrayList<Spieler>();
		spielfiguren = new HashMap<String, Figur>();
	}
	
	public void setzteAufsSpielfeld(Figur[] eigeneSpielfiguren, Spieler spieler) {
		
	}
	
}
