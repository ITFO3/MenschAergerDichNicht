import java.util.ArrayList;


public class ClientController {

	
	private List<Spieler> spieler;
	private Spielfeld spielfeld;
	//muss noch initialisiert werden
	private ServerController server;
	
	aktualisiereSpielfeld(String ausgewaehlteFigurId, String geschlageneFigurId, int neuePosition) {
		
		Figur ausgewaehltFigur = sucheFigur(ausgewaehlteFigurId);
		Figur geschlageneFigur = null;
		if(geschlageneFigurId != null) geschlageneFigur = sucheFigur(geschlageneFigurId);
		
		
		ausgewaehltFigur.setPosition(neuePosition);
		if(geschlageneFigur != null) geschlageneFigur.setPosition(geschlageneFigur.getHausfeld());	
	}
	
	zeigeMoeglichkeiten(List<String> moeglichkeiten, int wurfAnzahl) {
		
		ArrayList<Figur> figuren = new ArrayList<Figur>(4);
		
		for(String figurId : moeglichkeiten) {
			
			figuren.add(sucheFigur(figurId));
		}
		
		
	}
	
	
	private Figur sucheFigur(String id) {
		for(Figur f : spielfeld.getFiguren()) {
			if(f.getId().equals(figurId)) 
				return f;
		}
	}
	
}
