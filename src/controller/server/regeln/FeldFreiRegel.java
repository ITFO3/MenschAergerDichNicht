package controller.server.regeln;

import java.util.HashMap;

import model.Figur;

public class FeldFreiRegel extends Regel {

	@Override
	public boolean check() {
		// Alle Figuren holen
		HashMap<Integer, Figur> figuren = this.zustand.getSpielfeld().getSpielfiguren();
		Integer position = 0;
		
		// Die aktuelle Figur holen
		for (Integer key : figuren.keySet()) {
			if (figuren.get(key).equals(this.zustand.getFigur())) {
				position = key;
				break;
			}
		}
		
		return (figuren.get(position + this.zustand.getWuerfelzahl()) == null);
	}

	@Override
	public void execute() {
		if (this.check()) {
			// Die Figur kann vorgesetzt werden 
		} else {
			// Wenn die Figur von einem anderen Spieler ist -> werfen
			// Ansonsten, nicht moeglich
		}
		
		if (this.nextRegel != null) {
			this.nextRegel.execute();
		}
	}

}
