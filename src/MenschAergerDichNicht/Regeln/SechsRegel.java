package MenschAergerDichNicht.Regeln;

public class SechsRegel implements IRegel{

	@Override
	public boolean check(Zustand zustand) {
		if (zustand.getWuerfelzahl() == 6) {
			return true;
		} else {
			return false;
		}
	}
	
}
