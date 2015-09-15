package MenschAergerDichNicht.Regeln;

public class FeldFreiRegel extends Regel {

	@Override
	public boolean check() {
		return false;
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
