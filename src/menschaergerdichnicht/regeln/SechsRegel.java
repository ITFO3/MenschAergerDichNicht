package menschaergerdichnicht.regeln;

/**
 * Diese Regel wird angewendet, wenn eine Sechs gewuerfelt wurde
 */
public class SechsRegel extends Regel {

	@Override
	public boolean check() {
		if (this.zustand.getWuerfelzahl() == 6) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void execute() {
		if (this.check()) {
			// Figur muss raus gesetzt werden
			
			
			// Benutzer darf nochmal wuerfeln
		}
		
		if (this.nextRegel != null) {
			this.nextRegel.execute();
		}
	}
}
