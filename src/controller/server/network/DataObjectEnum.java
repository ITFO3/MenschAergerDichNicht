package controller.server.network;

public enum DataObjectEnum {
	/*
	 * Wird vom Client beim Verbinden an den Server geschickt
	 * 
	 * Bsp.: SPIELERNAME=Chris
	 */
	SPIELERNAME,
	/*
	 * Wird vom Client beim Verbinden an den Server geschickt
	 * 
	 * Bsp.: SPIELERFARBE=RED
	 */
	SPIELERFARBE,
	/*
	 * Spiel wird gestartet
	 * 
	 * Bsp: STARTGAME=true,SPIELER={Test,Chris,Daniel}
	 */
	STARTGAME,
	/*
	 * Eine Menge von Figuren die gesetzt werden koennen. 
	 * Beinhaltet auch die gewuerfelte Zahl.
	 * 
	 * Bsp.: MOEGLICHKEITEN={Test:1,Test:3},4
	 * 
	 * Aufruf im Clienten:
	 * 
	 * figurenIds = new ArrayList<String>();
	 * ClientController.zeigeMoeglichkeiten(figurenIds, wurfanzahl);
	 */
	MOEGLICHKEITEN,
	/*
	 * Wird vom Client an den Server geschickt. 
	 * Beinhaltet die vom Spieler ausgewaehlte Figur eines Zugs.
	 * 
	 * Bsp.: GEWAEHLTEFIGUR=Test:1
	 */
	GEWAEHLTEFIGUR,
	/*
	 * Wird vom Server an die Clienten gesendet, 
	 * sobald sich eine Figur geaendert hat. 
	 * Beinhaltet die Id der Figur und die neue Position.
	 * 
	 * Bsp.: FIGURGEAENDERT=Test:3,19
	 * 
	 * Aufruf im Clienten:
	 * ClientController.aktualisiereSpielfeld(figurId, null, neuePosition);
	 */
	FIGURGEAENDERT;
}
